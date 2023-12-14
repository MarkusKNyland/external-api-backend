package bspoke.markus.statnett;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WeatherAlertController {

    private final WeatherAlertService weatherAlertService;

    public WeatherAlertController(WeatherAlertService weatherAlertService) {
        this.weatherAlertService = weatherAlertService;
    }

    @GetMapping(value = "rss")
    public List<FeedItem> getFeed(){
        try {
            return weatherAlertService.getFeed();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("alert/{guid}")
    public WeatherAlert getFeed(@PathVariable String guid){
        return weatherAlertService.getAlert(guid);
    }
}
