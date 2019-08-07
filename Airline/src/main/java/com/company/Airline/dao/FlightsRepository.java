package com.company.Airline.dao;

import com.company.Airline.dto.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights, Integer> {
    List<Flights> findByFlightDistance(Double flightDistance);
    List<Flights> findByFlightPrice(Double flightPrice);
    Double findFlightsPriceByFlightDistance(Double flightPrice);
    List<Flights> findByAirlineIdAndAirportId(Integer airlineId, Integer airportId);
}
