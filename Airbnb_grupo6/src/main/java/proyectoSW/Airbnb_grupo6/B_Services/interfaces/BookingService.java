package proyectoSW.Airbnb_grupo6.B_Services.interfaces;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import java.util.List;

public interface BookingService {

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO
    List<Booking> getBookingsByUserId(Long userId);

}
