package com.company.Airline.Controllers;

import com.company.Airline.Service.AirportsService;
import com.company.Airline.dto.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/airports")
public class AirportsController {

    @Autowired
    AirportsService airportsService;

    @PostMapping
    public Airports addAirport(@RequestBody @Valid Airports airport){
        airportsService.addAirport(airport);
        return airport;
    }
    @GetMapping(value = "/{id}")
    public Airports getAirport(@PathVariable int id) {
        return airportsService.getAirport(id);
    }
}
