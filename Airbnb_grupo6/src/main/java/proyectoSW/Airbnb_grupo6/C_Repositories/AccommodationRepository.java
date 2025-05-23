package proyectoSW.Airbnb_grupo6.C_Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO ASCENDENTE
    List<Accommodation> findAllByOrderByPricePerNightAsc();

    ///TRAER TODOS LOS ALOJAMIENTO POR PRECIO DESCENDENTE
    List<Accommodation> findAllByOrderByPricePerNightDesc();

    //TRAER TODOS LOS ALOJAMIENTOS POR PAIS
    List<Accommodation> findAllByCountry(String country);

}
