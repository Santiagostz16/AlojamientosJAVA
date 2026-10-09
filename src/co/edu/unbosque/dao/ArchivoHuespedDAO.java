package co.edu.unbosque.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.dto.HuespedDTO;

// manejo la carga y el guardado de huespedes en huespedes.txt
public class ArchivoHuespedDAO implements HuespedDAO {
    private final String archivo = "data/huespedes.txt";

    @Override
    // leo las líneas del archivo y armo un huésped cuando encuentro sus cinco datos
    public List<HuespedDTO> cargar() {
        List<HuespedDTO> lista = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] d = linea.split("\\|", -1);
                if (d.length == 5) lista.add(new HuespedDTO(d[0],d[1],d[2],d[3],d[4]));
            }
        } catch (IOException e) {
            System.out.println("Error al leer huespedes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    // guardo los datos de cada huesped separados por el mismo formato del archivo
    public void guardar(List<HuespedDTO> datos) {
        new File("data").mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (HuespedDTO h : datos) {
                pw.println(h.getId()+"|"+h.getNombre()+"|"+h.getApellido()+"|"+h.getCorreo()+"|"+h.getTelefono());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar huespedes: " + e.getMessage());
        }
    }
}
