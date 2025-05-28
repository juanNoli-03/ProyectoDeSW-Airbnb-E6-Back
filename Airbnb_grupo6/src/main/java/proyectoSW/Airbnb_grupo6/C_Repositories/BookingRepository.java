package proyectoSW.Airbnb_grupo6.C_Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface BookingRepository extends JpaRepository<Booking, Long>{

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS Y USUARIO
    @Query("SELECT b FROM Booking b JOIN FETCH b.accommodation WHERE b.user.idUser = :idUser")
    List<Booking> findBookingsByIdUserWithAccommodationsAndUser(@Param("idUser") Long idUser);


    /// TRAER RESERVAS ASOCIADAS A UN USUARIO
    @Query("SELECT b FROM Booking b JOIN FETCH b.accommodation WHERE b.user.idUser = :userId")
    List<Booking> findBookingsByUserId(@Param("userId") Long userId);

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS PASADOS
    @Query("SELECT b FROM Booking b JOIN FETCH b.accommodation WHERE b.user.idUser = :idUser AND b.endDate < :endDate")
    List<Booking> findPastBookingsByUserBeforeDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("endDate") LocalDateTime endDate
    );

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS FUTUROS
    @Query("SELECT b FROM Booking b JOIN FETCH b.accommodation WHERE b.user.idUser = :idUser AND b.startDate > :startDate")
    List<Booking> findFutureBookingsByUserAfterDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("startDate") LocalDateTime startDate
    );

    /// TRAER RESERVAS ASOCIADAS A UN USUARIO CON ALOJAMIENTOS EN CURSO 
    @Query("SELECT b FROM Booking b JOIN FETCH b.accommodation WHERE b.user.idUser = :idUser AND b.startDate < :nowDate AND b.endDate > :nowDate")
    List<Booking> findInProgressBookingsByUserAfterDateWithAccommodation(
        @Param("idUser") Long idUser,
        @Param("nowDate") LocalDateTime nowDate
    );






}

