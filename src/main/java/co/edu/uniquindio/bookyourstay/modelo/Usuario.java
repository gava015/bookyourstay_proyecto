package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
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
    protected String codigoConfirmacionCuenta;

    public boolean esCodigoValido(String codigo) throws Exception {
        if (codigo == null) {
            throw new Exception("Debe ingresar un çódigo de activación válido");
        }

        return this.codigoConfirmacionCuenta.equals(codigo);
    }
}

