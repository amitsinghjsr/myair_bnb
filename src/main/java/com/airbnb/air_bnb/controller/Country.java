package com.airbnb.air_bnb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this class is dummy data for example
@RestController
@RequestMapping("/api/v1/countries")
public class Country {

    @PostMapping("/addCountry")
    public String addCountry(){
        return "done";
    }
}
