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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDaoTest {

    @Autowired
    FlightsRepository flightsRepo;
    @Autowired
    AirlinesRepository airlinesRepo;
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
        flight1.setFlightTotalTime("01:30:00");
        flight1.setFlightPrice(1500.00);
        flight1.setFlightDistance(2500.00);
        flight1.setFlightDelay(false);
        flight1.setAirlineId(1);
        flight1.setAirportId(1);

        flight2 = new Flights();
        flight2.setFlightId(2);
        flight2.setFlightTotalTime("02:30:00");
        flight2.setFlightPrice(2000.00);
        flight2.setFlightDistance(5600.40);
        flight2.setFlightDelay(false);
        flight2.setAirlineId(2);
        flight2.setAirportId(2);

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
    public void shouldAddFlights(){
        flight1.setAirline(airline1);
        flight2.setAirline(airline2);
        flight1.setAirport(airport1);
        flight2.setAirport(airport2);
        flightsRepo.save(flight1);
        flightsRepo.save(flight2);

        assertNotNull(flight1.getFlightId());
        assertNotNull(flight2.getFlightId());
    }
    @Test
    @Transactional
    public void shouldGetFlights() {

        flightsRepo.save(flight1);

        flightsRepo.getOne(flight1.getFlightId());

        Optional<Flights> fromRepo = flightsRepo.findById(flight1.getFlightId());

        assertTrue(fromRepo.isPresent());
    }
    @Test
    @Transactional
    public void shouldGetFlightByDistance(){
        airportsRepo.save(airport1);
        airlinesRepo.save(airline1);
        flight1.setAirport(airport1);
        flight1.setAirline(airline1);
        flightsRepo.save(flight1);

        List<Flights> flightsList = flightsRepo.findByFlightDistance(flight1.getFlightDistance());

        assertEquals(flight1, flightsList.get(1));
    }
    @Test
    @Transactional
    public void shouldGetFlightByPrice(){
        airportsRepo.save(airport1);
        airlinesRepo.save(airline1);
        flight1.setAirport(airport1);
        flight1.setAirline(airline1);
        flightsRepo.save(flight1);

        airlinesRepo.save(airline2);
        airportsRepo.save(airport2);
        flight2.setAirline(airline2);
        flight2.setAirport(airport2);
        flightsRepo.save(flight2);

        List<Flights> flightsList = flightsRepo.findByFlightPrice(flight1.getFlightPrice());

        assertEquals(flight1, flightsList.get(1));
    }

    @Test
    @Transactional
    public void shouldGetFlightByAirlineIdAndAirportId(){
        airportsRepo.save(airport1);
        airlinesRepo.save(airline1);
        flight1.setAirport(airport1);
        flight1.setAirline(airline1);
        flightsRepo.save(flight1);

        airlinesRepo.save(airline2);
        airportsRepo.save(airport2);
        flight2.setAirline(airline2);
        flight2.setAirport(airport2);
        flightsRepo.save(flight2);

        List<Flights> flightsList = flightsRepo.findByAirlineIdAndAirportId
                (flight1.getAirlineId(), flight1.getAirportId());

        assertEquals(flight1, flightsList.get(1));
    }
    @After
    public void tearDown() {
        airportsRepo.deleteAll();
        airlinesRepo.deleteAll();
        flightsRepo.deleteAll();
    }
}
