package co.edu.uniquindio.bookyourstayco.modelo;

import lombok.Getter;

public class Cliente extends Usuario {

    @Getter
    private BilleteraVirtual billeteraVirtual;

    public Cliente(String identificacion, String nombre, String telefono, String correo, String contrasenia) {
        super(identificacion, nombre, telefono, correo, contrasenia);
        billeteraVirtual = new BilleteraVirtual(0);
    }
}

