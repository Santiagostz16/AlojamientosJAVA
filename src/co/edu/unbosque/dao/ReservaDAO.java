package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.ReservaDTO;

public interface ReservaDAO {
    // devuelvo las reservas guardadas como objetos de transferencia
    List<ReservaDTO> cargar();
    // reemplazo el contenido persistido por la lista actual
    void guardar(List<ReservaDTO> datos);
}
