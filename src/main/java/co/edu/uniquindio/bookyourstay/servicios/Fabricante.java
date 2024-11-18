package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;

import java.util.List;

public interface Fabricante {

    Alojamiento crearAlojamiento(String id, TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion,
                                 String urlImagen, double precioPorNoche, int capacidadMax, Double costoAseoMantenimiento,
                                 List<Servicio> listaServicios) throws Exception;
}
