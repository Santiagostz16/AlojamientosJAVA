package co.edu.unbosque.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private String id;
    private Huesped huesped;
    private Alojamiento alojamiento;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private int numeroHuespedes;
    private double valorTotal;
    private String estado;

    public Reserva(String id, Huesped huesped, Alojamiento alojamiento,
                   LocalDate fechaLlegada, LocalDate fechaSalida, int numeroHuespedes) {
        this.id = id;
        this.huesped = huesped;
        this.alojamiento = alojamiento;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.numeroHuespedes = numeroHuespedes;
        this.valorTotal = calcularValorTotal();
        this.estado = "CONFIRMADA";
    }

    // aplico la tarifa que corresponde al alojamiento para las noches reservadas
    private double calcularValorTotal() {
        return alojamiento.calcularValorReserva(getNumeroNoches());
    }

    public String getId() { return id; }
    public Huesped getHuesped() { return huesped; }
    public Alojamiento getAlojamiento() { return alojamiento; }
    public LocalDate getFechaLlegada() { return fechaLlegada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public int getNumeroHuespedes() { return numeroHuespedes; }
    // cuento los días entre llegada y salida como noches de la estadía
    public int getNumeroNoches() {
        return (int) ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
    }
    public double getValorTotal() { return valorTotal; }
    public String getEstado() { return estado; }

    // cambio el estado solo si la reserva todavía está confirmada
    public void cancelar() {
        if (estado.equals("CONFIRMADA")) {
            estado = "CANCELADA";
        }
    }

    @Override
    public String toString() {
        return id + " | Huesped: " + huesped.getId()
                + " | Alojamiento: " + alojamiento.getId()
                + " | " + fechaLlegada + " a " + fechaSalida
                + " | Noches: " + getNumeroNoches()
                + " | Total: $" + valorTotal + " | " + estado;
    }
}
