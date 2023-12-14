package bspoke.markus.statnett;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class CustomInstantDeserializer extends StdDeserializer<Instant> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, ppd MMM yyyy HH:mm:ss xxxx");

    public CustomInstantDeserializer() {
        this(null);
    }

    public CustomInstantDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Instant deserialize(final JsonParser jsonParser, final DeserializationContext context) {
        try {
            return formatter.parse(jsonParser.getText(), Instant::from);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}