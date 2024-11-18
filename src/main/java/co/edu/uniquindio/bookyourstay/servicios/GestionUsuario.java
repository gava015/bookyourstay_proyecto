package co.edu.uniquindio.bookyourstay.servicios;


import co.edu.uniquindio.bookyourstay.modelo.Usuario;

public interface GestionUsuario {

    Usuario ingresarUsuario(String correo, String contrasenia) throws Exception;

    Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia, String codigoConfirmacionCuenta) throws Exception;
}

