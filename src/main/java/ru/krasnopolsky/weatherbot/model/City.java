package ru.krasnopolsky.weatherbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
    private Location location;
    @JsonProperty("current")
    private Weather currentWeather;
}
