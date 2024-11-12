package co.edu.uniquindio.bookyourstay.factory;

import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Apartamento;
import co.edu.uniquindio.bookyourstay.modelo.Casa;
import co.edu.uniquindio.bookyourstay.modelo.Hotel;

import java.util.List;
import java.util.UUID;

public class AlojamientoFactory {

    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String ciudad, String descripcion,
                                        String urlImagen, double precioPorNoche, int capacidadMax,
                                        Double costoAseoMantenimiento, List<Servicio> listaServicios) throws Exception {

        String id = UUID.randomUUID().toString();
        switch (tipoAlojamiento) {
            case CASA:
                return new Casa(id, nombre, ciudad, descripcion, urlImagen, precioPorNoche, capacidadMax, costoAseoMantenimiento, listaServicios);
            case APARTAMENTO:
                return new Apartamento(id, nombre, ciudad, descripcion, urlImagen, precioPorNoche, capacidadMax, costoAseoMantenimiento, listaServicios);
            case HOTEL:
                return new Hotel(id, nombre, ciudad, descripcion, urlImagen, precioPorNoche, capacidadMax, costoAseoMantenimiento, listaServicios);
        }

        throw new Exception("El tipo de alojamiento es incorrecto.");
    }
}

