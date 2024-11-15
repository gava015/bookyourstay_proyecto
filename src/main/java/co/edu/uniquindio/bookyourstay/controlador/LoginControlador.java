package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.modelo.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginControlador {

    @FXML
    public TextField txtCodigoActivacion;
    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasenia;

    private final ControladorPrincipal controladorPrincipal;

    private boolean mostrarActivacion;

    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    /*public void ingresarUsuario(ActionEvent actionEvent) {
        try {
            String correo = txtCorreo.getText();
            String contrasenia = txtContrasenia.getText();

            Usuario usuario = controladorPrincipal.ingresarUsuario(correo, contrasenia);
            Sesion.getInstancia().setUsuario(usuario);

            if (usuario instanceof Administrador) {
                controladorPrincipal.navegarVentana("/panelInicioAdministrador.fxml", "Panel Administrador", null);
            } else if (usuario instanceof Cliente) {
                controladorPrincipal.navegarVentana("/panelInicioCliente.fxml", "Panel Usuario", null);
            } else {
                AlertaUtil.mostrarAlerta("Tipo de usuario no reconocido", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("El usuario no existe o la contraseña es incorrecta", Alert.AlertType.ERROR);
        }
    }

     */

    public void ingresarUsuario(ActionEvent actionEvent) {

        try {
            String correo = txtCorreo.getText();
            String contrasenia = txtContrasenia.getText();

            Usuario usuario = controladorPrincipal.ingresarUsuario(correo,contrasenia);
            Sesion.getInstancia().setUsuario(usuario);

            if (usuario instanceof Cliente) {
                controladorPrincipal.navegarVentana("/panelInicioCliente.fxml", "Panel Usuario", null);
            } else {
                if(usuario instanceof Administrador) {
                    controladorPrincipal.navegarVentana("/panelInicioAdministrador.fxml", "Panel Administrador", null);
                }
            }

            controladorPrincipal.cerrarVentana(txtCorreo);

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void setMostrarActivacion(boolean mostrarActivacion) {
        this.mostrarActivacion = mostrarActivacion;
        txtCodigoActivacion.setVisible(mostrarActivacion);
    }
}
