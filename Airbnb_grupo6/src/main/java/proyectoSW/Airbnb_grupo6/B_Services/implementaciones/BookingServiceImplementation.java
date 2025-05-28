package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.C_Repositories.BookingRepository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import proyectoSW.Airbnb_grupo6.D_Entities.User;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;

import java.time.LocalDateTime;
import java.time.LocalDate;
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

    @Autowired
    private EmailService emailService;


    ///constructor
    public BookingServiceImplementation(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    ///CREAR UNA RESERVA 
    public Booking createBooking(Booking b) throws MessagingException {

        // Validaciones de campos obligatorios
        if (b.getStartDate() == null || b.getEndDate() == null
                || b.getNumberOfGuests() <= 0 || b.getNumberOfNights() <= 0
                || b.getFinalAmount() <= 0 || b.getPaymentMethod() == null
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

        //Enviar mail de confirmación de reserva
        emailService.sendEmailBooking(user.getFirstName(),user.getLastName() , user.getEmail(), accommodation.getTitle(), "Reservaste en Airbnb!" );

        // Guardar la reserva en base de datos
        return bookingRepository.save(b);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    public List<Booking> getBookings(Long idUser) {
        return bookingRepository.findBookingsByIdUserWithAccommodationsAndUser(idUser);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS PASADOS
    public List<Booking> getPastBookingsByUserBeforeDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("endDate") LocalDateTime endDate
    ){
        return bookingRepository.findPastBookingsByUserBeforeDateWithAccommodation(idUser, endDate);
    }


    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS FUTUROS
    public List<Booking> getFutureBookingsByUserAfterDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("startDate") LocalDateTime startDate
    ){
        return bookingRepository.findFutureBookingsByUserAfterDateWithAccommodation(idUser, startDate);
    }



}
