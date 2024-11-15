package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


public class PanelInicioControlador {

    @FXML
    private StackPane panelPrincipal;
    private ControladorPrincipal controladorPrincipal;

    public PanelInicioControlador(){
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void mostrarGestionAlojamiento(ActionEvent actionEvent) {
        Parent node = controladorPrincipal.cargarPanel("/panelAlojamientoAdmin.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarGestionOferta(ActionEvent actionEvent) {
        Parent node = controladorPrincipal.cargarPanel("/panelOfertaAdmin.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarReportes(ActionEvent actionEvent) {

        Parent node = controladorPrincipal.cargarPanel("/panelReporteAdmin.fxml");

        panelPrincipal.getChildren().setAll(node);
    }
}
