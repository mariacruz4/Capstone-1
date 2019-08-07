package com.company.Airline.DaoTests;

import com.company.Airline.dao.AirlinesRepository;
import com.company.Airline.dao.AirportsRepository;
import com.company.Airline.dao.FlightsRepository;
import com.company.Airline.dto.Airlines;
import com.company.Airline.dto.Airports;
import com.company.Airline.dto.Flights;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineDaoTests {

    @Autowired
    AirlinesRepository airlinesRepo;
    @Autowired
    FlightsRepository flightsRepo;
    @Autowired
    AirportsRepository airportsRepo;

    Flights flight1;
    Flights flight2;

    Airlines airline1;
    Airlines airline2;

    Airports airport1;
    Airports airport2;

    @Before
    public void setUp(){

        flight1 = new Flights();
        flight1.setFlightId(1);
        flight1.setFlightPrice(1500.00);
        flight1.setFlightDistance(2500.00);
        flight1.setAirline(airline1);
        flight1.setAirport(airport1);

        flight2 = new Flights();
        flight2.setFlightId(2);
        flight2.setFlightPrice(2000.00);
        flight2.setFlightDistance(5600.40);
        flight2.setAirline(airline2);
        flight2.setAirport(airport2);

        airline1 = new Airlines();
        airline1.setAirlineId(1);
        airline1.setAirlineName("American Airlines");

        airline2 = new Airlines();
        airline2.setAirlineId(2);
        airline2.setAirlineName("Southwest Airlines");

        airport1 = new Airports();
        airport1.setAirportId(1);
        airport1.setAirportName("Dallas/Fort Worth International Airport");
        airport1.setAirportAddress("2400 Aviation Dr, DFW Airport, TX 75261");

        airport2 = new Airports();
        airport2.setAirportId(2);
        airport2.setAirportName("Baltimore/Washington International Airport");
        airport2.setAirportAddress("Baltimore, MD 21240");
    }
    @Test
    @Transactional
    public void shouldAddAirlines(){
        airlinesRepo.save(airline1);
        airlinesRepo.save(airline2);

        assertNotNull(airline1.getAirlineId());
        assertNotNull(airline2.getAirlineId());
    }
    @Test
    @Transactional
    public void shouldGetAirlines(){

        airlinesRepo.save(airline1);

        airlinesRepo.getOne(airline1.getAirlineId());

        Optional<Airlines> fromRepo = airlinesRepo.findById(airline1.getAirlineId());

        assertTrue(fromRepo.isPresent());

    }
    @Test
    @Transactional
    public void shouldDeleteAirlines() {

        airlinesRepo.save(airline1);

        airlinesRepo.deleteById(airline1.getAirlineId());

        Optional<Airlines> fromRepo = airlinesRepo.findById(airline1.getAirlineId());

        assertFalse(fromRepo.isPresent());
    }

    @After
    public void tearDown() {
        flightsRepo.deleteAll();
        airlinesRepo.deleteAll();
        airportsRepo.deleteAll();
    }
}

