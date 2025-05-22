package proyectoSW.Airbnb_grupo6.B_Services.interfaces;

import java.util.List;

import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

public interface AccommodationService {
    
    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    List<Accommodation> getAll();

    ///TRAER USUARIO POR ID
    Accommodation getAccommodation(Long idAccommodation);

    
}
