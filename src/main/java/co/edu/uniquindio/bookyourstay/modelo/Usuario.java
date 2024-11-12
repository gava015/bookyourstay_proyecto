package co.edu.uniquindio.bookyourstayco.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    protected String identificacion;
    protected String nombre;
    protected String telefono;
    protected String correo;
    protected String contrasenia;
}

