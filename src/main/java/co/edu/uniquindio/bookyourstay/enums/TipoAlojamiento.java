package co.edu.uniquindio.bookyourstay.enums;

public enum TipoAlojamiento {
    CASA,
    APARTAMENTO,
    HOTEL;

    @Override
    public String toString() {
        switch (this) {
            case HOTEL: return "Hotel";
            case CASA: return "Casa";
            case APARTAMENTO: return "Apartamento";
            default: return super.toString();
        }
    }
}