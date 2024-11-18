package co.edu.uniquindio.bookyourstay.controlador.panelClienteControlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


public class PanelInicioClienteControlador {

    @FXML
    private StackPane panelPrincipal;

    public void mostrarConfiguracion(ActionEvent actionEvent) {
        Parent node = cargarPanel("/panelConfiguracionCliente.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarGestionReserva(ActionEvent actionEvent) {
       Parent node = cargarPanel("/panelGestionReserva.fxml");
       panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarCrearReserva(ActionEvent actionEvent) {
        Parent node = cargarPanel("/panelReservaCliente.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarRecargarBilletera(ActionEvent actionEvent) {
        Parent node = cargarPanel("/panelBilleteraCliente.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();

            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
