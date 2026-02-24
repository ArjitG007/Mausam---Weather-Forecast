package com.example.Mausam.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {

    @JsonProperty("temp_c")
    private Double tempCalcius;

    @JsonProperty("temp_f")
    private Double tempFarenheit;

    @JsonProperty("is_day")
    private int isDay;

    private Condition condition;

    @JsonProperty("wind_dir")
    private String windDirection;

    @JsonProperty("wind_kph")
    private Double windSpeed;

    private String humidity;

    private AQI air_quality;


}
