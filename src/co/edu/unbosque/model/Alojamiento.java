package co.edu.unbosque.model;

public abstract class Alojamiento implements CalculableReserva {
    private String id;
    private String nombre;
    private String ciudad;
    private String ubicacion;
    private int capacidad;
    private double precioNoche;
    private boolean activo;
    private String descripcion;

    public Alojamiento(String id, String nombre, String ciudad, String ubicacion,
                       int capacidad, double precioNoche, boolean activo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.activo = activo;
        this.descripcion = descripcion;
    }

    public abstract String getTipo();

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public String getUbicacion() { return ubicacion; }
    public int getCapacidad() { return capacidad; }
    public double getPrecioNoche() { return precioNoche; }
    public boolean isActivo() { return activo; }
    public String getDescripcion() { return descripcion; }

    public void setActivo(boolean activo) { this.activo = activo; }

    public boolean estaDisponible() {
        return activo;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + ciudad + " | " + getTipo()
                + " | Capacidad: " + capacidad + " | Precio: $" + precioNoche
                + " | " + (activo ? "ACTIVO" : "INACTIVO");
    }
}
