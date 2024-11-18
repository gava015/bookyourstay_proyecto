package co.edu.uniquindio.bookyourstay.controlador.panelClienteControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PanelBilleteraControlador  {

    @FXML
    TextField txtValor;

    private ControladorPrincipal controladorPrincipal;

    public PanelBilleteraControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void recargarBilletera(ActionEvent actionEvent) {

        String valor = txtValor.getText();

        try {
            controladorPrincipal.recargarBilletera(Sesion.getInstancia().getUsuario().getIdentificacion(), valor);
            AlertaUtil.mostrarAlerta("Billetera recargada", Alert.AlertType.INFORMATION);
            txtValor.clear();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
