package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PanelAlojamientoControlador implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Ciudad> cbCiudad;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtImagen;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtMantenimiento;

    @FXML
    private ComboBox<TipoAlojamiento> cbTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> colNombre;

    @FXML
    private TableColumn<Alojamiento, String> colCiudad;

    @FXML
    private TableColumn<Alojamiento, String> colDescripcion;

    @FXML
    private TableColumn<Alojamiento, String> colImagen;

    @FXML
    private TableColumn<Alojamiento, String> colPrecio;

    @FXML
    private TableColumn<Alojamiento, String> colCapacidad;

    @FXML
    private TableColumn<Alojamiento, String> colMantenimiento;

    @FXML
    private TableColumn<Alojamiento, String> colAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> colServicios;

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private ListView<Servicio> listViewServicios;

    private ObservableList<Alojamiento> observableList;

    private ControladorPrincipal controladorPrincipal;

    public PanelAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void crearAlojamiento(ActionEvent a) {

        try {

            TipoAlojamiento tipoAlojamiento = cbTipoAlojamiento.getValue();
            String nombre = txtNombre.getText();
            Ciudad ciudad = cbCiudad.getValue();
            String descripcion = txtDescripcion.getText();
            String imagen = txtImagen.getText();
            double precio = txtPrecio.getText().isEmpty() ? 0 : Double.parseDouble(txtPrecio.getText());
            int capacidad = txtCapacidad.getText().isEmpty() ? 0 : Integer.parseInt(txtCapacidad.getText());
            double mantenimiento = txtMantenimiento.getText().isEmpty() ? 0 : Double.parseDouble(txtMantenimiento.getText());
            List<Servicio> listaServicios = listViewServicios.getSelectionModel().getSelectedItems();

            controladorPrincipal.crearAlojamiento(
                    tipoAlojamiento, nombre, ciudad, descripcion, imagen, precio, capacidad, mantenimiento, listaServicios);

            AlertaUtil.mostrarAlerta("El alojamiento ha sido creado con éxito", Alert.AlertType.INFORMATION);
            actualizarTabla();
            limpiarCampos();

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void modificarAlojamiento(ActionEvent a) {

        Alojamiento alojamiento = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamiento == null) {
            AlertaUtil.mostrarAlerta("Debe seleccionar un alojamiento", Alert.AlertType.ERROR);
            return;
        }

        TipoAlojamiento tipoAlojamiento = cbTipoAlojamiento.getValue();
        String nombre = txtNombre.getText();
        Ciudad ciudad = cbCiudad.getValue();
        String descripcion = txtDescripcion.getText();
        String imagen = txtImagen.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        double mantenimiento = Double.parseDouble(txtMantenimiento.getText());
        List<Servicio> listaServicios = listViewServicios.getSelectionModel().getSelectedItems();

        try {
            controladorPrincipal.modificarAlojamiento(alojamiento.getId(), tipoAlojamiento, nombre, ciudad, descripcion, imagen, precio, capacidad, mantenimiento, listaServicios);
            AlertaUtil.mostrarAlerta("El alojamiento ha sido modificado con éxito", Alert.AlertType.INFORMATION);
            actualizarTabla();
            limpiarCampos();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void eliminarAlojamiento(ActionEvent a) {

        Alojamiento alojamiento = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamiento == null) {
            AlertaUtil.mostrarAlerta("Debe seleccionar un alojamiento", Alert.AlertType.ERROR);
            return;
        }

        try {
            controladorPrincipal.eliminarAlojamiento(alojamiento.getId());
            AlertaUtil.mostrarAlerta("El alojamiento ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
            limpiarCampos();
            actualizarTabla();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void gestionarHabitaciones(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/panelHabitacion.fxml", "Gestión Habitaciones", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarImagen();
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCuidad().getNombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colImagen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlImagen()));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty("$" + cellData.getValue().getPrecioPorNoche()));
        colCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidadMax())));
        colMantenimiento.setCellValueFactory(cellData -> new SimpleStringProperty("$" + cellData.getValue().getCostoAseoMantenimiento()));
        colAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipoAlojamiento().toString()));
        colServicios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaServicios().toString()));
        cbTipoAlojamiento.setItems(FXCollections.observableArrayList(TipoAlojamiento.values()));
        cbCiudad.setItems(FXCollections.observableArrayList(Ciudad.values()));
        listViewServicios.setItems(FXCollections.observableArrayList(Servicio.values()));
        listViewServicios.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        tablaAlojamientos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            obtenerDatosFilaSeleccionada();
        });

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaAlojamientos.setItems(observableList);
        actualizarTabla();
    }

    public void cargarImagen() {
        colImagen.setCellValueFactory(new PropertyValueFactory<>("urlImagen"));
        colImagen.setCellFactory(cell -> new TableCell<>() {
            final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                if (s != null && Pattern.matches("^(https?|ftp)://[^\s/$.?#].[^\s]*$", s)) {
                    Image image = new Image(s, 50, 50, true, true);
                    imageView.setImage(image);
                    setGraphic(imageView);
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    private void obtenerDatosFilaSeleccionada() {
        Alojamiento alojamiento = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamiento != null) {
            cbTipoAlojamiento.setValue(alojamiento.obtenerTipoAlojamiento());
            txtNombre.setText(alojamiento.getNombre());
            txtCapacidad.setText(String.valueOf(alojamiento.getCapacidadMax()));
            txtDescripcion.setText(alojamiento.getDescripcion());
            txtImagen.setText(alojamiento.getUrlImagen());
            txtPrecio.setText(String.valueOf(alojamiento.getPrecioPorNoche()));
            txtMantenimiento.setText(String.valueOf(alojamiento.getCostoAseoMantenimiento()));
            cbCiudad.setValue(alojamiento.getCuidad());

            for (Servicio servicio : alojamiento.getListaServicios()) {
                listViewServicios.getSelectionModel().select(servicio);
            }

        } else {
            limpiarCampos();
        }
    }

    public void actualizarTabla() {
        observableList.setAll(controladorPrincipal.obtenerListaAlojamientos());
    }

    public void limpiarCampos() {
        txtNombre.clear();
        txtCapacidad.clear();
        txtDescripcion.clear();
        txtImagen.clear();
        txtPrecio.clear();
        txtMantenimiento.clear();
        cbCiudad.setValue(null);
        cbTipoAlojamiento.setValue(null);
        listViewServicios.getSelectionModel().clearSelection();
    }
}
