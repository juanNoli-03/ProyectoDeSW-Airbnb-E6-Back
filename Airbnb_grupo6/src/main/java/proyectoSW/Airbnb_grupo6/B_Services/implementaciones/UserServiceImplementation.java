package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.UserService;
import proyectoSW.Airbnb_grupo6.C_Repositories.UserRepository;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.CreateUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.LoginUserDTO;
import proyectoSW.Airbnb_grupo6.D_Entities.User;
import proyectoSW.Airbnb_grupo6.E_Enums.UserType;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserDTO signUp(CreateUserDTO createUserDTO) {

        //Inicializamos el user vacío.
        User userAGuardar = User.builder().build();
        userAGuardar.setUserType(UserType.ANFITRION);
        createUserDTO.guardarCreateUserDTO(userAGuardar);
        userRepository.save(userAGuardar);

        return createUserDTO;
    }

    @Override
    public LoginUserDTO login(LoginUserDTO loginUserDTO) {
        //Validamos si los datos son correctos
        User userEncontrado = userRepository.findByEmailAndPassoword(loginUserDTO.getEmail(), loginUserDTO.getPassword())
                .orElseThrow( ()-> new CustomException(HttpStatus.FORBIDDEN, "Credenciales invalidas!"));
        return new LoginUserDTO(userEncontrado);
    }
}
