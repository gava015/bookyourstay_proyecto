package co.edu.uniquindio.bookyourstay.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegistroClienteControlador {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField pfContrasenia;


    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    public void registrarCliente(ActionEvent a) {
        String correo = txtCorreo.getText();
        String contrasenia = pfContrasenia.getText();
        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        try {
            //Clinica.getInstancia().registrarPaciente(cedula,nombre,telefono,correo,suscripcion);
            //AlertaUtil.mostrarAlerta("Cliente registrado con éxito", Alert.AlertType.INFORMATION);
            //limpiarFormularioCliente();

        } catch (Exception ex) {
            //AlertaUtil.mostrarAlerta(ex.getMessage(),Alert.AlertType.ERROR);
        }
    }
}
