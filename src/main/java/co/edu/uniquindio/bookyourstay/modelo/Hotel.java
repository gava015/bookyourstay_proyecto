package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Hotel extends Alojamiento {

    private List<Habitacion> listaHabitaciones;

    public Hotel(String id, String nombre, Ciudad ciudad, String descripcion, String urlImagen, double precioPorNoche,
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
        listaHabitaciones = new ArrayList<>();
    }

    @Override
    double calcularValorTotal(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes) {
        return 0;
    }

    @Override
    public TipoAlojamiento obtenerTipoAlojamiento() {
        return TipoAlojamiento.HOTEL;
    }

    public Habitacion crearHabitacion(String habitacionId, String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen, String descripcion) {
        Habitacion habitacion = Habitacion.builder()
                .id(habitacionId)
                .numero(numeroHabitacion)
                .precio(Double.parseDouble(precioPorNoche))
                .capacidad(Integer.parseInt(capacidad))
                .urlImagen(urlImagen)
                .descripcion(descripcion)
                .build();

        listaHabitaciones.add(habitacion);
        return habitacion;
    }

    public Habitacion buscarHabitacion(String habitacionId) {
        for (Habitacion habitacion : getListaHabitaciones()) {
            if (habitacion.getId().equals(habitacionId)) {
                return habitacion;
            }
        }
        return null;
    }
}