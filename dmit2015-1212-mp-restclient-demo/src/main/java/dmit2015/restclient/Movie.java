package dmit2015.restclient;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Movie pojo class
 */
@Data
public class Movie {
    private String title;
    private String releaseDate;
    private String genre;
    private String rating;
    private BigDecimal price;
}
