package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.Hotel;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelHabitacionControlador implements Initializable {

    @FXML
    private TextField txtNumHabitacion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtUrlImagen;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private ComboBox<Alojamiento> cbHotel;

    @FXML
    private TableView<Habitacion> tablaHabitaciones;

    @FXML
    private TableColumn<Habitacion, String> colNumero;

    @FXML
    private TableColumn<Habitacion, String> colPrecio;

    @FXML
    private TableColumn<Habitacion, String> colCapacidad;

    @FXML
    private TableColumn<Habitacion, String> colImagen;

    @FXML
    private TableColumn<Habitacion, String> colDescripcion;

    private ControladorPrincipal controladorPrincipal;

    private ObservableList<Habitacion> observableList;

    public PanelHabitacionControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNumero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumero()));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        colCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidad())));
        colImagen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlImagen()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));

        cbHotel.setItems(FXCollections.observableArrayList(controladorPrincipal.obtenerAlojamientosPorTipo(TipoAlojamiento.HOTEL)));
        tablaHabitaciones.getSelectionModel().selectedItemProperty().addListener((observable, anteriorValor, nuevoValor) -> obtenerDatosFilaSeleccionada());
        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaHabitaciones.setItems(observableList);
        configurarComboBoxHotel();
    }

    public void crearHabitacion(ActionEvent actionEvent) {
        Hotel hotel = (Hotel) cbHotel.getSelectionModel().getSelectedItem();
        String numero = txtNumHabitacion.getText();
        String precioPorNoche = txtPrecio.getText();
        String capacidad = txtCapacidad.getText();
        String urlImagen = txtUrlImagen.getText();
        String descripcion = txtDescripcion.getText();

        try {
            controladorPrincipal.crearHabitacion(hotel.getId(), numero, precioPorNoche, capacidad, urlImagen, descripcion);
            AlertaUtil.mostrarAlerta("La habitación ha sido creada con éxito", Alert.AlertType.INFORMATION);
            actualizarTabla(hotel.getId());
            limpiarCampos();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void modificarHabitacion(ActionEvent actionEvent) {
        Habitacion habitacion = tablaHabitaciones.getSelectionModel().getSelectedItem();
        Hotel hotel = (Hotel) cbHotel.getValue();
        String numeroHabitacion = txtNumHabitacion.getText();
        String precioPorNoche = txtPrecio.getText();
        String capacidad = txtCapacidad.getText();
        String urlImagen = txtUrlImagen.getText();
        String descripcion = txtDescripcion.getText();

        try {
            controladorPrincipal.modificarHabitacion(
                    habitacion.getId(), hotel.getId(), numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);
            AlertaUtil.mostrarAlerta("La habitación ha sido modificada con éxito", Alert.AlertType.INFORMATION);
            actualizarTabla(hotel.getId());
            limpiarCampos();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void eliminarHabitacion(ActionEvent actionEvent) {
        Habitacion habitacion = tablaHabitaciones.getSelectionModel().getSelectedItem();
        //controladorPrincipal.eliminarHabitacion(habitacion.getId());
    }

    private void obtenerDatosFilaSeleccionada() {
        Habitacion habitacion = tablaHabitaciones.getSelectionModel().getSelectedItem();
        if (habitacion != null) {
            txtNumHabitacion.setText(habitacion.getNumero());
            txtPrecio.setText(String.valueOf(habitacion.getPrecio()));
            txtCapacidad.setText(String.valueOf(habitacion.getCapacidad()));
            txtUrlImagen.setText(habitacion.getUrlImagen());
            txtDescripcion.setText(habitacion.getDescripcion());

        } else {
            limpiarCampos();
        }
    }

    private void actualizarTabla(String hotelId) {
        if (hotelId != null) {
            try {
                observableList.setAll(controladorPrincipal.obtenerHabitacionesHotel(hotelId));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            observableList.clear();
        }
    }

    private void configurarComboBoxHotel() {
        if (!cbHotel.getItems().isEmpty()) {
            Hotel hotel = (Hotel) cbHotel.getItems().get(0);
            cbHotel.setValue(hotel);
            actualizarTabla(hotel.getId());
        }

        cbHotel.valueProperty().addListener((observable, anterior, nuevo) -> actualizarTabla(cbHotel.getSelectionModel().getSelectedItem().getId()));
    }

    private void limpiarCampos() {
        txtNumHabitacion.clear();
        txtPrecio.clear();
        txtCapacidad.clear();
        txtUrlImagen.clear();
        txtDescripcion.clear();
    }
}

