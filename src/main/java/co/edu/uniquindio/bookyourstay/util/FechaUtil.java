package co.edu.uniquindio.bookyourstayco.util;

import java.time.LocalDate;
import java.time.Period;

public class FechaUtil {

    public static int obtenerDiferenciaEnDias(LocalDate fechaInicio, LocalDate fechaFinal) {
        Period periodo = Period.between(fechaInicio, fechaFinal);
        return periodo.getDays();
    }
}
