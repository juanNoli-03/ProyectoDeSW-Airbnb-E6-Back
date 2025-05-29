package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
import java.util.ArrayList;
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

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS EN CURSO 
    public List<Booking> getInProgressBookingsByUserAfterDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("nowDate") LocalDateTime nowDate
    ){
        return bookingRepository.findInProgressBookingsByUserAfterDateWithAccommodation(idUser, nowDate);
    }


    ///TRAER RESERVA POR ID CON ALOJAMIENTO
    public Booking getBookingByIdWithAccommodation(@Param("idBooking") Long idBooking){
        return bookingRepository.findBookingByIdWithAccommodation(idBooking);
    }

    
    ///MODIFICAR RATING DE UNA BOOKING
    public Booking updateRatingBooking(
        @Param("idBooking") Long idBooking,
        @Param("newRating") float newRating
    ){
        
        Booking b = getBookingByIdWithAccommodation(idBooking);

        if (b != null) {
            
            if(b.isRated() == false){

                int numberOfRating = b.getAccommodation().getNumberOfRating() + 1;
                float resultRating = ((b.getAccommodation().getRating() * b.getAccommodation().getNumberOfRating()) + newRating) / (numberOfRating);
                
                b.getAccommodation().setNumberOfRating(numberOfRating);
                b.getAccommodation().setRating(resultRating);
                b.setRated(true);

            }else{

                throw new CustomException(HttpStatus.BAD_REQUEST, "Esta reserva ya fue calificada.");
            }

        }else{

            throw new CustomException(HttpStatus.NOT_FOUND, "No se encontró la reserva con ID: " + idBooking);
        }

        return bookingRepository.save(b);
    } 
    

}
