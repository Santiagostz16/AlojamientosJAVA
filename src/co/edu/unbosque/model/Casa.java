package co.edu.unbosque.model;

// define los datos y el cobro de reserva para una casa
public class Casa extends Alojamiento {
    public Casa(String id, String nombre, String ciudad, String ubicacion, int capacidad, double precioNoche, boolean activo, String descripcion) {
        super(id, nombre, ciudad, ubicacion, capacidad, precioNoche, activo, descripcion);
    }

    @Override
    public String getTipo() {
        return "Casa";
    }

    // sumo el cargo fijo de la casa al precio de las noches reservadas
    @Override
    public double calcularValorReserva(int noches) {
        return getPrecioNoche() * noches + 30000;
    }
}
