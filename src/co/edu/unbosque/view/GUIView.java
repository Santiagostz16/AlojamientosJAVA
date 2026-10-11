package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import co.edu.unbosque.model.Alojamiento;
import co.edu.unbosque.model.Huesped;
import co.edu.unbosque.model.Reserva;

public class GUIView extends View {

    private boolean menuReportes = false;

    // preparo el estado para mostrar el menu principal en la siguiente lectura
    @Override
    public void mostrarMenu() {
        menuReportes = false;
    }

    // muestro las opciones en una ventana y devuelvo la opcion seleccionada
    @Override
    public int leerOpcion() {
        if (menuReportes) {
            String[] opciones = {
                "Alojamientos por ciudad",
                "Reservas confirmadas",
                "Reservas canceladas",
                "Ingresos estimados",
                "Alojamientos por tipo"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione un reporte:",
                    "Reportes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (opcion == -1) {
                return 0;
            }

            menuReportes = false;
            return opcion + 1;
        }

        String[] opciones = {
            "Consultar alojamientos",
            "Buscar alojamientos",
            "Consultar detalle",
            "Registrar huesped",
            "Crear reserva",
            "Consultar reservas",
            "Cancelar reserva",
            "Consultar huespedes",
            "Reportes",
            "Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                null,
                "SISTEMA DE ALOJAMIENTOS",
                "Menu principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcion == -1) {
            return 0;
        }

        if (opcion == 8) {
            menuReportes = true;
        }

        if (opcion == 9) {
            return 0;
        }

        return opcion + 1;
    }

    // pido un texto mediante una ventana
    @Override
    public String leerTexto(String mensaje) {
        String dato = JOptionPane.showInputDialog(null, mensaje, "Dato", JOptionPane.PLAIN_MESSAGE);

        if (dato == null) {
            return "";
        }

        return dato.trim();
    }

    // repito la lectura hasta que se escriba un dato
    @Override
    public String leerObligatorio(String mensaje) {
        String dato;

        do {
            dato = leerTexto(mensaje);

            if (dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor, añada un dato.", "Validacion",
                        JOptionPane.WARNING_MESSAGE);
            }
        } while (dato.isEmpty());

        return dato;
    }

    // valido que el dato ingresado sea un entero
    @Override
    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero.",
                        "Validacion", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // valido que el entero sea mayor que cero
    @Override
    public int leerEnteroPositivo(String mensaje) {
        int valor;

        do {
            valor = leerEntero(mensaje);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Debe ser mayor que cero.",
                        "Validacion", JOptionPane.WARNING_MESSAGE);
            }
        } while (valor <= 0);

        return valor;
    }

    // valido que el valor decimal sea positivo
    @Override
    public double leerDoublePositivo(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje));

                if (valor > 0) {
                    return valor;
                }

                JOptionPane.showMessageDialog(null, "El valor debe ser mayor que cero.",
                        "Validacion", JOptionPane.WARNING_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.",
                        "Validacion", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // valido que la fecha tenga el formato esperado
    @Override
    public LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(leerTexto(mensaje));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Use el formato AAAA-MM-DD.",
                        "Validacion", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // muestro los tipos disponibles para elegir uno
    @Override
    public String leerTipo() {
        String[] opciones = {"Apartamento", "Casa", "Cabaña"};

        int opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de alojamiento:",
                "Tipo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcion == 0) {
            return "Apartamento";
        }

        if (opcion == 1) {
            return "Casa";
        }

        if (opcion == 2) {
            return "Cabaña";
        }

        return "";
    }

    // muestro los alojamientos en una ventana con scroll
    @Override
    public void mostrarAlojamientos(List<Alojamiento> lista) {
        if (lista.isEmpty()) {
            mensaje("No hay alojamientos.");
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Alojamiento a : lista) {
            texto.append(a).append("\n");
        }

        mostrarTexto("Alojamientos", texto.toString());
    }

    // muestro el detalle completo del alojamiento
    @Override
    public void mostrarDetalle(Alojamiento a) {
        if (a == null) {
            mensaje("Alojamiento no encontrado.");
            return;
        }

        String estado;

        if (a.isActivo()) {
            estado = "ACTIVO";
        } else {
            estado = "INACTIVO";
        }

        String texto =
                "ID: " + a.getId() + "\n" +
                "Nombre: " + a.getNombre() + "\n" +
                "Ciudad: " + a.getCiudad() + "\n" +
                "Tipo: " + a.getTipo() + "\n" +
                "Ubicacion: " + a.getUbicacion() + "\n" +
                "Capacidad: " + a.getCapacidad() + "\n" +
                "Precio por noche: $" + a.getPrecioNoche() + "\n" +
                "Estado: " + estado + "\n" +
                "Descripcion: " + a.getDescripcion();

        mensaje(texto);
    }

    // muestro los huespedes registrados
    @Override
    public void mostrarHuespedes(List<Huesped> lista) {
        if (lista.isEmpty()) {
            mensaje("No hay huespedes.");
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Huesped h : lista) {
            texto.append(h).append("\n");
        }

        mostrarTexto("Huespedes", texto.toString());
    }

    // muestro las reservas registradas
    @Override
    public void mostrarReservas(List<Reserva> lista) {
        if (lista.isEmpty()) {
            mensaje("No hay reservas.");
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Reserva r : lista) {
            texto.append(r).append("\n");
        }

        mostrarTexto("Reservas", texto.toString());
    }

    // muestro mensajes del controlador en una ventana
    @Override
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Sistema de Alojamientos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // muestro textos largos dentro de una ventana con desplazamiento
    private void mostrarTexto(String titulo, String texto) {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(650, 350));

        JOptionPane.showMessageDialog(null, scroll, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // no cierro el Scanner de View porque la GUI no lo utiliza
    @Override
    public void cerrar() {
    }
}
