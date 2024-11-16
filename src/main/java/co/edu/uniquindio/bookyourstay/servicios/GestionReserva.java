package co.edu.uniquindio.bookyourstay.servicios;


import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;

import java.util.List;

public interface GestionReserva {

    Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion, String urlImagen, double precioNoche,
                                 int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception;




  /* Usuario login(String correo, String contrasena) throws Exception;

   void crearApartamento(String nombre, String cuidad, String descripcion, String imagen, double precio, int capacidadMax,
                          List<Servicio> listaServicios);


    void crearCasa(String nombre, String cuidad, String descripcion, String imagen, double precio, int capacidadMax,
                    List<Servicio> listaServicios);

    void crearHotel(String nombre, String cuidad, String descripcion, String imagen, double precio, int capacidadMax,
                    List<Servicio> listaServicios);


    */
}
