package proyectoSW.Airbnb_grupo6.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoSW.Airbnb_grupo6.Enums.MedioDePago;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "cantidad_huespedes", nullable = false)
    private int cantidadHuespedes;

    @Column(name = "monto_final", nullable = false)
    private double montoFinal;

    @Column(name = "medio_de_pago", nullable = false)
    private MedioDePago mediosDePago;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alojamiento", nullable = false)
    private Alojamiento alojamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}
