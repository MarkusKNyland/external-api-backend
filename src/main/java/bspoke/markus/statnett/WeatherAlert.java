package bspoke.markus.statnett;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherAlert(
    @JsonProperty("identifier")
    String guid,
    String sender,
    Instant sent,
    String status,
    String msgType,
    String scope,
    String code,
    @JsonProperty("info")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<AlertInfo> alertInfoList
){}
