package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
    private String id;
    private String numero;
    private Double precio;
    private int capacidad;
    private String urlImagen;
    private String descripcion;

}
