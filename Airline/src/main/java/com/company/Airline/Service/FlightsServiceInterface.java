package com.company.Airline.Service;

import com.company.Airline.dto.Flights;

import java.util.List;

public interface FlightsServiceInterface {

    Flights addFlight(Flights flight);
    List<Flights> getAllFlights();
    Flights getFlight(int id);
    List<Flights> getFlightsByDistance(Double flightDistance);
    List<Flights> getFlightsByPrice(Double flightPrice);
    Double getFlightsPriceByFlightsDistance(Double flightDistance, Double flightPrice, int id);
    List<Flights> getFlightPriceByAirportId(Double flightPrice, Integer airportId1, Integer airportId2);
    List<Flights> getFlightDistanceByAirportId(Double flightDistance, Integer airportId1, Integer airportId2);
    List<Flights> getFlightByAirlineIdAndAirportId(Integer airlineId, Integer airportId);

}

