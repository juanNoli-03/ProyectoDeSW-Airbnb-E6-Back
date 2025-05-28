package proyectoSW.Airbnb_grupo6.B_Services.interfaces;

import java.util.List;

import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.D_Entities.AccommodationFilter;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

public interface AccommodationService {
    
    /// TRAER TODOS LOS ALOJAMIENTOS
    List<Accommodation> getAccommodations();

    ///TRAER ALOJAMIENTO POR ID
    Accommodation getAccommodation(Long idAccommodation);

    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO ASCENDENTE
    List<Accommodation> getAccommodationsByOrderByPricePerNightAsc();
    
    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO DESCENDENTE
    List<Accommodation> getAccommodationsByOrderByPricePerNightDesc();

    //TRAER TODOS LOS ALOJAMIENTOS POR PAIS
    List<Accommodation> getAccommodationsByCountry(String country);

    //TRAER TODOS LOS ALOJAMIENTOS POR CIUDAD
    List<Accommodation> getAccommodationsByCity(String city);

    //TRAER TODOS LOS ALOJAMIENTOS POR CONTINENTE
    List<Accommodation> getAccommodationsByContinent(String continent);

    //TRAER TODOS LOS ALOJAMIENTOS POR DISPONIBILIDAD TRUE
    List<Accommodation> getAccommodationsByAvailableTrue();

    // FILTRADO DINAMICO PARA PERMITIR MULTIPLES COMBINACIONES
    List<Accommodation> filterAccommodations(AccommodationFilter filter);

    ///TRAER POR TITULO 
    List<Accommodation> getAccommodationsByTitleContainingIgnoreCase(String title);
}
