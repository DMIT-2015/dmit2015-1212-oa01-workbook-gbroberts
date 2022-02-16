package dmit2015.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LocationsEntityDto {

    private Short locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;

    // country id useless to user, add countryName instead
    private String countryName;
}
