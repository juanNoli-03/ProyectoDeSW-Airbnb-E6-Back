package proyectoSW.Airbnb_grupo6.C_Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository

public interface BookingRepository extends JpaRepository<Booking, Long>{

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO
    @Query("SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.accommodation WHERE b.user.idUser = :userId")
    List<Booking> findBookingsByUserId(@Param("userId") Long userId);


}

