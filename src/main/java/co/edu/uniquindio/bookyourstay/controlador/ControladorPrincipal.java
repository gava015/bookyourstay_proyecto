package co.edu.uniquindio.bookyourstay.controlador;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.*;
import co.edu.uniquindio.bookyourstay.observador.Observador;
import co.edu.uniquindio.bookyourstay.observador.VentanaObservable;
import co.edu.uniquindio.bookyourstay.servicios.AlojamientoGestionable;
import co.edu.uniquindio.bookyourstay.servicios.GestionReserva;
import co.edu.uniquindio.bookyourstay.servicios.GestionUsuario;
import co.edu.uniquindio.bookyourstay.servicios.ReservaGestionable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControladorPrincipal implements GestionUsuario, GestionReserva, AlojamientoGestionable, ReservaGestionable {

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
    public Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia, String codigoConfirmacionCuenta) throws Exception {
        return servicioReserva.registrarUsuario(identificacion, nombre, telefono, correo, contrasenia, codigoConfirmacionCuenta);
    }

    @Override
    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion, String urlImagen,
                                        double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception {
        return servicioReserva.crearAlojamiento(tipoAlojamiento, nombre, ciudad, descripcion, urlImagen, precioNoche, capacidadMax, costoMantenimiento, listaServicios);
    }

    @Override
    public Alojamiento modificarAlojamiento(String alojamientoId, TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion,
                                            String urlImagen, double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception {
        return servicioReserva.modificarAlojamiento(alojamientoId, tipoAlojamiento, nombre, ciudad, descripcion, urlImagen, precioNoche, capacidadMax, costoMantenimiento, listaServicios);
    }

    @Override
    public void eliminarAlojamiento(String alojamientoId) throws Exception {
        servicioReserva.eliminarAlojamiento(alojamientoId);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosPorTipo(TipoAlojamiento tipoAlojamiento) {
        return servicioReserva.obtenerAlojamientosPorTipo(tipoAlojamiento);
    }


    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String hotelId) throws Exception {
        return servicioReserva.obtenerHabitacionesHotel(hotelId);
    }

    @Override
    public Habitacion crearHabitacion(String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen,
                                      String descripcion) throws Exception {
        return servicioReserva.crearHabitacion(hotelId, numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);
    }

    @Override
    public void modificarHabitacion(String habitacionId, String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad,
                                    String urlImagen, String descripcion) throws Exception {
        servicioReserva.modificarHabitacion(habitacionId, hotelId, numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);
    }

    @Override
    public void eliminarHabitacion(String hotelId, String habitacionId) throws Exception {
        servicioReserva.eliminarHabitacion(hotelId, habitacionId);
    }

    @Override
    public Reserva crearReserva(String identificacion, String alojamientoId, LocalDate fechaInicio, LocalDate fechaFinal,
                                String cantidadHuespedes) throws Exception {
        return servicioReserva.crearReserva(identificacion, alojamientoId, fechaInicio, fechaFinal, cantidadHuespedes);
    }

    @Override
    public void cancelarReserva(String reservaId) throws Exception {
        servicioReserva.cancelarReserva(reservaId);
    }

    @Override
    public List<Reserva> obtenerReservasPorCliente(String identificacion) throws Exception {
        return servicioReserva.obtenerReservasPorCliente(identificacion);
    }

    public List<Alojamiento> obtenerListaAlojamientos() {
        return servicioReserva.getListaAlojamientos();
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

    public void cerrarSesion() {
        Sesion.getInstancia().cerrarSesion();
        navegarVentana("/ventanaLogin.fxml", "Login", null);
    }

    public void navegarLogin(String nombreVentana, String tituloVentana, boolean mostrarActivacion) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreVentana));
            Parent root = loader.load();

            LoginControlador loginControlador = loader.getController();
            loginControlador.mostrarCampoConfirmacionCuenta(mostrarActivacion);

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