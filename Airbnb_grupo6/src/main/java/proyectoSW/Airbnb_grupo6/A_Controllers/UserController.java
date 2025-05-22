package proyectoSW.Airbnb_grupo6.A_Controllers;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proyectoSW.Airbnb_grupo6.B_Services.implementaciones.UserServiceImplementation;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.CreateUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.LoginUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.ShowUserDTO;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @PostMapping("/login")
    public ResponseEntity <ShowUserDTO> login (@RequestBody LoginUserDTO loginUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImplementation.login(loginUserDTO));
    }

    @PostMapping("/signUp")
    public ResponseEntity<CreateUserDTO> signUp (@RequestBody CreateUserDTO createUserDTO) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImplementation.signUp(createUserDTO));
    }
}
