package bspoke.markus.statnett;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.net.URL;
import java.time.Instant;

public record FeedItem(
        String title,
        String description,
        URL link,
        String author,
        String category,
        String guid,
        @JsonProperty("pubDate")
        @JsonDeserialize(using = CustomInstantDeserializer.class)
        Instant publicationTime
) {}
