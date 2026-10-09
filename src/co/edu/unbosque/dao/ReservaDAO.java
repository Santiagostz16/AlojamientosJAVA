package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.ReservaDTO;

public interface ReservaDAO {
    List<ReservaDTO> cargar();
    void guardar(List<ReservaDTO> datos);
}
