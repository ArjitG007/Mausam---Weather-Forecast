package com.example.Mausam;

import com.example.Mausam.Model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MausamService {

    @Value("${api.key}")
    private String Key;

    @Value("${api.baseURL}")
    private String BaseURL;

    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("https://api.weatherapi.com/v1")
                    .build();

    public Weather call(String city)
    {
        Weather response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", Key)
                        .queryParam("q", city)
                        .queryParam("aqi", "yes")
                        .build())
                .retrieve()
                .bodyToMono(Weather.class)
                .block();

        System.out.println(response);

        return response;

    }
}