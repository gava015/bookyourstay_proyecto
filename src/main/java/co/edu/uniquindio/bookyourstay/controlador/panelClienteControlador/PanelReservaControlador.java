package co.edu.uniquindio.bookyourstay.controlador.panelClienteControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import co.edu.uniquindio.bookyourstay.util.EnvioEmail;
import co.edu.uniquindio.bookyourstay.util.QRUtil;
import com.google.zxing.WriterException;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PanelReservaControlador implements Initializable {

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
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TextField txtCantidadHuespedes;

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private ImageView imageViewQR;

    private ObservableList<Alojamiento> observableList;

    private ControladorPrincipal controladorPrincipal;

    public PanelReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
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

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaAlojamientos.setItems(observableList);
        actualizarTabla();
    }

    public void crearReserva(ActionEvent actionEvent) {
        Alojamiento alojamiento = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamiento == null) {
            AlertaUtil.mostrarAlerta("Debe seleccionar un alojamiento", Alert.AlertType.ERROR);
            return;
        }

        String identificacion = Sesion.getInstancia().getUsuario().getIdentificacion();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFinal = dpFechaFin.getValue();
        String numeroHuespedes = txtCantidadHuespedes.getText();

        try {
            Reserva reserva = controladorPrincipal.crearReserva(identificacion, alojamiento.getId(), fechaInicio, fechaFinal, numeroHuespedes);
            enviarNotificacion(reserva, generarCodigoQR());

            AlertaUtil.mostrarAlerta("Reservaste el alojamiento!", Alert.AlertType.INFORMATION);
            limpiarCampos();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void actualizarTabla() {
        observableList.setAll(controladorPrincipal.obtenerListaAlojamientos());
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

    private void limpiarCampos() {
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        txtCantidadHuespedes.clear();
    }

    private byte[] generarCodigoQR() {
        try {
            BufferedImage lectorImagenQR = QRUtil.generarQR("Gracias por tu reserva!", 300, 300);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ImageIO.write(lectorImagenQR, "PNG", os);

            Image imagen = new Image(new ByteArrayInputStream(os.toByteArray()));
            imageViewQR.setImage(imagen);

            return os.toByteArray();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("No pudimos generar el código QR", Alert.AlertType.ERROR);
            return new byte[0];
        }
    }

    private void enviarNotificacion(Reserva reserva, byte[] salida) {
        Usuario usuario = Sesion.getInstancia().getUsuario();

        String mensaje = reserva.getCliente()
                + "\nInstalación: " + reserva.getAlojamiento().getNombre()
                + "\nFecha: " + reserva.getFechaInicio() + " " + reserva.getFechaFinal()
                + "\nTotal: " + reserva.getCostoReserva();

        EnvioEmail.enviarNotificacionConQR(usuario.getCorreo(), "Gracias por tu reserva", mensaje, salida);
    }
}
