package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaGestionable {

    Reserva crearReserva(String identificacion, String alojamientoId, LocalDate fechaInicio, LocalDate fechaFinal, String cantidadHuespedes)
            throws Exception;

    List<Reserva> obtenerReservasPorCliente(String identificacion) throws Exception;

    void cancelarReserva(String reservaId) throws Exception;
}
