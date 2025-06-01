package proyectoSW.Airbnb_grupo6.C_Repositories;

import org.springframework.data.jpa.domain.Specification;
import proyectoSW.Airbnb_grupo6.D_Entities.Accommodation;

public class AccommodationSpecification {

    public static Specification<Accommodation> hasContinent(String continent) {
        return (root, query, cb) -> cb.equal(root.get("continent"), continent);
    }

    public static Specification<Accommodation> hasCountry(String country) {
        return (root, query, cb) -> cb.equal(root.get("country"), country);
    }

    public static Specification<Accommodation> hasCity(String city) {
        return (root, query, cb) -> cb.equal(root.get("city"), city);
    }

    public static Specification<Accommodation> isPricePerNightLower(Double pricePerNight) {
        return (root, query, cb) -> cb.lessThan(root.get("pricePerNight"), pricePerNight);
    }
}
