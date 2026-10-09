package co.edu.unbosque.model;

public class Apartamento extends Alojamiento {
    public Apartamento(String id, String nombre, String ciudad, String ubicacion,
                       int capacidad, double precioNoche, boolean activo, String descripcion) {
        super(id, nombre, ciudad, ubicacion, capacidad, precioNoche, activo, descripcion);
    }

    @Override
    public String getTipo() {
        return "Apartamento";
    }

    @Override
    public double calcularValorReserva(int noches) {
        return getPrecioNoche() * noches;
    }
}
