package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import co.edu.uniquindio.bookyourstay.util.EnvioEmail;
import co.edu.uniquindio.bookyourstay.util.RandomUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

        try {
            String codigoConfirmacionCuenta = String.valueOf(RandomUtil.generarCodigoAleatorio());
            Usuario usuario = controladorPrincipal.registrarUsuario(identificacion, nombre, telefono, correo, contrasenia, codigoConfirmacionCuenta);
            EnvioEmail.enviarNotificacion(
                    usuario.getCorreo(), "Código de activación", "Tu código de activación es: " + codigoConfirmacionCuenta);

            controladorPrincipal.navegarLogin("/ventanaLogin.fxml", "Panel login", true);
            AlertaUtil.mostrarAlerta("Usuario registrado con éxito", Alert.AlertType.INFORMATION);

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
