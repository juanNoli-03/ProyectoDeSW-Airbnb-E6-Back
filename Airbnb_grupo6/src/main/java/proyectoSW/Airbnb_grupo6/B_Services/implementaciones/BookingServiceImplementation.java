package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.C_Repositories.BookingRepository;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImplementation {

    @Autowired
    private BookingRepository BookingRepository;


    // crear reserva
    public Booking createReservation(Booking b ){

        if(b.getAccommodation() == null || b.getEndDate() == null || b.getUser() == null
            || b.getFinal_amount() <= 0 || b.getStartDate() == null || b.getPaymentMethod() == null
                || b.getNumberOfGuests() <= 0 || b.getNumberOfNights() <= 0 ){

            throw new CustomException(HttpStatus.BAD_REQUEST, "Error: faltan datos para completar la reserva");

        }

        return BookingRepository.save(b);

    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    public List<Booking> getBookings(Long idUser) {
        return BookingRepository.findBookings(idUser);
    }

}
