package co.edu.uniquindio.bookyourstay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ciudad {
    ARMENIA("Armenia"),
    BARRANQUILLA("Barranquilla"),
    BOGOTA("Bogotá"),
    MEDELLIN("Medellín"),
    PEREIRA("Pereira");

    private String nombre;

    @Override
    public String toString() {
        return nombre;
    }
}
