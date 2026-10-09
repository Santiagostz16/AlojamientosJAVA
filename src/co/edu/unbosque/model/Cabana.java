package co.edu.unbosque.model;

// define los datos y el cobro de reserva para una cabaña
public class Cabana extends Alojamiento {
    public Cabana(String id, String nombre, String ciudad, String ubicacion, int capacidad, double precioNoche, boolean activo, String descripcion) {
        super(id, nombre, ciudad, ubicacion, capacidad, precioNoche, activo, descripcion);
    }

    @Override
    public String getTipo() {
        return "Cabana";
    }

    // sumo el cargo fijo de la cabaña al precio de las noches reservadas
    @Override
    public double calcularValorReserva(int noches) {
        return getPrecioNoche() * noches + 20000;
    }
}
