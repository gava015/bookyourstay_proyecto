package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Servicio;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class  Alojamiento {
    protected String id;
    protected String nombre;
    protected String cuidad;
    protected String descripcion;
    protected String urlImagen;
    protected double precioPorNoche;
    protected int capacidadMax;
    protected Double costoAseoMantenimiento;
    protected List<Servicio> listaServicios;

    protected List<Oferta> listaOfertas;

    protected List<Reseña> listaReseñas;

    abstract double calcularValorTotal(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes);
}