package bspoke.markus.statnett;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CustomInstantDeserializerTest {

    private final JsonParser jsonParser;

    CustomInstantDeserializerTest() {
        this.jsonParser = Mockito.spy(JsonParser.class);
    }

    @Test
    void deserialize_happy_case() throws IOException {
        Mockito.when(jsonParser.getText())
                .thenReturn("Wed, 13 Dec 2023 12:04:32 +0000");

        CustomInstantDeserializer customInstantDeserializer = new CustomInstantDeserializer();
        customInstantDeserializer.deserialize(jsonParser, null);
    }

    @Test
    void deserialize_double_space_before_day_of_month() throws IOException {
        Mockito.when(jsonParser.getText()).thenReturn("Sat,  9 Dec 2023 22:04:32 +0000");

        CustomInstantDeserializer customInstantDeserializer = new CustomInstantDeserializer();
        customInstantDeserializer.deserialize(jsonParser, null);
    }

    @Test
    void deserialize_invalid_string() throws IOException {
        Mockito.when(jsonParser.getText()).thenReturn("Wed, 32 Dec 2023 25:04:32 +0000");

        assertThrows(RuntimeException.class, () -> {
            CustomInstantDeserializer customInstantDeserializer = new CustomInstantDeserializer();
            customInstantDeserializer.deserialize(jsonParser, null);
        });
    }
}