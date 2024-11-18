package co.edu.uniquindio.bookyourstay.modelo;

import co.edu.uniquindio.bookyourstay.enums.Ciudad;
import co.edu.uniquindio.bookyourstay.enums.Servicio;
import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.factory.AlojamientoFactory;
import co.edu.uniquindio.bookyourstay.servicios.*;
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
public class ServicioReserva implements GestionUsuario, ReservaGestionable,  GestionReserva, AlojamientoGestionable {

    private Fabricante factory;

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
        listaReservas = new ArrayList<>();

        crearDatosPrueba();
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
    public Usuario registrarUsuario(String identificacion, String nombre, String telefono, String correo, String contrasenia,
                                    String codigoConfirmacionCuenta) throws Exception {
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
                .codigoConfirmacionCuenta(codigoConfirmacionCuenta)
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

    public Usuario buscarUsuario(String identificacion) {
        for (Usuario usuario : listaUsuarios) {
            if (identificacion.equals(usuario.getIdentificacion())) {
                return usuario;
            }
        }
        return null;
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

    @Override
    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion, String urlImagen,
                                        double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception {

        validarCamposAlojamiento(tipoAlojamiento, nombre, ciudad, descripcion, urlImagen, precioNoche, capacidadMax);

        Alojamiento alojamiento = factory.crearAlojamiento(
                UUID.randomUUID().toString(),
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


    public Alojamiento buscarAlojamiento(String alojamientoId) {
        if (alojamientoId == null) {
            return null;
        }

        for (Alojamiento alojamiento : listaAlojamientos) {
            if (alojamientoId.equals(alojamiento.getId())) {
                return alojamiento;
            }
        }

        return null;
    }

    @Override
    public Alojamiento modificarAlojamiento(String alojamientoId, TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion,
                                            String urlImagen, double precioNoche, int capacidadMax, double costoMantenimiento, List<Servicio> listaServicios) throws Exception {

        validarCamposAlojamiento(tipoAlojamiento, nombre, ciudad, descripcion, urlImagen, precioNoche, capacidadMax);

        Alojamiento nuevoAlojamiento;
        for (int i = 0; i < getListaAlojamientos().size(); i++) {
            Alojamiento alojamiento = getListaAlojamientos().get(i);
            if (alojamiento.getId().equals(alojamientoId)) {
                nuevoAlojamiento = factory.crearAlojamiento(
                        alojamientoId,
                        tipoAlojamiento,
                        nombre,
                        ciudad,
                        descripcion,
                        urlImagen,
                        precioNoche,
                        capacidadMax,
                        costoMantenimiento,
                        listaServicios);

                getListaAlojamientos().set(i, nuevoAlojamiento);
                return nuevoAlojamiento;
            }
        }

        throw new Exception("No existe un alojamiento con el ID " + alojamientoId);
    }

    @Override
    public void eliminarAlojamiento(String alojamientoId) throws Exception {
        Alojamiento alojamiento = buscarAlojamiento(alojamientoId);
        if (alojamiento == null) {
            throw new Exception("No existe un alojamiento con el ID " + alojamientoId);
        }

        listaAlojamientos.remove(alojamiento);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosPorTipo(TipoAlojamiento tipoAlojamiento) {
        List<Alojamiento> alojamientos = new ArrayList<>();
        for (Alojamiento alojamiento : getListaAlojamientos()) {
            if (alojamiento.obtenerTipoAlojamiento().equals(tipoAlojamiento)) {
                alojamientos.add(alojamiento);
            }
        }

        return alojamientos;
    }

    @Override
    public Habitacion crearHabitacion(String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen, String descripcion) throws Exception {
        Hotel hotel = (Hotel) buscarAlojamiento(hotelId);
        if (hotel == null) {
            throw new Exception("No existe un hotel con el ID: " + hotelId);
        }

        validarCamposHabitacion(numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);

        String habitacionId = UUID.randomUUID().toString();
        return hotel.crearHabitacion(habitacionId, numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);
    }

    @Override
    public void modificarHabitacion(String habitacionId, String hotelId, String numeroHabitacion, String precioPorNoche, String capacidad,
                                    String urlImagen, String descripcion) throws Exception {

        Hotel hotel = (Hotel) buscarAlojamiento(hotelId);
        if (hotel == null) {
            throw new Exception("No existe un hotel con el ID: " + hotelId);
        }

        validarCamposHabitacion(numeroHabitacion, precioPorNoche, capacidad, urlImagen, descripcion);

        for (int i = 0; i < hotel.getListaHabitaciones().size(); i++) {
            Habitacion habitacion = hotel.getListaHabitaciones().get(i);
            if (habitacion.getId().equals(habitacionId)) {
                hotel.getListaHabitaciones().set(i, Habitacion.builder()
                        .id(habitacionId)
                        .numero(numeroHabitacion)
                        .precio(Double.parseDouble(precioPorNoche))
                        .capacidad(Integer.parseInt(capacidad))
                        .urlImagen(urlImagen)
                        .descripcion(descripcion)
                        .build());
                break;
            }
        }
    }

    @Override
    public void eliminarHabitacion(String hotelId, String habitacionId) throws Exception {
        Hotel hotel = (Hotel) buscarAlojamiento(hotelId);
        if (hotel == null) {
            throw new Exception("No existe un hotel con el ID: " + hotelId);
        }

        Habitacion habitacion = hotel.buscarHabitacion(habitacionId);
        if (habitacion == null) {
            throw new Exception("No existe una habitación para el hotel " + hotel.getNombre() + "con el ID " + habitacionId);
        }

        getListaAlojamientos().remove(habitacion);
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String hotelId) throws Exception {
        Hotel hotel = (Hotel) buscarAlojamiento(hotelId);
        if (hotel == null) {
            throw new Exception("No existe un hotel con el ID: " + hotelId);
        }

        return hotel.getListaHabitaciones();
    }

    @Override
     public Reserva crearReserva(String identificacion, String alojamientoId, LocalDate fechaInicio, LocalDate fechaFinal,
                                 String cantidadHuespedes) throws Exception {

        ValidacionUtil.validarCampo(identificacion, "Identificación");
        ValidacionUtil.validarCampo(cantidadHuespedes, "Cantidad huespedes");

        Cliente cliente = (Cliente) buscarUsuario(identificacion);
        if (cliente == null) {
            throw new Exception("No existe un cliente con la identificación: " + identificacion);
        }

        Alojamiento alojamiento = buscarAlojamiento(alojamientoId);
        if (alojamiento == null) {
            throw new Exception("No existe un alojamiento con el ID " + alojamientoId);
        }

        int numeroHuespedes = Integer.parseInt(cantidadHuespedes);
        if (numeroHuespedes > alojamiento.getCapacidadMax()) {
            throw new Exception("El alojamiento no cuenta con capacidad para el número de huespedes ingresado.");
        }

       double costoReserva = alojamiento.calcularValorTotal(fechaInicio, fechaFinal, numeroHuespedes);
        if (cliente.getBilleteraVirtual().getValor() < costoReserva) {
            throw new Exception("La billetera virtual no cuenta con fondos suficientes.");
        }

        Reserva reserva = Reserva.builder()
                .id(UUID.randomUUID().toString())
                .cliente(cliente)
                .alojamiento(alojamiento)
                .fechaInicio(fechaInicio)
                .fechaFinal(fechaFinal)
                .numeroHuespedes(numeroHuespedes)
                .costoReserva(costoReserva)
                .build();

        getListaReservas().add(reserva);
        System.out.println(getListaReservas());
        return reserva;
    }

    @Override
    public List<Reserva> obtenerReservasPorCliente(String identificacion) throws Exception {
        Cliente cliente = (Cliente) buscarUsuario(identificacion);
        if (cliente == null) {
            throw new Exception("No se encontró un cliente con la identificación: " + identificacion);
        }

        List<Reserva> reservas = new ArrayList<>();
        for (Reserva reserva: getListaReservas()) {
            if (reserva.getCliente().equals(cliente)) {
                reservas.add(reserva);
            }
        }

        return reservas;
    }

    public Reserva buscarReserva(String reservaId) {
        for (Reserva reserva : listaReservas) {
            if (reservaId.equals(reserva.getId())) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void cancelarReserva(String reservaId) throws Exception {
        Reserva reserva = buscarReserva(reservaId);
        if (reserva == null) {
            throw new Exception("No existe una reserva con el ID " + reservaId);
        }

        listaReservas.remove(reserva);
    }

    @Override
    public void recargarBilletera(String identificacion, String valor) throws Exception {
        Cliente cliente = (Cliente) buscarUsuario(identificacion);
        if (cliente == null) {
            throw new Exception("No existe un cliente con la identificación: " + identificacion);
        }

        double valorRecarga = Double.parseDouble(valor);
        if (valorRecarga <= 0) {
            throw new Exception("Debes ingresar un valor correcto");
        }

        cliente.getBilleteraVirtual().recargarBilletera(valorRecarga);
    }

    private void validarCamposAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, Ciudad ciudad, String descripcion, String urlImagen,
                                          double precioNoche, int capacidadMax) throws Exception {

        ValidacionUtil.validarCampo(nombre, "Nombre");
        ValidacionUtil.validarCampo(descripcion, "Descripción");
        ValidacionUtil.validarCampo(urlImagen, "URL imagen");

        if (tipoAlojamiento == null) {
            throw new Exception("El tipo de alojamiento es un campo obligatorio");
        }

        if (ciudad == null) {
            throw new Exception("La ciudad es un campo obligatorio");
        }

        if (precioNoche <= 0) {
            throw new Exception("Por favor, ingrese un valor coherente para el campo Precio por noche");
        }

        if (capacidadMax <= 0) {
            throw new Exception("La capacidad del alojamiento es incorrecto");
        }
    }

    private void validarCamposHabitacion(String numeroHabitacion, String precioPorNoche, String capacidad, String urlImagen, String descripcion) throws Exception {
        ValidacionUtil.validarCampo(numeroHabitacion, "Número habitación");
        ValidacionUtil.validarCampo(precioPorNoche, "Precio por noche");
        ValidacionUtil.validarCampo(capacidad, "Capacidad");
        ValidacionUtil.validarCampo(urlImagen, "URL imagen");
        ValidacionUtil.validarCampo(descripcion, "Descripción");

        if (Double.parseDouble(precioPorNoche) <= 0) {
            throw new Exception("El precio por noche de la habitación es incorrecto");
        }

        if (Integer.parseInt(capacidad) <= 0) {
            throw new Exception("La capacidad de la habitación es incorrecto");
        }
    }

    public void crearDatosPrueba() {
        try {

            Cliente cliente = Cliente.builder()
                    .identificacion("123")
                    .nombre("Test")
                    .correo("cliente")
                    .contrasenia("123")
                    .codigoConfirmacionCuenta("12345")
                    .estado(true)
                    .build();

            getListaUsuarios().add(cliente);
            crearAdministrador("admin@gmail.com", "123", "1212", "Admin", "5676");

            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                TipoAlojamiento [] tipoAlojamientos = {TipoAlojamiento.CASA, TipoAlojamiento.APARTAMENTO, TipoAlojamiento.HOTEL};
                Alojamiento alojamiento = crearAlojamiento(
                        tipoAlojamientos[random.nextInt(0,3)],
                        "Alojamiento " + (i + 1),
                        Ciudad.BOGOTA,
                        "Descripción de prueba",
                        "https://verplanos.com/wp-content/uploads/2009/12/1007-thehousedesigners-rendering1-150x150.jpg",
                        60000,
                        2,
                        40000,
                        List.of(Servicio.PISCINA, Servicio.WIFI, Servicio.DESAYUNO));

                if(alojamiento.obtenerTipoAlojamiento().equals(TipoAlojamiento.HOTEL)) {
                    for (int j = 0; j < random.nextInt(1, 5); j++) {
                        crearHabitacion(alojamiento.getId(), String.valueOf(j), "250000","2","URL", "Prueba");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}