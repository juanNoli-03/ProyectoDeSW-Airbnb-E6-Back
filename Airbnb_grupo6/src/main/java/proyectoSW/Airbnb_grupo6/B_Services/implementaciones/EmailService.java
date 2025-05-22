package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String nombreUsuario, String emailDestino, String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(emailDestino);
        helper.setSubject(subject);

        String htmlMsg = "<h1>Hola " + nombreUsuario + ", " +
                "¡Gracias por registrarte en Airbnb! ✈️🏡</h1>" +
                "<h3>Estamos muy contentos de tenerte con nosotros. Ahora podés explorar alojamientos únicos y empezar a planificar tu próxima aventura.</h3>" +
                "<h3>🔑 Tu cuenta ya está activa.</h3>" +
                "<h3>👉 Empezá ahora: http://localhost:5173/\n\n</h3>" +
                "<h4>¡Bienvenido a la comunidad Airbnb!</h4>" +
                "<h4>El equipo de Airbnb.</h4>";
        helper.setText(htmlMsg, true);
        javaMailSender.send(mimeMessage);
    }
}