package bspoke.markus.statnett;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AlertInfo(
    String language,
    String category,
    String event,
    String responseType,
    String urgency,
    Instant effective,
    Instant expires,
    String headline,
    String description
){}
