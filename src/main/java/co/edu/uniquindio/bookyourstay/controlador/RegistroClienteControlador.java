package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.modelo.ServicioReserva;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import co.edu.uniquindio.bookyourstay.util.EnvioEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Random;


public class RegistroClienteControlador {

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;


    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;


    @FXML
    private TextField txtContrasenia;

    private ControladorPrincipal controladorPrincipal;


    public RegistroClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void registrarUsuario(ActionEvent actionEvent) throws Exception {

        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String contrasenia = txtContrasenia.getText();

        String codigoActivacion = generarCodigoAleatorio();

        try {
            controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
            controladorPrincipal.registrarUsuario(identificacion, nombre,telefono, correo, contrasenia);

            //EnvioEmail.enviarNotificacion(correo, "Código de activación", "Tu código de activación es: " + codigoActivacion);

            controladorPrincipal.navegarVentana("/ventanaLogin.fxml", "Panel login", null);
            AlertaUtil.mostrarAlerta("Usuario registrado con éxito", Alert.AlertType.INFORMATION);

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private String generarCodigoAleatorio() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
}
