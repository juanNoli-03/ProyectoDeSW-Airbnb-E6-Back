package proyectoSW.Airbnb_grupo6.B_Services.interfaces;
import jakarta.mail.MessagingException;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import java.util.List;

public interface BookingService {

    ///CREAR UNA RESERVA 
    public Booking createBooking(Booking b) throws MessagingException;

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    List<Booking> getBookings(Long idUser);

    

}
