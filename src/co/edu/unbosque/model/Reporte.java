package co.edu.unbosque.model;

import java.util.List;

public class Reporte {

    public int alojamientosPorCiudad(List<Alojamiento> alojamientos, String ciudad) {

        int cantidad = 0;

        for (Alojamiento a : alojamientos) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int reservasConfirmadas(List<Reserva> reservas) {

        int cantidad = 0;

        for (Reserva r : reservas) {
            if (r.getEstado().equals("CONFIRMADA")) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int reservasCanceladas(List<Reserva> reservas) {

        int cantidad = 0;

        for (Reserva r : reservas) {
            if (r.getEstado().equals("CANCELADA")) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public double ingresosEstimados(List<Reserva> reservas) {

        double total = 0;

        for (Reserva r : reservas) {
            if (r.getEstado().equals("CONFIRMADA")) {
                total += r.getValorTotal();
            }
        }

        return total;
    }

    public int alojamientosPorTipo(List<Alojamiento> alojamientos, String tipo) {

        int cantidad = 0;

        for (Alojamiento a : alojamientos) {
            if (a.getTipo().equalsIgnoreCase(tipo)) {
                cantidad++;
            }
        }

        return cantidad;
    }
}
