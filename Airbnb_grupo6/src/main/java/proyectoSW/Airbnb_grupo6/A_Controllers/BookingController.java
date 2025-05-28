package proyectoSW.Airbnb_grupo6.A_Controllers;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.BookingService;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;

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

    @GetMapping("/user/bookingsPast/{idUser}")
    public List<Booking> getBookingsByUserAndDateRangeWithAccommodation(@PathVariable Long idUser) {
        LocalDateTime endDate = LocalDateTime.now();
        return bookingService.getBookingsByUserAndDateRangeWithAccommodation(idUser, endDate);
    }
    

}
