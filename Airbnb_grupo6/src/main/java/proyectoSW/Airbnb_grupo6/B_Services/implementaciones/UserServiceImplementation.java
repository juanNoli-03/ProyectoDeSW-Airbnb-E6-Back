package proyectoSW.Airbnb_grupo6.B_Services.implementaciones;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import proyectoSW.Airbnb_grupo6.B_Services.interfaces.UserService;
import proyectoSW.Airbnb_grupo6.C_Repositories.UserRepository;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.CreateUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.LoginUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.ShowUserDTO;
import proyectoSW.Airbnb_grupo6.D_Entities.User;
import proyectoSW.Airbnb_grupo6.E_Exceptions.CustomException;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {


    ///Atributos
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    
    ///constructor
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserDTO signUp(CreateUserDTO createUserDTO) throws MessagingException {

        //Inicializamos el user vacío.
        User userAGuardar = User.builder().build();
        createUserDTO.guardarCreateUserDTO(userAGuardar);
        userRepository.save(userAGuardar);
        emailService.sendEmail((createUserDTO.getFirstName() +" "+ createUserDTO.getLastName() ), createUserDTO.getEmail(),
                "Bienvenido a Airbnb!" );

        return createUserDTO;
    }

    @Override
    public ShowUserDTO login(LoginUserDTO loginUserDTO) {
        //Validamos si los datos son correctos
        User userEncontrado = userRepository.findByEmailAndPassoword(loginUserDTO.getEmail(), loginUserDTO.getPassword())
                .orElseThrow( ()-> new CustomException(HttpStatus.FORBIDDEN, "Credenciales invalidas!"));
        return new ShowUserDTO(userEncontrado);
    }

    ///TRAER USUARIO POR ID
    @Override
    public User getByIdUser(Long id) {
        return userRepository.findById(id).orElse(null); // Devuelve null si no se encuentra
    }

    @Override

    public User getByEmailUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new CustomException(HttpStatus.FORBIDDEN, "User Not Found"));
    }

}
