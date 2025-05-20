package proyectoSW.Airbnb_grupo6.C_Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;

@Repository

public interface BookingRepository extends JpaRepository<Booking, Long>{


}

