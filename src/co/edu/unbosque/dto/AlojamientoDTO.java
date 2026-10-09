package co.edu.unbosque.dto;

public class AlojamientoDTO {
    private String id, nombre, ciudad, ubicacion, descripcion, tipo;
    private int capacidad;
    private double precioNoche;
    private boolean activo;

    public AlojamientoDTO(String id, String nombre, String ciudad, String ubicacion,
                          int capacidad, double precioNoche, boolean activo,
                          String descripcion, String tipo) {
        this.id = id; this.nombre = nombre; this.ciudad = ciudad; this.ubicacion = ubicacion;
        this.capacidad = capacidad; this.precioNoche = precioNoche; this.activo = activo;
        this.descripcion = descripcion; this.tipo = tipo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public String getUbicacion() { return ubicacion; }
    public int getCapacidad() { return capacidad; }
    public double getPrecioNoche() { return precioNoche; }
    public boolean isActivo() { return activo; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }
}
