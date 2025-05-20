package proyectoSW.Airbnb_grupo6.A_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.B_Services.implementaciones.AccommodationServiceImplementation;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController

public class AccommodationsController {

    @Autowired
    private AccommodationServiceImplementation AccommodationService;

    @GetMapping("/accommodations")
    public ResponseEntity  <List<Accommodation>> getAccommodations (){
    List<Accommodation> allotments = AccommodationService.GetAllAccommodations();
    return ResponseEntity.ok(allotments);
    }

    @GetMapping("/accommodations/{id}")
    public ResponseEntity<Accommodation> GetAccommodationById(@PathVariable Long id){

    Accommodation allotment = AccommodationService.GetAccommodationById(id);
    return ResponseEntity.ok(allotment);
    }




}
