package proyectoSW.Airbnb_grupo6.A_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.BookingService;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class BookingController {

    @Autowired
    private BookingService bookingService;


    //TODO: TESTEAR SI SE REALIZO CORRECTAMENTE

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {

        Booking rent = bookingService.createBooking(booking);

        return ResponseEntity.ok(rent);
    }

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    @GetMapping("/user/bookings/{idUser}")
    public List<Booking> getBookings(@PathVariable Long idUser) {
        return bookingService.getBookings(idUser);
    }
    

}
