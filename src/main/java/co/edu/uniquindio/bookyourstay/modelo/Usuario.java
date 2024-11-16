package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    protected String identificacion;
    protected String nombre;
    protected String telefono;
    protected String correo;
    protected String contrasenia;
    protected boolean estado;

}

