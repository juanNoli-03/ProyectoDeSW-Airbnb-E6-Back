package proyectoSW.Airbnb_grupo6.B_Services.interfaces;

import jakarta.mail.MessagingException;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.CreateUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.LoginUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.ShowUserDTO;
import proyectoSW.Airbnb_grupo6.D_Entities.User;


public interface UserService {

    //1- Sign Up.
    CreateUserDTO signUp (CreateUserDTO createUserDTO) throws MessagingException;

    //2- Login.
    ShowUserDTO login (LoginUserDTO loginUserDTO);

    ///TRAER USUARIO POR ID
    User getUser(Long idUser);

    //TRAER USUARIO POR MAIL
    User getUser(String email);

    //TRAER USUARIO POR MAIL Y CONTRASEÑA
    User getUser(String email, String password);
}
