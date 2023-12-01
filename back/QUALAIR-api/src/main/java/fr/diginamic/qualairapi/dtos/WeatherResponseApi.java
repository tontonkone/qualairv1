package fr.diginamic.qualairapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record WeatherResponseApi(
        @JsonProperty("current_condition")
        Set<Condition> currentCondition
) {
    public record Condition(
            @JsonProperty("cloudcover")
            String cloudcover,
            @JsonProperty("temp_C")
            String temperature,
            @JsonProperty("humidity")
            String humidity
    ) {
    }
}