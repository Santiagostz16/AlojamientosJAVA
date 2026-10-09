package co.edu.unbosque.dto;

import java.time.LocalDate;

public class ReservaDTO {
    private String id, idHuesped, idAlojamiento, estado;
    private LocalDate fechaLlegada, fechaSalida;
    private int numeroHuespedes;
    private double valorTotal;

    public ReservaDTO(String id, String idHuesped, String idAlojamiento,
                      LocalDate fechaLlegada, LocalDate fechaSalida,
                      int numeroHuespedes, double valorTotal, String estado) {
        this.id = id; this.idHuesped = idHuesped; this.idAlojamiento = idAlojamiento;
        this.fechaLlegada = fechaLlegada; this.fechaSalida = fechaSalida;
        this.numeroHuespedes = numeroHuespedes; this.valorTotal = valorTotal; this.estado = estado;
    }

    public String getId() { return id; }
    public String getIdHuesped() { return idHuesped; }
    public String getIdAlojamiento() { return idAlojamiento; }
    public LocalDate getFechaLlegada() { return fechaLlegada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public int getNumeroHuespedes() { return numeroHuespedes; }
    public double getValorTotal() { return valorTotal; }
    public String getEstado() { return estado; }
}
