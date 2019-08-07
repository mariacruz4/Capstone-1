package com.company.Airline.ServiceTests;

import com.company.Airline.Service.FlightsService;
import com.company.Airline.dao.FlightsRepository;
import com.company.Airline.dto.Airlines;
import com.company.Airline.dto.Airports;
import com.company.Airline.dto.Flights;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTests {

    @Mock
    @Autowired
    FlightsRepository flightsRepoMock;

    @InjectMocks
    FlightsService flightsService;

    Flights flight1;
    Flights flight2;

    Airlines airline1;
    Airlines airline2;

    Airports airport1;
    Airports airport2;

    List<Flights> flightsList;

    @Before
    public void setUp(){
        flight1 = new Flights();
        flight1.setFlightId(1);
        flight1.setFlightTotalTime("02:50:00");
        flight1.setFlightPrice(1500.00);
        flight1.setFlightDistance(2500.00);
        flight1.setAirline(airline1);
        flight1.setAirport(airport1);

        flight2 = new Flights();
        flight2.setFlightId(2);
        flight2.setFlightTotalTime("02:00:00");
        flight2.setFlightPrice(2000.00);
        flight2.setFlightDistance(5600.40);
        flight2.setAirline(airline2);
        flight2.setAirport(airport2);

        airline1 = new Airlines();
        airline1.setAirlineName("American Airlines");

        airline2 = new Airlines();
        airline2.setAirlineName("Southwest Airlines");

        airport1 = new Airports();
        airport1.setAirportName("Dallas/Fort Worth International Airport");
        airport1.setAirportAddress("2400 Aviation Dr, DFW Airport, TX 75261");

        airport2 = new Airports();
        airport2.setAirportName("Baltimore/Washington International Airport");
        airport2.setAirportAddress("Baltimore, MD 21240");

        flightsList = Arrays.asList(flight1,flight2);
    }

    @Test
    public void shouldGetFlightById(){


        when(flightsRepoMock.getOne(flight1.getFlightId())).thenReturn(flight1);

        assertEquals(flight1, flightsService.getFlight(flight1.getFlightId()));
    }
    @Test
    public void shouldGetFlightsByDistance(){
        List<Flights> expectedList = Arrays.asList(flight2);
        List<Flights> flightsList = Arrays.asList(flight2);

        when(flightsRepoMock.findByFlightDistance(flight2.getFlightDistance())).thenReturn(flightsList);

        assertEquals(expectedList, flightsService.getFlightsByDistance(flight2.getFlightDistance()));
    }
    @Test
    public void shouldGetFlightsByPrice(){
        List<Flights> expectedList = Arrays.asList(flight1);
        List<Flights> flightsList = Arrays.asList(flight1);

        when(flightsRepoMock.findByFlightPrice(flight1.getFlightPrice())).thenReturn(flightsList);

        assertEquals(expectedList, flightsService.getFlightsByPrice(flight1.getFlightPrice()));
    }

    @Test
    public void shouldGetFlightsByAirlineIdAndAirportId(){
        List<Flights> expectedList = Arrays.asList(flight2);
        List<Flights> flightsList = Arrays.asList(flight2);

        when(flightsRepoMock.findByAirlineIdAndAirportId(flight2.getAirlineId(), flight2.getAirportId())).thenReturn(flightsList);

        assertEquals(expectedList, flightsService.getFlightByAirlineIdAndAirportId(flight2.getAirlineId(), flight2.getAirportId()));
    }
}
