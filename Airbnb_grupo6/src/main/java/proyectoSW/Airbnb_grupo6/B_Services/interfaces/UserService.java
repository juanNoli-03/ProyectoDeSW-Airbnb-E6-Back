package proyectoSW.Airbnb_grupo6.B_Services.interfaces;

import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.CreateUserDTO;
import proyectoSW.Airbnb_grupo6.D_Dtos.userDTO.LoginUserDTO;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

public interface UserService {

    //1- Sign Up.
    CreateUserDTO signUp (CreateUserDTO createUserDTO);

    //2- Login.
    LoginUserDTO login (LoginUserDTO loginUserDTO);
}
