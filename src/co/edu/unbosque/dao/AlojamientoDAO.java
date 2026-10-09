package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.AlojamientoDTO;

public interface AlojamientoDAO {
    List<AlojamientoDTO> cargar();
    void guardar(List<AlojamientoDTO> datos);
}
