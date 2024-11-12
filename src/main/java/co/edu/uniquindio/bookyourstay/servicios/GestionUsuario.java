package co.edu.uniquindio.bookyourstayco.servicios;


import co.edu.uniquindio.bookyourstayco.modelo.Usuario;

public interface GestionUsuario {

    Usuario ingresarUsuario(String correo, String contrasenia) throws Exception;

    Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia) throws Exception;
}

