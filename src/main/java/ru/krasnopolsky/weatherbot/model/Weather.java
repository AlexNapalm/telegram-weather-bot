package ru.krasnopolsky.weatherbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    @JsonProperty("temp_c")
    private double temperatureCelsius;
    @JsonProperty("temp_f")
    private double temperatureFahrenheit ;
}
