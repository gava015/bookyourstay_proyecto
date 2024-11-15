package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.ServicioReserva;
import co.edu.uniquindio.bookyourstay.modelo.Sesion;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.observador.Observador;
import co.edu.uniquindio.bookyourstay.observador.VentanaObservable;
import co.edu.uniquindio.bookyourstay.servicios.Gestion;
import co.edu.uniquindio.bookyourstay.servicios.GestionUsuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;


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


    @Override
    public Usuario ingresarUsuario(String correo, String contrasenia) throws Exception {
        return servicioReserva.ingresarUsuario(correo, contrasenia);
    }

    @Override
    public Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia) throws Exception {
        return servicioReserva.registrarUsuario(identificacion, nombre, telefono, correo, contrasenia);
    }

    @Override
    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String ciudad, String descripcion, String urlImagen, double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) {

        return null;
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
           // stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();

            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void cerrarSesion(){
        Sesion.getInstancia().cerrarSesion();
        navegarVentana("/ventanaLogin.fxml", "Login", null );
    }

    public void navegarLogin(String nombreVentana, String tituloVentana, boolean mostrarActivacion) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreVentana));
            Parent root = loader.load();

            LoginControlador loginControlador = loader.getController();
            loginControlador.setMostrarActivacion(mostrarActivacion);

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            // stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}