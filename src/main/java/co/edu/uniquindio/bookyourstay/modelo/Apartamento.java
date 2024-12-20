package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;

import java.time.LocalDate;
import java.util.List;

public class Apartamento extends Alojamiento {

    public Apartamento(String id, String nombre, Ciudad ciudad, String descripcion, String urlImagen, double precioPorNoche,
                       int capacidadMax, Double costoAseoMantenimiento, List<Servicio> listaServicios) {
        this.id = id;
        this.nombre = nombre;
        this.cuidad = ciudad;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.precioPorNoche = precioPorNoche;
        this.capacidadMax = capacidadMax;
        this.costoAseoMantenimiento = costoAseoMantenimiento;
        this.listaServicios = listaServicios;
    }

    @Override
    double calcularValorTotal(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes) {
        return 0;
    }

    @Override
    public TipoAlojamiento obtenerTipoAlojamiento() {
        return TipoAlojamiento.APARTAMENTO;
    }
}


