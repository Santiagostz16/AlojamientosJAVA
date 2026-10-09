package co.edu.unbosque.mapper;

import co.edu.unbosque.dto.*;
import co.edu.unbosque.model.*;

public class DataMapper {

    public AlojamientoDTO alojamientoToDTO(Alojamiento a) {
        return new AlojamientoDTO(a.getId(),a.getNombre(),a.getCiudad(),a.getUbicacion(),
                a.getCapacidad(),a.getPrecioNoche(),a.isActivo(),a.getDescripcion(),a.getTipo());
    }

    public Alojamiento dtoToAlojamiento(AlojamientoDTO d) {
        if (d.getTipo().equalsIgnoreCase("Apartamento"))
            return new Apartamento(d.getId(),d.getNombre(),d.getCiudad(),d.getUbicacion(),d.getCapacidad(),d.getPrecioNoche(),d.isActivo(),d.getDescripcion());
        if (d.getTipo().equalsIgnoreCase("Casa"))
            return new Casa(d.getId(),d.getNombre(),d.getCiudad(),d.getUbicacion(),d.getCapacidad(),d.getPrecioNoche(),d.isActivo(),d.getDescripcion());
        return new Cabana(d.getId(),d.getNombre(),d.getCiudad(),d.getUbicacion(),d.getCapacidad(),d.getPrecioNoche(),d.isActivo(),d.getDescripcion());
    }

    public HuespedDTO huespedToDTO(Huesped h) {
        return new HuespedDTO(h.getId(),h.getNombre(),h.getApellido(),h.getCorreo(),h.getTelefono());
    }

    public Huesped dtoToHuesped(HuespedDTO d) {
        return new Huesped(d.getId(),d.getNombre(),d.getApellido(),d.getCorreo(),d.getTelefono());
    }

    public ReservaDTO reservaToDTO(Reserva r) {
        return new ReservaDTO(r.getId(),r.getHuesped().getId(),r.getAlojamiento().getId(),
                r.getFechaLlegada(),r.getFechaSalida(),r.getNumeroHuespedes(),r.getValorTotal(),r.getEstado());
    }
}
