package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.observador.Observador;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelAlojamientoControlador implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbCuidad;

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
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private TableColumn<Alojamiento,String> colNombre;

    @FXML
    private TableColumn<Alojamiento,String> colCiudad;

    @FXML
    private TableColumn<Alojamiento,String> colDescripcion;

    @FXML
    private TableColumn<Alojamiento,String> colImagen;

    @FXML
    private TableColumn<Alojamiento, String> colPrecio;

    @FXML
    private TableColumn<Alojamiento, String> colCapacidad;

    @FXML
    private TableColumn<Alojamiento,String> colMantenimiento;

    private Observador observador;

    private ControladorPrincipal controladorPrincipal;

    public PanelAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void crearAlojamiento (ActionEvent a){
        String nombre = txtNombre.getText();
        String ciudad = cbCuidad.getValue();
        String descripcion = txtDescripcion.getText();
        String imagen = txtImagen.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        double mantenimiento = Double.parseDouble(txtMantenimiento.getText());
        TipoAlojamiento tipoAlojamiento = cbTipoAlojamiento.getValue();

        try {
           //controladorPrincipal.crearAlojamiento(tipoAlojamiento,nombre,ciudad,descripcion,imagen,precio,capacidad,mantenimiento,listaServicios);
            AlertaUtil.mostrarAlerta("Cita creada con éxito", Alert.AlertType.INFORMATION);
            //limpiarFormularioAlojamiento();

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);

        }
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCuidad()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colImagen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlImagen()));
        //colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get()));
        //colCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCapacidadMax()));
        //colMantenimiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCostoAseoMantenimiento()));

        /*observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaAlojamientos.setItems(observableList);

         */
        actualizarTabla();

    }

    public void actualizarTabla() {

    }
}
