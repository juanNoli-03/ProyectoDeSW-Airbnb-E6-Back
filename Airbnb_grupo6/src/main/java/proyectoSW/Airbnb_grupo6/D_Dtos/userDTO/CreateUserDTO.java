package proyectoSW.Airbnb_grupo6.D_Dtos.userDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

@Data
@Getter
@Setter
public class CreateUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dni;

    public void guardarCreateUserDTO (final User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setDni(this.dni);
    }


}
