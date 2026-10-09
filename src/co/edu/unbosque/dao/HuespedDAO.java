package co.edu.unbosque.dao;

import java.util.List;
import co.edu.unbosque.dto.HuespedDTO;

public interface HuespedDAO {
    List<HuespedDTO> cargar();
    void guardar(List<HuespedDTO> datos);
}
