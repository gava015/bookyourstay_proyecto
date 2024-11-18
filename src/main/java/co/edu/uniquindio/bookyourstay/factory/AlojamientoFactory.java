package co.edu.uniquindio.bookyourstay.factory;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Apartamento;
import co.edu.uniquindio.bookyourstay.modelo.Casa;
import co.edu.uniquindio.bookyourstay.modelo.Hotel;
import co.edu.uniquindio.bookyourstay.servicios.Fabricante;

import java.util.List;

public class AlojamientoFactory implements Fabricante {

    @Override
    public Alojamiento crearAlojamiento(String id, TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion,
                                        String urlImagen, double precioPorNoche, int capacidadMax, Double costoAseoMantenimiento,
                                        List<Servicio> listaServicios) throws Exception {
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

