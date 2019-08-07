package com.company.Airline.ControllerTests;

import com.company.Airline.Controllers.AirportsController;
import com.company.Airline.Service.AirportsService;
import com.company.Airline.dto.Airports;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AirportControllerTests {

    private MockMvc mockMvc;

    @Mock
    AirportsService mockAirportsService;

    @InjectMocks
    AirportsController airportsController;

    Airports airport1;
    Airports airport2;

    List<Airports> airportsList;

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportsController).build();

        airport1 = new Airports();
        airport1.setAirportName("Dallas/Fort Worth International Airport");
        airport1.setAirportAddress("2400 Aviation Dr, DFW Airport, TX 75261");

        airport2 = new Airports();
        airport2.setAirportName("Baltimore/Washington International Airport");
        airport2.setAirportAddress("Baltimore, MD 21240");

        airportsList = Arrays.asList(airport1, airport2);

    }

    @Test
    public void rootContext_ShouldRespondWith404() throws Exception {


        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldReturnFlight() throws Exception{
        airportsList = Arrays.asList(airport1);
        when(mockAirportsService.getAirport(airportsList.get(0).getAirportId())).thenReturn(airport1);


        mockMvc.perform(get("/airports/{id}" + airportsList.get(0).getAirportId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(airportsList.get(0).getAirportId())));

        verify(mockAirportsService).getAirport(airportsList.get(0).getAirportId());
    }
}