package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Hotel extends Alojamiento {

    private List<Habitacion> listaHabitaciones;

    public Hotel(String id, String nombre, String ciudad, String descripcion, String urlImagen, double precioPorNoche,
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

    public Habitacion crearHabitacion(String numero, Double precio, int capacidad, String urlImagen, String descripcion) {
        Habitacion habitacion = Habitacion.builder()
                .id(UUID.randomUUID().toString())
                .numero(numero)
                .precio(precio)
                .capacidad(capacidad)
                .urlImagen(urlImagen)
                .descripcion(descripcion)
                .build();

        listaHabitaciones.add(habitacion);
        return habitacion;
    }

    @Override
    double calcularValorTotal(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes) {
        return 0;
    }

    @Override
    public TipoAlojamiento obtenerTipoAlojamiento() {
        return TipoAlojamiento.HOTEL;
    }
}