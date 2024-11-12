package co.edu.uniquindio.bookyourstay.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BilleteraVirtual {

    private double valor;

    public void recargarBilletera(double valor){
        this.valor += valor;
    }
}
