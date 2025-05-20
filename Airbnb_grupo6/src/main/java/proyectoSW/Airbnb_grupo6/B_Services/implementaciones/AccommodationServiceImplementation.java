package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;
import proyectoSW.Airbnb_grupo6.C_Repositories.AccommodationRepository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImplementation {

    @Autowired
    private AccommodationRepository AccommodationRepository;


    public List<Accommodation> GetAllAccommodations(){

        List<Accommodation> list =  new ArrayList<>();
        list = AccommodationRepository.findAll();

        if (list.isEmpty()) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Error: no existen alojamientos");
        }

        return list;

    }

}
