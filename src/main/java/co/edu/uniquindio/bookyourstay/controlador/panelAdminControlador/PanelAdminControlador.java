package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


public class PanelAdminControlador {

    @FXML
    private StackPane panelPrincipal;


    public void mostrarGestionAlojamiento(ActionEvent actionEvent) {
        Parent node = cargarPanel("/panelAlojamiento.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarGestionOferta(ActionEvent actionEvent) {
        Parent node = cargarPanel("/panelOferta.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarReportes(ActionEvent actionEvent) {

        Parent node = cargarPanel("/panelReporte.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            //((AbstractControlador) loader.getController()).inicializarClinica(clinica);


            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
