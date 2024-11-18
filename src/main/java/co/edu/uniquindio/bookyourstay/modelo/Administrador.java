package co.edu.uniquindio.bookyourstay.modelo;


import lombok.Builder;

public class Administrador extends Usuario {

    @Builder

    public Administrador(String identificacion, String nombre, String telefono, String correo, String contrasenia, boolean estado) {
        super(identificacion, nombre, telefono, correo, contrasenia, estado, null);
    }
}
