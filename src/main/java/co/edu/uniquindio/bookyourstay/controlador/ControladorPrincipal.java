package co.edu.uniquindio.bookyourstayco.controlador;

import co.edu.uniquindio.bookyourstayco.modelo.ServicioReserva;
import co.edu.uniquindio.bookyourstayco.modelo.Usuario;
import co.edu.uniquindio.bookyourstayco.observador.Observador;
import co.edu.uniquindio.bookyourstayco.observador.VentanaObservable;
import co.edu.uniquindio.bookyourstayco.servicios.Gestion;
import co.edu.uniquindio.bookyourstayco.servicios.GestionUsuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ControladorPrincipal implements GestionUsuario, Gestion {

    private static ControladorPrincipal INSTANCIA;


    private ServicioReserva servicioReserva;


    public ControladorPrincipal() {
        servicioReserva = new ServicioReserva();
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana, Observador observador) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            if (observador != null) {
                VentanaObservable ventanaObservable = loader.getController();
                ventanaObservable.setObservador(observador);
            }

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario ingresarUsuario(String correo, String contrasenia) throws Exception {
        return servicioReserva.ingresarUsuario(correo, contrasenia);
    }

    @Override
    public Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia) throws Exception {
        return servicioReserva.registrarUsuario(identificacion, nombre, telefono, correo, contrasenia);
    }
}