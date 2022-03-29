package dmit2015.view;

import dmit2015.model.OpenWeatherData;
import dmit2015.model.WeatherService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

// need this for injection in pages
@Named
public class WeatherServiceController {

    // only use underscore in names if data field for internal usage only
    @Inject
    @RestClient
    private WeatherService _weatherService;

    @Inject
    @ConfigProperty(name = "api.openweathermap.org.ApiKey")
    private String _weatherApiKey;

    @Getter
    private OpenWeatherData weatherData;

    // used when you need to wait for injection of code to finish before you create what you need
    @PostConstruct
    void init() {
        try {
            weatherData = _weatherService.findByCityName("Edmonton", "metric", _weatherApiKey);
        } catch (Exception ex) {
            ex.printStackTrace();
            Messages.addGlobalError("Error fetching weather data with exception {0}", ex.getMessage());
        }
    }
}
