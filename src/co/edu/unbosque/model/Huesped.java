package co.edu.unbosque.model;

public class Huesped {
    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

    public Huesped(String id, String nombre, String apellido, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return id + " | " + nombre + " " + apellido + " | " + correo + " | " + telefono;
    }
}
