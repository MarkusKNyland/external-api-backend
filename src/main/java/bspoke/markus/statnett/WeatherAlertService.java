package bspoke.markus.statnett;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class WeatherAlertService {

    private final HttpRequest request;
    private final HttpClient httpClient;
    private final XmlMapper xmlMapper;
    private final WeatherAlertRepository repository;

    public WeatherAlertService(XmlMapper xmlMapper, WeatherAlertRepository repository) throws URISyntaxException {
        this.repository = repository;
        this.httpClient = HttpClient.newBuilder().build();
        this.request = HttpRequest.newBuilder()
                .uri(new URI("https://api.met.no/weatherapi/metalerts/1.1?show=all"))
                .GET()
                .build();
        this.xmlMapper = xmlMapper;
    }

    public List<FeedItem> getFeed() throws IOException, InterruptedException {
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        JsonNode jsonNode;
        try {
            jsonNode = xmlMapper.readTree(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode itemList = jsonNode.get("channel").get("item");

        return xmlMapper.readerForListOf(FeedItem.class).readValue(itemList);
    }

    public WeatherAlert getAlert(String guid) {
        return repository.findByGuid()
                .orElseGet(() -> {
                    try {
                        HttpRequest httpRequest = HttpRequest.newBuilder()
                                .uri(new URI("https://api.met.no/weatherapi/metalerts/1.1?cap=%s".formatted(guid)))
                                .GET()
                                .build();

                        HttpResponse<InputStream> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
                        JsonNode jsonNode = xmlMapper.readTree(response.body());

                        WeatherAlert alert = xmlMapper.readerFor(WeatherAlert.class).readValue(jsonNode);
                        repository.saveAlert(alert);
                        return alert;

                    } catch (URISyntaxException | IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
