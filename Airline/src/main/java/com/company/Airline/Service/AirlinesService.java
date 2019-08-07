package com.company.Airline.Service;

import com.company.Airline.dao.AirlinesRepository;
import com.company.Airline.dto.Airlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirlinesService {

    @Autowired
    AirlinesRepository airlinesRepo;

    public Airlines addAirline(Airlines airline){
        airlinesRepo.save(airline);
        return airline;
    }
    public Airlines getAirline(int id){
        return airlinesRepo.getOne(id);
    }
    public void deleteAirline(int id) {
        airlinesRepo.deleteById(id);
    }
}
