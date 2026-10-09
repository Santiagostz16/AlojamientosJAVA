package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.HuespedDTO;

public interface HuespedDAO {
    // devuelvo los huespedes guardados como objetos de transferencia
    List<HuespedDTO> cargar();
    // reemplazo el contenido persistido por la lista actual
    void guardar(List<HuespedDTO> datos);
}
