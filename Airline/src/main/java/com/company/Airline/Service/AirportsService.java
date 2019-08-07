package com.company.Airline.Service;

import com.company.Airline.dao.AirportsRepository;
import com.company.Airline.dto.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportsService {

    @Autowired
    AirportsRepository airportsRepo;

    public Airports addAirport(Airports airport){
        airportsRepo.save(airport);
        return airport;
    }
    public Airports getAirport(int id){
        return airportsRepo.getOne(id);
    }
}
