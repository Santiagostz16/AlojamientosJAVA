package co.edu.unbosque.dao;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.dto.ReservaDTO;

public class ArchivoReservaDAO implements ReservaDAO {
    private final String archivo = "data/reservas.txt";

    @Override
    public List<ReservaDTO> cargar() {
        List<ReservaDTO> lista = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] d = linea.split("\\|", -1);
                if (d.length == 8) {
                    try {
                        lista.add(new ReservaDTO(d[0],d[1],d[2],LocalDate.parse(d[3]),LocalDate.parse(d[4]),
                                Integer.parseInt(d[5]),Double.parseDouble(d[6]),d[7]));
                    } catch (RuntimeException e) {
                        System.out.println("Registro de reserva incorrecto: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void guardar(List<ReservaDTO> datos) {
        new File("data").mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (ReservaDTO r : datos) {
                pw.println(r.getId()+"|"+r.getIdHuesped()+"|"+r.getIdAlojamiento()+"|"
                        +r.getFechaLlegada()+"|"+r.getFechaSalida()+"|"+r.getNumeroHuespedes()+"|"
                        +r.getValorTotal()+"|"+r.getEstado());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar reservas: " + e.getMessage());
        }
    }
}
