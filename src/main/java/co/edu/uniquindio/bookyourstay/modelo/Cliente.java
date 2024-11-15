package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Getter;

public class Cliente extends Usuario {

    @Getter
    private BilleteraVirtual billeteraVirtual;

    @Builder
    public Cliente(String identificacion, String nombre, String telefono, String correo, String contrasenia, boolean estado) {
        super(identificacion, nombre, telefono, correo, contrasenia, estado);
        billeteraVirtual = new BilleteraVirtual(0);
    }
}

