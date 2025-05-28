package proyectoSW.Airbnb_grupo6.B_Services.interfaces;
import jakarta.mail.MessagingException;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingService {

    ///CREAR UNA RESERVA 
    public Booking createBooking(Booking b) throws MessagingException;

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    List<Booking> getBookings(Long idUser);

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS PASADOS
    List<Booking> getPastBookingsByUserBeforeDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("endDate") LocalDateTime endDate
    );

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS FUTUROS
    List<Booking> getFutureBookingsByUserAfterDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("startDate") LocalDateTime startDate
    );

    

}
