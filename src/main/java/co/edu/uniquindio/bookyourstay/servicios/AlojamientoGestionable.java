package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Habitacion;

import java.util.List;

public interface AlojamientoGestionable {

    Habitacion crearHabitacion(String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen, String descripcion) throws Exception;

    void modificarHabitacion(String habitacionId, String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen, String descripcion) throws Exception;

    void eliminarHabitacion(String hotelId, String habitacionId) throws Exception;

    List<Alojamiento> obtenerAlojamientosPorTipo(TipoAlojamiento tipoAlojamiento);

    List<Habitacion> obtenerHabitacionesHotel(String hotelId) throws Exception;



}
