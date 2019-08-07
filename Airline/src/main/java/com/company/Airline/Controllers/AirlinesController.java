package com.company.Airline.Controllers;

import com.company.Airline.Service.AirlinesService;
import com.company.Airline.dto.Airlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/airlines")
public class AirlinesController {
    @Autowired
    AirlinesService airlinesService;

    @PostMapping
    public Airlines addAirline(@RequestBody @Valid Airlines airline){
        airlinesService.addAirline(airline);
        return airline;
    }

    @GetMapping(value = "/{id}")
    public Airlines getAirline(@PathVariable int id) {
        return airlinesService.getAirline(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAirline(@PathVariable int id) {
        airlinesService.deleteAirline(id);
    }
}
