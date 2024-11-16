package co.edu.uniquindio.bookyourstay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Servicio {
    DESAYUNO("Desayuno"),
    WIFI("Wifi"),
    PISCINA("Piscina"),
    GIMNASIO("Gimnasio");

    private String value;

    @Override
    public String toString() {
        return value;
    }
}