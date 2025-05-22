package proyectoSW.Airbnb_grupo6.A_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.AccommodationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AccommodationsController {

    @Autowired
    private AccommodationService AccommodationService;

    /// TRAER TODOS LOS ALOJAMIENTOS
    @GetMapping("/accommodations")
    public ResponseEntity  <List<Accommodation>> getAccommodations (){
        List<Accommodation> allotments = AccommodationService.getAccommodations();
        return ResponseEntity.ok(allotments);
    }

    ///TRAER ALOJAMIENTO POR ID
    @GetMapping("/accommodations/{idAccommodation}")
    public ResponseEntity<Accommodation> GetAccommodationById(@PathVariable Long idAccommodation){

        Accommodation accommodation = AccommodationService.getAccommodation(idAccommodation);
        return ResponseEntity.ok(accommodation);
    }

}
