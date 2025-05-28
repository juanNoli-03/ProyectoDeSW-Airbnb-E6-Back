package proyectoSW.Airbnb_grupo6.D_Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "accommodation_details")
public class AccommodationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccommodationDetail;

    @Column(name = "rooms", nullable = false)
    private int rooms;

    @Column(name = "beds", nullable = false)
    private String beds;

    @Column(name = "bathrooms", nullable = false)
    private String bathrooms;
}
