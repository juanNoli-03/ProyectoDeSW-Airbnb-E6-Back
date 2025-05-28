package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.C_Repositories.AccommodationSpecification;
import proyectoSW.Airbnb_grupo6.D_Entities.AccommodationFilter;
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
    @Autowired
    private AccommodationRepository accommodationRepository;

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

     //TRAER TODOS LOS ALOJAMIENTOS POR CIUDAD
    public List<Accommodation> getAccommodationsByCity(String city){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByCity(city);

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;

    }

    //TRAER TODOS LOS ALOJAMIENTOS POR CIUDAD
    public List<Accommodation> getAccommodationsByContinent(String continent){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByContinent(continent);

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;

    }
    
    //TRAER TODOS LOS ALOJAMIENTOS POR DISPONIBILIDAD TRUE
    public List<Accommodation> getAccommodationsByAvailableTrue(){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAllByAvailableTrue();

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;

    }

    /**
     * Metodo de combinacion dinamica de filtros usando la entidad {@code AccommodationFilter}
     *  Solo se buscan en la consulta, los atributos presentes en el objeto filter.
     * <p>
     *  Si se quiere añadir un nuevo filtro, se debe agregar en la Clase AccommodationFilter, y agregar sus respectivo
     *  hasPropiedad en la clase {@code AccommodationSpecification}
     */
    public List<Accommodation> filterAccommodations(AccommodationFilter filter) {

        Specification<Accommodation> spec = Specification.where(null);

        if (filter.getContinent() != null) {
            spec = spec.and(AccommodationSpecification.hasContinent(filter.getContinent()));
        }
        if (filter.getCountry() != null) {
            spec = spec.and(AccommodationSpecification.hasCountry(filter.getCountry()));
        }
        if (filter.getCity() != null) {
            spec = spec.and(AccommodationSpecification.hasCity(filter.getCity()));
        }

        spec = spec.and(AccommodationSpecification.isAvailable(filter.isAvailable()));


        Sort sort = filter.isSortByPriceDesc() ? Sort.by("pricePerNight").descending() : Sort.by("pricePerNight").ascending();

        List<Accommodation> result = accommodationRepository.findAll(spec, sort);

        if (result.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return result;
    }


    ///TRAER POR TITULO 
    public List<Accommodation> getAccommodationsByTitleContainingIgnoreCase(String title){

        List<Accommodation> list =  new ArrayList<>();

        list = AccommodationRepository.findByTitleContainingIgnoreCase(title);

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Error: no existen alojamientos");
        }

        return list;
    }
}
