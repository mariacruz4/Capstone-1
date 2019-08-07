package com.company.Airline.Controllers;

import com.company.Airline.Service.FlightsService;
import com.company.Airline.dto.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/flights")
public class FlightsController {

    @Autowired
    FlightsService flightsService;

    @PostMapping
    public Flights addFlight(@RequestBody @Valid Flights flight){
        flightsService.addFlight(flight);
        return flight;
    }
    @GetMapping
    public List<Flights> getAllFlights() {
        return flightsService.getAllFlights();
    }
    @GetMapping(value = "/{id}")
    public Flights getFlight(@PathVariable int id) {
        return flightsService.getFlight(id);
    }

    @GetMapping(value = "/distance/{flightDistance}")
    public List<Flights> getFlightsByDistance(@PathVariable Double flightDistance){
        return flightsService.getFlightsByDistance(flightDistance);
    }

    @GetMapping(value = "/price/{flightPrice}")
    public List<Flights> getFlightsByPrice(@PathVariable Double flightPrice){
        return flightsService.getFlightsByPrice(flightPrice);
    }

    @GetMapping(value = "/price/distance/{flightDistance}")
    public Double getFlightsPriceByFlightsDistance(@PathVariable Double flightDistance, Double flightPrice, Integer id){
        return flightsService.getFlightsPriceByFlightsDistance(flightDistance, flightPrice, id);
    }

    @GetMapping(value = "/price/{airportId1}/{airportId2}")
    public List<Flights> getFlightPriceByAirportId(Double flightPrice, @PathVariable Integer airportId1, @PathVariable Integer airportId2){
        return flightsService.getFlightPriceByAirportId(flightPrice, airportId1, airportId2);
    }
    @GetMapping(value = "/distance/{airportId1}/{airportId2}")
    public List<Flights> getFlightDistanceByAirportId(Double flightDistance, @PathVariable Integer airportId1, @PathVariable Integer airportId2){
        return flightsService.getFlightDistanceByAirportId(flightDistance, airportId1, airportId2);
    }
    @GetMapping(value = "/airport/{airportId}/airline/{airlineId}")
    public List<Flights> getFlightsByAirlineIdAndAirportId(@PathVariable Integer airlineId, @PathVariable Integer airportId){
        return flightsService.getFlightByAirlineIdAndAirportId(airlineId, airportId);
    }
}
