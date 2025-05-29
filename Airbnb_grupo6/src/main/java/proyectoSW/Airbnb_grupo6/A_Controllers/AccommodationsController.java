package proyectoSW.Airbnb_grupo6.A_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.AccommodationService;
import proyectoSW.Airbnb_grupo6.D_Entities.AccommodationFilter;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AccommodationsController {

    @Autowired
    private AccommodationService AccommodationService;

    /// TRAER TODOS LOS ALOJAMIENTOS
    @GetMapping("/accommodations")
    public ResponseEntity  <List<Accommodation>> getAccommodations (){
        List<Accommodation> accommodations = AccommodationService.getAccommodations();
        return ResponseEntity.ok(accommodations);
    }

    ///TRAER ALOJAMIENTO POR ID
    @GetMapping("/accommodations/{idAccommodation}")
    public ResponseEntity<Accommodation> GetAccommodationById(@PathVariable Long idAccommodation){

        Accommodation accommodation = AccommodationService.getAccommodation(idAccommodation);
        return ResponseEntity.ok(accommodation);
    }


    /// TRAER TODOS LOS ALOJAMIENTO POR PRECIO ASCENDENTE
    @GetMapping("/accommodationsByOrderByPricePerNightAsc")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByOrderByPricePerNightAsc (){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByOrderByPricePerNightAsc();
        return ResponseEntity.ok(accommodations);
    }

    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO DESCENDENTE
    @GetMapping("/accommodationsByOrderByPricePerNightDesc")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByOrderByPricePerNightDesc (){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByOrderByPricePerNightDesc();
        return ResponseEntity.ok(accommodations);
    }

    //TRAER TODOS LOS ALOJAMIENTOS POR PAIS
    @GetMapping("/accommodationsByCountry/{country}")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByCountry (@PathVariable String country){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByCountry(country);
        return ResponseEntity.ok(accommodations);
    }


    //TRAER TODOS LOS ALOJAMIENTOS POR CIUDAD
    @GetMapping("/accommodationsByCity/{city}")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByCity (@PathVariable String city){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByCity(city);
        return ResponseEntity.ok(accommodations);
    }

    //TRAER TODOS LOS ALOJAMIENTOS POR CONTINENTE
    @GetMapping("/accommodationsByContinent/{continent}")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByContinent (@PathVariable String continent){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByContinent(continent);
        return ResponseEntity.ok(accommodations);
    }

    //TRAER TODOS LOS ALOJAMIENTOS POR DISPONIBILIDAD TRUE
    @GetMapping("/accommodationsByAvailableTrue")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByAvailableTrue (){
        List<Accommodation> accommodations = AccommodationService.getAccommodationsByAvailableTrue();
        return ResponseEntity.ok(accommodations);
    }

    // FILTRADO DINAMICO PARA PERMITIR MULTIPLES COMBINACIONES
    @GetMapping("/filterAccommodations")
    public ResponseEntity<List<Accommodation>> filterAccommodations(
            @RequestParam(required = false) String continent,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false, defaultValue = "true") Boolean sortByPriceDesc,
            @RequestParam(required = false) Double pricePerNight) {
        AccommodationFilter filters = new AccommodationFilter(
                continent,
                country,
                city,
                pricePerNight,
                available != null && available,
                sortByPriceDesc != null && sortByPriceDesc
                );
        List<Accommodation> accommodations = AccommodationService.filterAccommodations(filters);
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/accommodationsByTitle")
    public ResponseEntity  <List<Accommodation>> getAccommodationsByTitleContainingIgnoreCase (
            @RequestParam(required = true) String title){

        List<Accommodation> accommodations = AccommodationService.getAccommodationsByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(accommodations);
    }


}
