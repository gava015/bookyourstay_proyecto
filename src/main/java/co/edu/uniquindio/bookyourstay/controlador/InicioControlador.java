package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InicioControlador implements Initializable {

    @FXML
    private ComboBox<TipoAlojamiento> cbTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> colNombre;

    @FXML
    private TableColumn<Alojamiento, String> colCiudad;

    @FXML
    private TableColumn<Alojamiento, String> colDescripcion;

    @FXML
    private TableColumn<Alojamiento, String> colPrecioNoche;

    @FXML
    private TableColumn<Alojamiento, String> colCapacidad;

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    private ObservableList<Alojamiento> observableList;

    private final ControladorPrincipal controladorPrincipal;


    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarLogin("/ventanaLogin.fxml", "Panel login", false);
    }

    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaRegistro.fxml", "Registro Persona", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCuidad().getNombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colPrecioNoche.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioPorNoche())));
        colCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidadMax())));
        cbTipoAlojamiento.setItems(FXCollections.observableArrayList(TipoAlojamiento.values()));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaAlojamientos.setItems(observableList);
        actualizarTabla();
    }

    public void actualizarTabla() {
        observableList.setAll(controladorPrincipal.obtenerListaAlojamientos());
    }

}
