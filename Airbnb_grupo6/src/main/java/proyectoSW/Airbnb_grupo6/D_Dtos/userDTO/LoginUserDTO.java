package proyectoSW.Airbnb_grupo6.D_Dtos.userDTO;

import lombok.*;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {

    private String email;
    private String password;

    public LoginUserDTO (final User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
