package co.edu.unbosque.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import co.edu.unbosque.dao.*;
import co.edu.unbosque.dto.*;
import co.edu.unbosque.mapper.DataMapper;
import co.edu.unbosque.model.*;
import co.edu.unbosque.view.View;
import co.edu.unbosque.model.Reporte;

public class Controller {
    private List<Alojamiento> alojamientos = new ArrayList<>();
    private Set<String> idsAlojamientos = new HashSet<>();
    private List<Huesped> huespedes = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private AlojamientoDAO alojamientoDAO = new ArchivoAlojamientoDAO();
    private HuespedDAO huespedDAO = new ArchivoHuespedDAO();
    private ReservaDAO reservaDAO = new ArchivoReservaDAO();
    private DataMapper mapper = new DataMapper();
    private View view;
    private Reporte reporte = new Reporte();

    public Controller(View view) {
        this.view = view;
    }

    public void iniciar() {
        cargarDatos();
        ejecutarMenu();
    }

    private void cargarDatos() {
        for (AlojamientoDTO d : alojamientoDAO.cargar()) {
            if (idsAlojamientos.add(d.getId())) {
                alojamientos.add(mapper.dtoToAlojamiento(d));
            }
        }

        for (HuespedDTO d : huespedDAO.cargar())
            huespedes.add(mapper.dtoToHuesped(d));

        for (ReservaDTO d : reservaDAO.cargar()) {
            Huesped h = buscarHuesped(d.getIdHuesped());
            Alojamiento a = buscarAlojamiento(d.getIdAlojamiento());
            if (h != null && a != null) {
                Reserva r = new Reserva(d.getId(), h, a, d.getFechaLlegada(), d.getFechaSalida(),
                        d.getNumeroHuespedes());
                if (d.getEstado().equals("cancelada!!!!!"))
                    r.cancelar();
                reservas.add(r);
            }
        }
    }

    private void ejecutarMenu() {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = view.leerOpcion();
            switch (opcion) {
            case 1:
                listarAlojamientos();
                break;
            case 2:
                buscarAlojamientos();
                break;
            case 3:
                verDetalle();
                break;
            case 4:
                registrarHuesped();
                break;
            case 5:
                crearReserva();
                break;
            case 6:
                view.mostrarReservas(reservas);
                break;
            case 7:
                cancelarReserva();
                break;
            case 8:
                view.mostrarHuespedes(huespedes);
                break;
            case 9:
                reportes();
                break;
            case 0:
                guardarTodo();
                view.mensaje("cambios guardados!!!!");
                break;
            default:
                view.mensaje("Opción no valida");
            }
        } while (opcion != 0);
        view.cerrar();
    }

    private void listarAlojamientos() {
        view.mostrarAlojamientos(alojamientos);
    }

    private void buscarAlojamientos() {
        String ciudad = view.leerTexto("Ciudad (dejala en blanco para CUALQUIERA): ");
        String tipo = view.leerTexto("Tipo(dejala en blanco para CUALQUIERA): ");
        String capacidadTexto = view.leerTexto("Capacidad minima(dejala en blanco para CUALQUIERA): ");
        String precioTexto = view.leerTexto("Precio maximo (dejala en blanco para CUALQUIERA): ");

        int capacidad = 0;
        double precio = Double.MAX_VALUE;

        try {
            if (!capacidadTexto.isEmpty())
                capacidad = Integer.parseInt(capacidadTexto);
            if (!precioTexto.isEmpty())
                precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            view.mensaje("Uno de los filtros numericos no es valido.");
            return;
        }

        List<Alojamiento> resultado = new ArrayList<>();
        for (Alojamiento a : alojamientos) {
            boolean ciudadOk = ciudad.isEmpty() || a.getCiudad().equalsIgnoreCase(ciudad);
            boolean tipoOk = tipo.isEmpty() || a.getTipo().equalsIgnoreCase(tipo);
            boolean capacidadOk = a.getCapacidad() >= capacidad;
            boolean precioOk = a.getPrecioNoche() <= precio;
            if (ciudadOk && tipoOk && capacidadOk && precioOk)
                resultado.add(a);
        }
        view.mostrarAlojamientos(resultado);
    }

    private void verDetalle() {
        String id = view.leerObligatorio("ID del alojamiento: ");
        view.mostrarDetalle(buscarAlojamiento(id));
    }

    private void registrarHuesped() {
        String id = view.leerObligatorio("ID: ");

        if (id.length() != 10) {
            view.mensaje("El ID debe tener 10 digitos.");
            return;
        }

        for (char c : id.toCharArray()) {
            if (!Character.isDigit(c)) {
                view.mensaje("El ID debe tener solo numeros.");
                return;
            }
        }

        if (buscarHuesped(id) != null) {
            view.mensaje("El ID ya existe.");
            return;
        }

        String nombre = view.leerObligatorio("Nombre: ");
        String apellido = view.leerObligatorio("Apellido: ");
        String correo = view.leerObligatorio("Correo: ");
        String telefono = view.leerObligatorio("Telefono: ");
        huespedes.add(new Huesped(id, nombre, apellido, correo, telefono));
        guardarHuespedes();
        view.mensaje("Huesped registrado.");
    }

    private void crearReserva() {
        String id = view.leerObligatorio("ID de reserva: ");
        if (buscarReserva(id) != null) {
            view.mensaje("El ID de reserva ya existe.");
            return;
        }

        Huesped h = buscarHuesped(view.leerObligatorio("ID del huesped: "));
        if (h == null) {
            view.mensaje("El huesped no esta registrado.");
            return;
        }

        Alojamiento a = buscarAlojamiento(view.leerObligatorio("ID del alojamiento: "));
        if (a == null) {
            view.mensaje("El alojamiento no existe.");
            return;
        }

        if (!a.estaDisponible()) {
            view.mensaje("El alojamiento esta inactivo.");
            return;
        }

        LocalDate llegada = view.leerFecha("Fecha llegada AAAA-MM-DD: ");
        LocalDate salida = view.leerFecha("Fecha salida AAAA-MM-DD: ");
        if (!salida.isAfter(llegada)) {
            view.mensaje("La salida debe ser posterior.");
            return;
        }

        int cantidad = view.leerEnteroPositivo("Numero de huespedes: ");
        if (cantidad > a.getCapacidad()) {
            view.mensaje("La cantidad supera la capacidad.");
            return;
        }

        if (hayCruce(a, llegada, salida)) {
            view.mensaje("El alojamiento ya esta reservado en esas fechas.");
            return;
        }

        Reserva r = new Reserva(id, h, a, llegada, salida, cantidad);
        reservas.add(r);
        guardarReservas();
        view.mensaje("Reserva creada. Total: $" + r.getValorTotal());
    }

    private boolean hayCruce(Alojamiento a, LocalDate llegada, LocalDate salida) {
        for (Reserva r : reservas) {
            if (r.getEstado().equals("CONFIRMADA") && r.getAlojamiento().getId().equals(a.getId())) {
                if (llegada.isBefore(r.getFechaSalida()) && salida.isAfter(r.getFechaLlegada()))
                    return true;
            }
        }
        return false;
    }

    private void cancelarReserva() {
        Reserva r = buscarReserva(view.leerObligatorio("ID de reserva: "));
        if (r == null) {
            view.mensaje("Reserva no encontrada.");
            return;
        }

        if (r.getEstado().equals("CANCELADA")) {
            view.mensaje("La reserva ya esta cancelada.");
            return;
        }

        r.cancelar();
        guardarReservas();
        view.mensaje("Reserva cancelada.");
    }

    private void reportes() {
        System.out.println("1. Alojamientos por ciudad");
        System.out.println("2. Reservas confirmadas");
        System.out.println("3. Reservas canceladas");
        System.out.println("4. Ingresos estimados");
        System.out.println("5. Alojamientos por tipo");

        int op = view.leerOpcion();
        switch (op) {
        case 1:
            String ciudad = view.leerObligatorio("Ciudad: ");
            int cantidad = reporte.alojamientosPorCiudad(
                    alojamientos, ciudad);
            view.mensaje("Alojamientos en " + ciudad + ": " + cantidad);
            break;
        case 2:
            int confirmadas = reporte.reservasConfirmadas(reservas);
            view.mensaje("Reservas confirmadas: " + confirmadas);
            break;
        case 3:
            int canceladas = reporte.reservasCanceladas(reservas);
            view.mensaje("Reservas canceladas: " + canceladas);
            break;
        case 4:
            double total = reporte.ingresosEstimados(reservas);
            view.mensaje("Ingresos estimados: $" + total);
            break;
        case 5:
            int apt = reporte.alojamientosPorTipo(
                    alojamientos, "Apartamento");
            int casas = reporte.alojamientosPorTipo(
                    alojamientos, "Casa");
            int cabanas = reporte.alojamientosPorTipo(
                    alojamientos, "Cabana");
            view.mensaje("Apartamentos: " + apt);
            view.mensaje("Casas: " + casas);
            view.mensaje("Cabanas: " + cabanas);
            break;
        default:
            view.mensaje("reporte no valido.");
        }
    }

    private Alojamiento buscarAlojamiento(String id) {
        for (Alojamiento a : alojamientos)
            if (a.getId().equalsIgnoreCase(id))
                return a;
        return null;
    }

    private Huesped buscarHuesped(String id) {
        for (Huesped h : huespedes)
            if (h.getId().equalsIgnoreCase(id))
                return h;
        return null;
    }

    private Reserva buscarReserva(String id) {
        for (Reserva r : reservas)
            if (r.getId().equalsIgnoreCase(id))
                return r;
        return null;
    }

    private void guardarTodo() {
        guardarAlojamientos();
        guardarHuespedes();
        guardarReservas();
    }

    private void guardarAlojamientos() {
        List<AlojamientoDTO> datos = new ArrayList<>();
        for (Alojamiento a : alojamientos)
            datos.add(mapper.alojamientoToDTO(a));
        alojamientoDAO.guardar(datos);
    }

    private void guardarHuespedes() {
        List<HuespedDTO> datos = new ArrayList<>();
        for (Huesped h : huespedes)
            datos.add(mapper.huespedToDTO(h));
        huespedDAO.guardar(datos);
    }

    private void guardarReservas() {
        List<ReservaDTO> datos = new ArrayList<>();
        for (Reserva r : reservas)
            datos.add(mapper.reservaToDTO(r));
        reservaDAO.guardar(datos);
    }
}
