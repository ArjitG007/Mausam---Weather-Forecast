package com.example.Mausam.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AQI
{
    private double co;
    private double no2;
    private double o3;
    private double so2;

    @JsonProperty("pm2_5")
    private double pm25;

    private double pm10;

    @JsonProperty("us-epa-index")
    private int usEpaIndex;

    @JsonProperty("gb-defra-index")
    private int gbDefraIndex;

}
