package co.edu.uniquindio.bookyourstayco.controlador;

import co.edu.uniquindio.bookyourstayco.modelo.Administrador;
import co.edu.uniquindio.bookyourstayco.modelo.Cliente;
import co.edu.uniquindio.bookyourstayco.modelo.Sesion;
import co.edu.uniquindio.bookyourstayco.modelo.Usuario;
import co.edu.uniquindio.bookyourstayco.util.AlertaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginControlador {

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasenia;

    private final ControladorPrincipal controladorPrincipal;

    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void ingresarUsuario(ActionEvent actionEvent) {
        try {
            String correo = txtCorreo.getText();
            String contrasenia = txtContrasenia.getText();

            Usuario usuario = controladorPrincipal.ingresarUsuario(correo, contrasenia);
            Sesion.getInstancia().setUsuario(usuario);

            if (usuario instanceof Administrador) {
                controladorPrincipal.navegarVentana("/ventanaAdmin.fxml", "Panel Administrador", null);
            } else if (usuario instanceof Cliente) {
                controladorPrincipal.navegarVentana("/ventanaUsuario.fxml", "Panel Usuario", null);
            } else {
                AlertaUtil.mostrarAlerta("Tipo de usuario no reconocido", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("El usuario no existe o la contraseña es incorrecta", Alert.AlertType.ERROR);
        }
    }
}
