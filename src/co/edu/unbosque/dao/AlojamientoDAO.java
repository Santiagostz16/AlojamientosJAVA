package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.AlojamientoDTO;

public interface AlojamientoDAO {
    // devuelvo los alojamientos guardados como objetos de transferencia
    List<AlojamientoDTO> cargar();
    // reemplazo el contenido persistido por la lista actual
    void guardar(List<AlojamientoDTO> datos);
}
