package proyectoSW.Airbnb_grupo6.C_Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;


import java.util.Optional;
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{



}
