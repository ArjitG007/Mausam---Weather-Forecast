package com.example.Mausam;

import com.example.Mausam.Model.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class MausamController {

    private final MausamService mausamService;

    public MausamController(MausamService mausamService) {
        this.mausamService = mausamService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {

        try {
            Weather response = mausamService.call(city);

            System.out.println(response);

            if (response == null || response.getLocation() == null) {
                model.addAttribute("errorMessage", "City not found.");
                return "index";
            }

            int aqi = 1; // default good

            if (response.getCurrent() != null &&
                    response.getCurrent().getAir_quality() != null) {

                aqi = response.getCurrent()
                        .getAir_quality()
                        .getUsEpaIndex();
            }

            String aqiStatus;
            String aqiColor;

            switch (aqi) {
                case 1 -> {
                    aqiStatus = "Good";
                    aqiColor = "bg-success";
                }
                case 2 -> {
                    aqiStatus = "Moderate";
                    aqiColor = "bg-warning";
                }
                case 3 -> {
                    aqiStatus = "Unhealthy (Sensitive)";
                    aqiColor = "bg-orange";
                }
                case 4 -> {
                    aqiStatus = "Unhealthy";
                    aqiColor = "bg-danger";
                }
                case 5 -> {
                    aqiStatus = "Very Unhealthy";
                    aqiColor = "bg-danger";
                }
                default -> {
                    aqiStatus = "Hazardous";
                    aqiColor = "bg-dark";
                }
            }

            model.addAttribute("aqiStatus", aqiStatus);
            model.addAttribute("aqiColor", aqiColor);
            model.addAttribute("aqiValue", aqi * 50); // convert index to visual scale

            model.addAttribute("weather", response);
            return "weather";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "City not found.");
            return "index";
        }



    }
}
