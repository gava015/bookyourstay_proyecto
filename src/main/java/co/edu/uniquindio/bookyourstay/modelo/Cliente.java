package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Getter;

public class Cliente extends Usuario {

    @Getter
    private BilleteraVirtual billeteraVirtual;

    @Builder
    public Cliente(String identificacion, String nombre, String telefono, String correo, String contrasenia, boolean estado,
                   String codigoConfirmacionCuenta) {
        super(identificacion, nombre, telefono, correo, contrasenia, estado, codigoConfirmacionCuenta);
        billeteraVirtual = new BilleteraVirtual(0);
    }

    @Override
    public String toString() {
        return "identificacion: " + identificacion + "\n" +
                "Cliente: " + nombre + "\n" +
                "Correo='" + correo + "\n";
    }
}

