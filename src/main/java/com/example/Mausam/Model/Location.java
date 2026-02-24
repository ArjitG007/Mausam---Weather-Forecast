package com.example.Mausam.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Location {

    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    //private LocalDateTime localtime;




}
