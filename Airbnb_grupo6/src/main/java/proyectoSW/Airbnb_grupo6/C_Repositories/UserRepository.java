package proyectoSW.Airbnb_grupo6.C_Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query ("SELECT u FROM User u WHERE u.email = :email AND u.password = :password ")
    Optional <User> findByEmailAndPassoword (@Param("email") String email, @Param("password") String password);
}