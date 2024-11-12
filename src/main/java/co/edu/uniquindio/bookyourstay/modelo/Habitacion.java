package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Habitacion {
    private String id;
    private String numero;
    private Double precio;
    private int capacidad;
    private String urlImagen;
    private String descripcion;
}
