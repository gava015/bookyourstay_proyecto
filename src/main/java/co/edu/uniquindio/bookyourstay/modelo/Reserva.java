package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.EstadoReserva;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Reserva {
    private String id;
    private Cliente cliente;
    private Alojamiento alojamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int numeroHuespedes;
    private double costoReserva;
    private EstadoReserva estado;

    public void calcularCostoReserva() {
        this.costoReserva = alojamiento.calcularValorTotal(fechaInicio, fechaFinal, numeroHuespedes);;
    }
}
