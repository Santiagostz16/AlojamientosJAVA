package co.edu.unbosque.dto;

// transporto los datos personales de un huésped hacia y desde el archivo
public class HuespedDTO {
    private String id, nombre, apellido, correo, telefono;

    public HuespedDTO(String id, String nombre, String apellido, String correo, String telefono) {
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
}
