package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.C_Repositories.BookingRepository;
import proyectoSW.Airbnb_grupo6.C_Repositories.UserRepository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import proyectoSW.Airbnb_grupo6.D_Entities.User;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;
import java.util.List;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.BookingService;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.UserService;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.AccommodationService;

@Service
@RequiredArgsConstructor
public class BookingServiceImplementation implements BookingService{

    ///Atributos
    @Autowired
    private BookingRepository bookingRepository;

     @Autowired
    private UserService userService;

    @Autowired
    private AccommodationService accommodationService;

    ///constructor
    public BookingServiceImplementation(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    ///CREAR UNA RESERVA 
    public Booking createBooking(Booking b) {

        // Validaciones de campos obligatorios
        if (b.getStartDate() == null || b.getEndDate() == null
                || b.getNumberOfGuests() <= 0 || b.getNumberOfNights() <= 0
                || b.getFinal_amount() <= 0 || b.getPaymentMethod() == null
                || b.getUser() == null || b.getAccommodation() == null) {

            throw new CustomException(HttpStatus.BAD_REQUEST, "Faltan datos obligatorios para la reserva.");
        }

        // Validación extra: la fecha de inicio debe ser anterior a la de fin
        if (b.getStartDate().isAfter(b.getEndDate())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La fecha de inicio no puede ser posterior a la de fin.");
        }

        // Recuperar entidades reales desde la base de datos
        User user = userService.getUser(b.getUser().getIdUser());
        Accommodation accommodation = accommodationService.getAccommodation(b.getAccommodation().getIdAccommodation());

        

        // Setear entidades completas
        b.setUser(user);
        b.setAccommodation(accommodation);

        // Guardar la reserva en base de datos
        return bookingRepository.save(b);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    public List<Booking> getBookings(Long idUser) {
        return bookingRepository.findBookingsByIdUserWithAccommodationsAndUser(idUser);
    }

}
