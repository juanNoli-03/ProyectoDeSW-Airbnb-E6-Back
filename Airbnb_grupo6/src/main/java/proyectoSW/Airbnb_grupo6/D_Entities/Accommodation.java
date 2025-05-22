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
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccommodation;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "ciudad", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "price_per_night", nullable = false)
    private Double pricePerNight;

    @Column(name = "available", nullable = false)
    private Boolean available;

    private String imageUrl;
}
