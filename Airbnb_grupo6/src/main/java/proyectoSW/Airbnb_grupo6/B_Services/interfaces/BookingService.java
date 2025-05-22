package proyectoSW.Airbnb_grupo6.B_Services.interfaces;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import java.util.List;

public interface BookingService {

    ///CREAR BOOKING
    public Booking createBooking(Booking b);

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    List<Booking> getBookings(Long idUser);

    

}
