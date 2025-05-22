package proyectoSW.Airbnb_grupo6.D_Dtos.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoSW.Airbnb_grupo6.D_Entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class ShowUserDTO {

    private String firstName;
    private String lastName;
    private String email;

    public ShowUserDTO(User user){
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }


}
