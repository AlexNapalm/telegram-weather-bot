package ru.krasnopolsky.weatherbot.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.krasnopolsky.weatherbot.model.City;
import ru.krasnopolsky.weatherbot.model.Error;

public class ResponseMapper {

    private final ObjectMapper mapper;

    public ResponseMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper = objectMapper;
    }

    @SneakyThrows
    public String toStringRepresentation(String json) {
        City city = mapper.readValue(json, City.class);
        return String.format("The weather in %s: %s \u2103 (%s \u2109)",
                city.getLocation().getName(),
                city.getCurrentWeather().getTemperatureCelsius(),
                city.getCurrentWeather().getTemperatureFahrenheit());
    }

    @SneakyThrows
    public String toError(String json) {
        Error error = mapper.readValue(json, Error.class);
        return error.getMessage();
    }
}
