package bspoke.markus.statnett;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WeatherAlertRepository {

    public Optional<WeatherAlert> findByGuid() {
        return Optional.empty();
    }

    public void saveAlert(WeatherAlert alert) {
    }
}
