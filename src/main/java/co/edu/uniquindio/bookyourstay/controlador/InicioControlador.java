package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioControlador implements Initializable {

    @FXML
    private ComboBox<TipoAlojamiento> cbTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento,String> colNombre;

    @FXML
    private TableColumn<Alojamiento,String> colCiudad;

    @FXML
    private TableColumn<Alojamiento,String> colDescripcion;

    @FXML
    private TableColumn<Alojamiento,String> colPrecioNoche;

    @FXML
    private TableColumn<Alojamiento,String> colCapacidad;


    private final ControladorPrincipal controladorPrincipal;


    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaLogin.fxml","Iniciar Sesión", null);
    }

    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaRegistro.fxml", "Registro Persona", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCuidad()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colPrecioNoche.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));

        cbTipoAlojamiento.setItems(FXCollections.observableArrayList(TipoAlojamiento.values()));

    }
}
