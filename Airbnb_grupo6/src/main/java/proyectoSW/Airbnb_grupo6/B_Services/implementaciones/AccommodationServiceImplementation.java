package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.AccommodationService;
import proyectoSW.Airbnb_grupo6.C_Repositories.AccommodationRepository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImplementation implements AccommodationService {

    @Autowired
    private AccommodationRepository AccommodationRepository;

    /// TRAER TODOS LOS ALOJAMIENTOS
    public List<Accommodation> getAccommodations(){

        List<Accommodation> list =  new ArrayList<>();

        list = AccommodationRepository.findAll();
        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;
    }

    ///TRAER ALOJAMIENTO POR ID
    public Accommodation getAccommodation(Long idAccommodation){
        return AccommodationRepository.findById(idAccommodation).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos"));
    }



    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO ASCENDENTE
    public List<Accommodation> getAccommodationsByOrderByPricePerNightAsc(){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByOrderByPricePerNightAsc();

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;
    }

    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO DESCENDENTE
    public List<Accommodation> getAccommodationsByOrderByPricePerNightDesc(){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByOrderByPricePerNightDesc();

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;
    }

    //TRAER TODOS LOS ALOJAMIENTOS POR PAIS
    public List<Accommodation> getAccommodationsByCountry(String country){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByCountry(country);

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;

    }
   
}
