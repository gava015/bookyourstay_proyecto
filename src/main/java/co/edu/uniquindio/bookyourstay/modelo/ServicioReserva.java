package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.factory.AlojamientoFactory;
import co.edu.uniquindio.bookyourstay.servicios.Gestion;
import co.edu.uniquindio.bookyourstay.servicios.GestionUsuario;
import co.edu.uniquindio.bookyourstay.util.EnvioEmail;
import co.edu.uniquindio.bookyourstay.util.ValidacionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Data
@AllArgsConstructor
public class ServicioReserva implements GestionUsuario, Gestion {

    private AlojamientoFactory factory;

    private List<Alojamiento> listaAlojamientos;

    private List<Oferta> listaOfertas;

    private List<Usuario> listaUsuarios;

    private List<Reserva> listaReservas;

    private String codigoVerificacion;

    public ServicioReserva() {
        factory = new AlojamientoFactory();
        listaAlojamientos = new ArrayList<>();
        listaOfertas = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        crearDatosPrueba();
        System.out.println(listaUsuarios);
    }

    public void crearDatosPrueba(){
        try {
            registrarUsuario("123", "usuario", "1212", "usuario@email.com", "1111111");
            crearAdministrador("admin@email.com", "1111111", "1212", "Admin", "5676");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Usuario ingresarUsuario(String correo, String contrasenia) throws Exception {
        ValidacionUtil.validarCampo(correo, "Correo");
        ValidacionUtil.validarCampo(contrasenia, "Contraseña");

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }

        throw new Exception("Datos de acceso incorrectos");
    }

    @Override
    public Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia) throws Exception {
        ValidacionUtil.validarCampo(identificacion, "Identificación");
        ValidacionUtil.validarCampo(nombre, "Nombre");
        ValidacionUtil.validarCampo(correo, "Correo");
        ValidacionUtil.validarCampo(contrasenia, "Contraseña");
        ValidacionUtil.validarCorreo(correo);

        Usuario usuarioBuscado = buscarUsuario(identificacion);
        if (usuarioBuscado != null) {
            throw new Exception("Ya existe un cliente registrado con la identificación: " + identificacion);
        }

        Cliente cliente = Cliente.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .correo(correo)
                .telefono(telefono)
                .contrasenia(contrasenia)
                .estado(false)
                .build();

        listaUsuarios.add(cliente);
        return cliente;
    }

    public Usuario crearAdministrador(String correo, String contrasenia, String identificacion, String nombre, String telefono) throws Exception {
        Usuario usuarioBuscado = buscarUsuario(identificacion);
        if (usuarioBuscado != null) {
            throw new Exception("Ya existe un cliente registrado con la identificación: " + identificacion);
        }

        Administrador administrador = Administrador.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .correo(correo)
                .telefono(telefono)
                .contrasenia(contrasenia)
                .build();

        listaUsuarios.add(administrador);
        return administrador;

    }


    public boolean enviarCodigoVerificacion(String correo) throws Exception {

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                String codigo = generarCodigoAleatorio();
                String mensaje = "Su código de verificación es: " + codigo;
                try {
                    EnvioEmail.enviarNotificacion(correo, "Código de Verificación", mensaje);
                } catch (Exception e) {
                    throw new Exception("Error al enviar el correo de verificación: " + e.getMessage(), e);
                }

                this.codigoVerificacion = codigo;
                return true;
            }
            throw new Exception("El correo proporcionado no coincide con el admiistra");
        }

        return false;

    }

    private String generarCodigoAleatorio() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }


   /* public Usuario crearUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia) throws Exception {
        //TODO: Agregar validaciones


        Usuario usuarioBuscado = buscarUsuario(identificacion);
        if (usuarioBuscado != null) {
            throw new Exception("Ya existe un cliente registrado con la identificacion: " + identificacion);
        }

        Usuario cliente = Usuario.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .correo(correo)
                .telefono(telefono)
                .contrasenia(contrasenia)
                .build();

        listaUsuarios.add(cliente);
        return cliente;
    }

    */

    public Usuario buscarUsuario(String identificacion) {
        for (Usuario usuario : listaUsuarios) {
            if (identificacion.equals(usuario.getIdentificacion())) {
                return usuario;
            }
        }
        return null;
    }



    public void eliminarUsuario(String usuarioId) throws Exception {
        Usuario usuario = buscarUsuario(usuarioId);
        if (usuario == null) {
            throw new Exception("No existe un usuario con el ID: " + usuarioId);
        }

        listaUsuarios.remove(usuario);
    }

    public Oferta crearOferta(String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFinal) throws Exception {

        //TODO: Agregar validaciones

        ValidacionUtil.validarCampo(descripcion, "Descripción");
        Oferta oferta = new Oferta(UUID.randomUUID().toString(), descripcion, descuento, fechaInicio, fechaFinal);
        listaOfertas.add(oferta);
        return oferta;
    }

    public Oferta buscarOferta(String ofertaId) {
        for (Oferta oferta : listaOfertas) {
            if (ofertaId.equals(oferta.getId())) {
                return oferta;
            }
        }
        return null;
    }

    public void eliminarOferta(String ofertaId) throws Exception {
        Oferta oferta = buscarOferta(ofertaId);
        if (oferta == null) {
            throw new Exception("No existe una oferta con el ID " + ofertaId);
        }

        listaOfertas.remove(oferta);
    }

    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String ciudad, String descripcion,
                                        String urlImagen, double precioNoche, int capacidadMax,
                                        Double costoAseoMantenimiento, List<Servicio> listaServicios) throws Exception {

        //TODO: Agregar validaciones
        Alojamiento alojamiento = factory.crearAlojamiento(

                tipoAlojamiento,
                nombre,
                ciudad,
                descripcion,
                urlImagen,
                precioNoche,
                capacidadMax,
                costoAseoMantenimiento,
                listaServicios);

        listaAlojamientos.add(alojamiento);
        return alojamiento;
    }

    public Alojamiento buscarAlojamiento(String alojamientoId) {
        for (Alojamiento alojamiento : listaAlojamientos) {
            if (alojamientoId.equals(alojamiento.getId())) {
                return alojamiento;
            }
        }
        return null;
    }

    public void eliminarAlojamiento(String alojamientoId) throws Exception {
        Alojamiento alojamiento = buscarAlojamiento(alojamientoId);
        if (alojamiento == null) {
            throw new Exception("No existe un alojamiento con el ID " + alojamientoId);
        }

        listaAlojamientos.remove(alojamiento);
    }

    public void crearHabitacion(String alojamientoId, String numero, double precio, int capacidad, String urlImagen, String descripcion) throws Exception {
        //TODO: Agregar validaciones

        Hotel hotel = (Hotel) buscarAlojamiento(alojamientoId);
        if (hotel == null) {
            throw new Exception("No existe un hotel con el ID: " + alojamientoId);
        }

        hotel.crearHabitacion(numero, precio, capacidad, urlImagen, descripcion);
    }

  /*  public Reserva crearReserva(String identificacion, String alojamientoId, LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes) throws Exception {

        Cliente cliente = (Cliente) buscarUsuario(identificacion);
        if (cliente == null) {
            throw new Exception("No existe un cliente con la identificación: " + identificacion);
        }

        Alojamiento alojamiento = buscarAlojamiento(alojamientoId);
        if (alojamiento == null) {
            throw new Exception("No existe un alojamiento con el ID " + alojamientoId);
        }

        if (numeroHuespedes > alojamiento.getCapacidadMax()) {
            throw new Exception("El alojamiento no cuenta con capacidad para el número de huespedes ingresado.");
        }

        //TODO: Agregar validaciones
        Reserva reserva = Reserva.builder()
                .cliente(cliente)
                .alojamiento(alojamiento)
                .fechaInicio(fechaInicio)
                .fechaFinal(fechaFinal)
                .numeroHuespedes(numeroHuespedes)
                .estado(EstadoReserva.CONFIRMADO)
                .build();

        reserva.calcularCostoReserva();
        if (cliente.getBilleteraVirtual().getValor() < reserva.getCostoReserva()) {
            throw new Exception("La billetera virtual no cuenta con fondos suficientes.");
        }

        listaReservas.add(reserva);
        return reserva;
    }

   */

    public Reserva buscarReserva(String reservaId) {
        for (Reserva reserva : listaReservas) {
            if (reservaId.equals(reserva.getId())) {
                return reserva;
            }
        }
        return null;
    }

    public void eliminarReserva(String reservaId) throws Exception {
        Reserva reserva = buscarReserva(reservaId);
        if (reserva == null) {
            throw new Exception("No existe una reserva con el ID " + reservaId);
        }

        listaReservas.remove(reserva);
    }


    public static void main(String[] args) {
        ServicioReserva servicioReserva = new ServicioReserva();

        try {
            Alojamiento casa = servicioReserva.crearAlojamiento(TipoAlojamiento.CASA, "Casa", "Armenia", "Casa", "URL",
                    60.000, 5, 40.000, new ArrayList<>());

            Alojamiento apartamento = servicioReserva.crearAlojamiento(TipoAlojamiento.APARTAMENTO, "Apartamento", "Medellin", "Apartamento", "URL",
                    120.000, 5, 50.000, new ArrayList<>());


            Usuario usuario = servicioReserva.registrarUsuario("1234", "Tefa", "7326132", "t@gmail.com", "123");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String ciudad, String descripcion, String urlImagen,
                                        double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception {
        //TODO: Agregar validaciones
        Alojamiento alojamiento = factory.crearAlojamiento(

                tipoAlojamiento,
                nombre,
                ciudad,
                descripcion,
                urlImagen,
                precioNoche,
                capacidadMax,
                costoMantenimiento,
                listaServicios);

        listaAlojamientos.add(alojamiento);
        return alojamiento;
    }

}