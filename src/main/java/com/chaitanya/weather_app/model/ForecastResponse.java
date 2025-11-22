package com.chaitanya.weather_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastResponse {

    private City city;
    private List<ForecastItem> list;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City {
        private String name;
        private Coord coord;
        private String country;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord {
        private double lat;
        private double lon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastItem {
        private long dt;

        // map JSON "dt_txt" to Java field dtTxt
        @JsonProperty("dt_txt")
        private String dtTxt;

        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Main {
            private double temp;
            @JsonProperty("feels_like")
            private double feelsLike;
            private int pressure;
            private int humidity;
            @JsonProperty("temp_min")
            private double tempMin;
            @JsonProperty("temp_max")
            private double tempMax;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Weather {
            private int id;
            private String main;
            private String description;
            private String icon;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Clouds {
            private int all;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Wind {
            private double speed;
            private int deg;
        }
    }
}
