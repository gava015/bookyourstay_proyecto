package co.edu.uniquindio.bookyourstay.controlador.panelClienteControlador;

import co.edu.uniquindio.bookyourstay.controlador.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelGestionReservaControlador implements Initializable {

    @FXML
    private TableColumn<Reserva, String> colAlojamiento;

    @FXML
    private TableColumn<Reserva, String> colCiudad;

    @FXML
    private TableColumn<Reserva, String> colPrecio;

    @FXML
    private TableColumn<Reserva, String> colFechaInicio;

    @FXML
    private TableColumn<Reserva, String> colFechaFin;

    @FXML
    private TableView<Reserva> tablaReservas;

    private ObservableList<Reserva> observableList;

    private ControladorPrincipal controladorPrincipal;

    public PanelGestionReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getCuidad().getNombre()));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCostoReserva())));
        colFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaInicio())));
        colFechaFin.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaFinal())));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaReservas.setItems(observableList);
        actualizarTabla();
    }

    public void cancelarReserva(ActionEvent actionEvent) {
        Reserva reserva = tablaReservas.getSelectionModel().getSelectedItem();
        if (reserva == null){
            AlertaUtil.mostrarAlerta("Por favor, selecciona una reserva antes de continuar.", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            controladorPrincipal.cancelarReserva(reserva.getId());
            AlertaUtil.mostrarAlerta("Reserva cancelada", Alert.AlertType.INFORMATION);
            actualizarTabla();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("Lo sentimos, no hemos podido cancelar tu reserva", Alert.AlertType.ERROR);
        }
    }

    public void actualizarTabla() {
        try {
            observableList.setAll(controladorPrincipal.obtenerReservasPorCliente(Sesion.getInstancia().getUsuario().getIdentificacion()));
        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
