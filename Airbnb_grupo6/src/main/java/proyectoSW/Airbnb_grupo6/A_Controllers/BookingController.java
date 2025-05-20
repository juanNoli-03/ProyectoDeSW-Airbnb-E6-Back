package proyectoSW.Airbnb_grupo6.A_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.B_Services.implementaciones.BookingServiceImplementation;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class BookingController {

    @Autowired
    private BookingServiceImplementation bookingService;


    //TODO: TESTEAR SI SE REALIZO CORRECTAMENTE

    @PostMapping("/rent")
    public ResponseEntity<Booking> rent(@RequestBody Booking booking) {

        Booking rent = bookingService.createReservation(booking);

        return ResponseEntity.ok(rent);
    }

}
