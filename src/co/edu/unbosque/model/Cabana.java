package co.edu.unbosque.model;

public class Cabana extends Alojamiento {
    public Cabana(String id, String nombre, String ciudad, String ubicacion, int capacidad, double precioNoche, boolean activo, String descripcion) {
        super(id, nombre, ciudad, ubicacion, capacidad, precioNoche, activo, descripcion);
    }

    @Override
    public String getTipo() {
        return "Cabana";
    }

    @Override
    public double calcularValorReserva(int noches) {
        return getPrecioNoche() * noches + 20000;
    }
}
