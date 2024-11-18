package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.modelo.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.util.AlertaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private boolean esActivo;

    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void ingresarUsuario(ActionEvent actionEvent) {

        try {
            String correo = txtCorreo.getText();
            String contrasenia = txtContrasenia.getText();

            Usuario usuario = controladorPrincipal.ingresarUsuario(correo, contrasenia);
            if (verificarCodigoActivacion(usuario)) {
                AlertaUtil.mostrarAlerta("El código ingresado no es correcto", Alert.AlertType.ERROR);
                txtCodigoActivacion.clear();

            } else {

                Sesion.getInstancia().setUsuario(usuario);
                if (usuario instanceof Cliente) {
                    controladorPrincipal.navegarVentana("/panelInicioCliente.fxml", "Panel Usuario", null);
                } else if (usuario instanceof Administrador) {
                    controladorPrincipal.navegarVentana("/panelInicioAdministrador.fxml", "Panel Administrador", null);
                }

                controladorPrincipal.cerrarVentana(txtCorreo);
            }

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void irVentanaInicio(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaInicio.fxml", "Book Your Stay", null);
    }

    private boolean verificarCodigoActivacion(Usuario usuario) throws Exception {
        if (esActivo) {
            String codigo = txtCodigoActivacion.getText();
            return !usuario.esCodigoValido(codigo);
        }

        return false;
    }

    public void mostrarCampoConfirmacionCuenta(boolean esActivo) {
        this.esActivo = esActivo;
        txtCodigoActivacion.setVisible(esActivo);
    }
}
