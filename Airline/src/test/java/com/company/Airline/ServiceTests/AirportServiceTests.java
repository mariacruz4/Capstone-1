package com.company.Airline.ServiceTests;

import com.company.Airline.Service.AirportsService;
import com.company.Airline.dao.AirportsRepository;
import com.company.Airline.dto.Airports;
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
public class AirportServiceTests {

    @Mock
    @Autowired
    AirportsRepository airportsRepoMock;

    @InjectMocks
    AirportsService airportsService;

    Airports airport1;
    Airports airport2;

    List<Airports> airportsList;

    @Before
    public void setUp(){
        airport1 = new Airports();
        airport1.setAirportName("Dallas/Fort Worth International Airport");
        airport1.setAirportAddress("2400 Aviation Dr, DFW Airport, TX 75261");

        airport2 = new Airports();
        airport2.setAirportName("Baltimore/Washington International Airport");
        airport2.setAirportAddress("Baltimore, MD 21240");

        airportsList = Arrays.asList(airport1,airport2);
    }

    @Test
    public void shouldGetAirport(){
        List<Airports> expectedList = Arrays.asList(airport2);
        List<Airports> airlinesList = Arrays.asList(airport2);

        when(airportsRepoMock.getOne(airport2.getAirportId())).thenReturn(airport2);

        assertEquals(airport2, airportsRepoMock.getOne(airport2.getAirportId()));
    }
}
