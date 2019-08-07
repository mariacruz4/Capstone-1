package com.company.Airline.Service;

import com.company.Airline.dao.FlightsRepository;
import com.company.Airline.dto.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class FlightsService implements FlightsServiceInterface {
    @Autowired
    FlightsRepository flightsRepo;

    @Override
    public Flights addFlight(Flights flight){
        flightsRepo.save(flight);
        return flight;
    }
    @Override
    public List<Flights> getAllFlights() {

        return flightsRepo.findAll();
    }
    @Override
    public Flights getFlight(int id){
        return flightsRepo.getOne(id);
    }
    @Override
    public List<Flights> getFlightsByDistance(Double flightDistance){
        return flightsRepo.findByFlightDistance(flightDistance);
    }
    @Override
    public List<Flights> getFlightsByPrice(Double flightPrice){
        return flightsRepo.findByFlightPrice(flightPrice);
    }
    @Override
    public Double getFlightsPriceByFlightsDistance(Double flightDistance, Double flightPrice, int id){
        flightPrice = 0.0;

        if(flightDistance <= 500.00){
            Flights flight = flightsRepo.getOne(id);
            flightPrice = flight.getFlightPrice();
        }else if (flightDistance > 500.00 && flightDistance <= 1000.00){
            Flights flight = flightsRepo.getOne(id);
            flightPrice = flight.getFlightPrice();
        }else if (flightDistance > 1000.00 && flightDistance <= 2000.00){
            Flights flight = flightsRepo.getOne(id);
            flightPrice = flight.getFlightPrice();
        }else{
            Flights flight = flightsRepo.getOne(id);
            flightPrice = flight.getFlightPrice();
        }
        return flightsRepo.findFlightsPriceByFlightDistance(flightPrice);
    }
    @Override
    public List<Flights> getFlightPriceByAirportId(Double flightPrice, Integer airportId1, Integer airportId2) {
        List<Flights> flightsList = new ArrayList<>();

        for (Flights flight : flightsRepo.findAll()) {
            Integer currId = flight.getAirportId();
            if (currId.equals(airportId1) || currId.equals(airportId2)) {
                flightsList.add(flight);
            }
        }
        flightsList.sort(Comparator.comparingDouble(Flights::getFlightPrice));
        return flightsList;
    }

    @Override
    public List<Flights> getFlightDistanceByAirportId(Double flightDistance, Integer airportId1, Integer airportId2){
        List<Flights> flightsList = new ArrayList<>();

        for (Flights flight : flightsRepo.findAll()) {
            Integer currId = flight.getAirportId();
            if (currId.equals(airportId1) || currId.equals(airportId2)) {
                flightsList.add(flight);
            }
        }
        flightsList.sort(Comparator.comparingDouble(Flights::getFlightDistance));
        return flightsList;
    }
    @Override
    public List<Flights> getFlightByAirlineIdAndAirportId(Integer airlineId, Integer airportId){
        return flightsRepo.findByAirlineIdAndAirportId(airlineId, airportId);
    }
}
