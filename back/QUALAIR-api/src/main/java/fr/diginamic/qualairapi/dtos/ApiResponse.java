package fr.diginamic.qualairapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiResponse(
        @JsonProperty("status") String status,
        @JsonProperty("data") Data data
) {

    public record Data(
            @JsonProperty("aqi") int aqi,
            @JsonProperty("idx") int idx,
            @JsonProperty("attributions") List<Attribution> attributions,
            @JsonProperty("city") City city,
            @JsonProperty("dominantpol") String dominantpol,
            @JsonProperty("iaqi") Iaqi iaqi,
            @JsonProperty("time") Time time,
            @JsonProperty("forecast") Forecast forecast
    ) {

        public record Attribution(
                @JsonProperty("url") String url,
                @JsonProperty("name") String name,
                @JsonProperty("logo") String logo
        ) {}

        public record City(
                @JsonProperty("geo") List<Double> geo,
                @JsonProperty("name") String name,
                @JsonProperty("url") String url,
                @JsonProperty("location") String location
        ) {}

        public record Iaqi(
                @JsonProperty("dew") Parameter dew,
                @JsonProperty("h") Parameter h,
                @JsonProperty("no2") Parameter no2,
                @JsonProperty("o3") Parameter o3,
                @JsonProperty("p") Parameter p,
                @JsonProperty("pm10") Parameter pm10,
                @JsonProperty("pm25") Parameter pm25,
                @JsonProperty("t") Parameter t,
                @JsonProperty("w") Parameter w,
                @JsonProperty("wg") Parameter wg
        ) {}

        public record Parameter(@JsonProperty("v") double v) {}

        public record Time(
                @JsonProperty("s") String s,
                @JsonProperty("tz") String tz,
                @JsonProperty("v") long v,
                @JsonProperty("iso") String iso
        ) {}

        public record Forecast(
                @JsonProperty("daily") DailyForecast daily
        ) {

            public record DailyForecast(
                    @JsonProperty("o3") List<ForecastValue> o3,
                    @JsonProperty("pm10") List<ForecastValue> pm10,
                    @JsonProperty("pm25") List<ForecastValue> pm25
            ) {}

            public record ForecastValue(
                    @JsonProperty("avg") int avg,
                    @JsonProperty("day") String day,
                    @JsonProperty("max") int max,
                    @JsonProperty("min") int min
            ) {}
        }
    }
}
