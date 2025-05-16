package proyectoSW.Airbnb_grupo6.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoSW.Airbnb_grupo6.Enums.TipoUsuario;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;
}
