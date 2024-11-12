package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Reseña {
    private Cliente cliente;
    private String comentario;
    private int calificaciónServicio;
}