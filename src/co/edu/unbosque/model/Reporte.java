package co.edu.unbosque.model;

import java.util.List;

public class Reporte {

    // cuento los alojamientos cuya ciudad coincide con la solicitada
    public int alojamientosPorCiudad(List<Alojamiento> alojamientos, String ciudad) {
        int cantidad = 0;
        for (Alojamiento a : alojamientos) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // cuento las reservas que siguen confirmadas
    public int reservasConfirmadas(List<Reserva> reservas) {
        int cantidad = 0;
        for (Reserva r : reservas) {
            if (r.getEstado().equals("CONFIRMADA")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // cuento las reservas que tienen estado cancelada
    public int reservasCanceladas(List<Reserva> reservas) {
        int cantidad = 0;
        for (Reserva r : reservas) {
            if (r.getEstado().equals("CANCELADA")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // sumo el valor de las reservas confirmadas y excluyo las canceladas
    public double ingresosEstimados(List<Reserva> reservas) {
        double total = 0;
        for (Reserva r : reservas) {
            if (r.getEstado().equals("CONFIRMADA")) {
                total += r.getValorTotal();
            }
        }
        return total;
    }

    // cuento los alojamientos cuyo tipo coincide con el solicitado
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
