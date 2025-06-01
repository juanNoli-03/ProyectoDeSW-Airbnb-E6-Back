package proyectoSW.Airbnb_grupo6.D_Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationFilter {
    private String continent;
    private String country;
    private String city;
    private Double pricePerNight;
    private boolean sortByPriceDesc;
}
