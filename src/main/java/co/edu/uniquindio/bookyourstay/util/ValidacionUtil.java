package co.edu.uniquindio.bookyourstay.util;

import java.util.regex.Pattern;

public class ValidacionUtil {

    public static void validarCampo(String campo, String nombreCampo) throws Exception {
        if (campo == null || campo.trim().isEmpty()) {
            throw new Exception("El campo " + nombreCampo + " es obligatorio.");
        }
    }

    public static void validarCorreo(String correo) throws Exception{
        if (!Pattern.matches("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}", correo)) {
            throw new Exception("El correo no tiene una estructura correcta");
        }
    }

    public static void validarTelefono(String telefono) throws Exception {
        if (!Pattern.matches("[0-9]*", telefono)) {
            throw new Exception("El teléfono no es válido");

        }
    }
}
