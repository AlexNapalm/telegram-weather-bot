package ru.krasnopolsky.weatherbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Error {
    private Integer code;
    private String message;

    @JsonProperty("error")
    private void mapNestedProperties(Map<String,Object> error) {
        this.code = (Integer) error.get("code");
        this.message = (String) error.get("message");
    }
}
