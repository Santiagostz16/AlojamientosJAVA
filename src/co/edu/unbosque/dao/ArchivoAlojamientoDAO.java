package co.edu.unbosque.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.dto.AlojamientoDTO;

// manejo la carga y el guardado de alojamientos en alojamientos.txt
public class ArchivoAlojamientoDAO implements AlojamientoDAO {
    private final String archivo = "data/alojamientos.txt";

    @Override
    // leo cada línea y solo convierto los registros con sus nueve campos válidos
    public List<AlojamientoDTO> cargar() {
        List<AlojamientoDTO> lista = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] d = linea.split("\\|", -1);
                if (d.length == 9) {
                    try {
                        lista.add(new AlojamientoDTO(d[0], d[1], d[2], d[3],
                                Integer.parseInt(d[4]), Double.parseDouble(d[5]),
                                Boolean.parseBoolean(d[6]), d[7], d[8]));
                    } catch (NumberFormatException e) {
                        System.out.println("Registro de alojamiento incorrecto: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer alojamientos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    // escribo la lista completa usando una línea por alojamiento
    public void guardar(List<AlojamientoDTO> datos) {
        crearCarpeta();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (AlojamientoDTO a : datos) {
                pw.println(a.getId()+"|"+a.getNombre()+"|"+a.getCiudad()+"|"+a.getUbicacion()+"|"
                        +a.getCapacidad()+"|"+a.getPrecioNoche()+"|"+a.isActivo()+"|"
                        +a.getDescripcion()+"|"+a.getTipo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar alojamientos: " + e.getMessage());
        }
    }

    // me aseguro de que exista la carpeta antes de crear el archivo
    private void crearCarpeta() { 
    	new File("data").mkdirs(); }
}
