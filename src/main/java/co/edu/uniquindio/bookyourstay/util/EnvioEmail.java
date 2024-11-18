package co.edu.uniquindio.bookyourstay.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {

    private static final String CORREO = "valentina.garzong@uqvirtual.edu.co";

    private static final String CONTRASENIA = "ppcq ervg aagk lwin";

    private static final Mailer mailer = MailerBuilder
            .withSMTPServer("smtp.gmail.com", 587, CORREO, CONTRASENIA)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .withDebugLogging(false)
            .buildMailer();

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {

        Email email = EmailBuilder.startingBlank()
                .from(CORREO)
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        mailer.sendMail(email);
    }

    public static void enviarNotificacionConQR(String destinatario, String asunto, String mensaje, byte[] qrBytes) {
        Email email = EmailBuilder.startingBlank()
                .from(CORREO)
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .withAttachment("reserva_qr.png", qrBytes, "image/png")
                .buildEmail();

        mailer.sendMail(email);
    }
}
