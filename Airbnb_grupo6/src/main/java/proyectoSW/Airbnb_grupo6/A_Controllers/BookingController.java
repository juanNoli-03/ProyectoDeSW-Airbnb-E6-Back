package proyectoSW.Airbnb_grupo6.A_Controllers;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.BookingService;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import scala.collection.concurrent.Map;

import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class BookingController {

    @Autowired
    private BookingService bookingService;

    ///CREAR UNA RESERVA 
    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws MessagingException {

        Booking reservation = bookingService.createBooking(booking);

        return ResponseEntity.ok(reservation);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    @GetMapping("/user/bookings/{idUser}")
    public List<Booking> getBookings(@PathVariable Long idUser) {
        return bookingService.getBookings(idUser);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS PASADOS
    @GetMapping("/user/bookingsPast/{idUser}")
    public List<Booking> getPastBookingsByUserAndDateRangeWithAccommodation(@PathVariable Long idUser) {
        LocalDateTime endDate = LocalDateTime.now();
        return bookingService.getPastBookingsByUserBeforeDateWithAccommodation(idUser, endDate);
    }
    

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS FUTUROS
    @GetMapping("/user/bookingsFuture/{idUser}")
    public List<Booking> getFutureBookingsByUserAfterDateWithAccommodation(@PathVariable Long idUser) {
        LocalDateTime startDate = LocalDateTime.now();
        return bookingService.getFutureBookingsByUserAfterDateWithAccommodation(idUser, startDate);
    }
    
    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS EN CURSO 
    @GetMapping("/user/bookingsInProgress/{idUser}")
    public List<Booking> getInProgressBookingsByUserAfterDateWithAccommodation(@PathVariable Long idUser) {
        LocalDateTime nowDate = LocalDateTime.now();
        return bookingService.getInProgressBookingsByUserAfterDateWithAccommodation(idUser, nowDate);
    }

    ///TRAER RESERVA POR ID ALOJAMIENTO CON ALOJAMIENTO
    @GetMapping("/booking/{idBooking}")
    public Booking getBookingByIdWithAccommodation(@PathVariable Long idBooking) {
        return bookingService.getBookingByIdWithAccommodation(idBooking);
    }


    ///MODIFICAR RATING DE UNA BOOKING
    @PutMapping("/booking/{idBooking}/rate")
    public ResponseEntity<Booking> rateBooking(
        @PathVariable Long idBooking,
        @RequestParam float rating
    ) {
        Booking updated = bookingService.updateRatingBooking(idBooking, rating);
        return ResponseEntity.ok(updated);
    }
    
    
}
