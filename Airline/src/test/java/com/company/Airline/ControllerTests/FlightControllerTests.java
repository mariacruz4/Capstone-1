package com.company.Airline.ControllerTests;

import com.company.Airline.Controllers.FlightsController;
import com.company.Airline.Service.FlightsService;
import com.company.Airline.dto.Airlines;
import com.company.Airline.dto.Airports;
import com.company.Airline.dto.Flights;
import org.junit.Before;
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

public class FlightControllerTests {

    private MockMvc mockMvc;

    @Mock
    FlightsService mockFlightsService;

    @InjectMocks
    FlightsController flightsController;

    Flights flight1;
    Flights flight2;
    Flights flight3;

    Airlines airline1;
    Airlines airline2;

    Airports airport1;
    Airports airport2;

    List<Flights> flightsList;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightsController).build();

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

        flight1 = new Flights();
        flight1.setFlightId(1);
        flight1.setFlightTotalTime("02:50:00");
        flight1.setFlightPrice(250.00);
        flight1.setFlightDistance(2500.00);
        flight1.setFlightDelay(true);
        flight1.setAirport(airport1);
        flight1.setAirline(airline1);

        flight2 = new Flights();
        flight2.setFlightId(2);
        flight2.setFlightTotalTime("02:00:00");
        flight2.setFlightPrice(200.00);
        flight2.setFlightDistance(5600.40);
        flight2.setFlightDelay(false);
        flight2.setAirline(airline1);
        flight2.setAirport(airport2);

        flight3 = new Flights();
        flight3.setFlightId(3);
        flight3.setFlightTotalTime("06:00:00");
        flight3.setFlightPrice(1000.00);
        flight3.setFlightDistance(7500.50);
        flight3.setFlightDelay(false);
        flight3.setAirline(airline2);
        flight3.setAirport(airport2);

        flightsList = Arrays.asList(flight1,flight2, flight3);
    }
    @Test
    public void rootContext_ShouldRespondWith404() throws Exception {


        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());

    }
    @Test
    public void ShouldReturnAllFlights() throws Exception {
        flightsList = Arrays.asList(flight1,flight2, flight3);
        when(mockFlightsService.getAllFlights()).thenReturn(flightsList);


        mockMvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].flightId", is(flightsList.get(0).getFlightId())));


        verify(mockFlightsService).getAllFlights();

    }
    /*@Test
    public void shouldReturnFlight() throws Exception{
        flightsList = Arrays.asList(flight1);
        when(mockFlightsService.getFlight(flightsList.get(0).getFlightId())).thenReturn(flight1);


        mockMvc.perform(get("/flights/{id}" + flightsList.get(0).getFlightId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0][flightId]").value(flight1.getFlightId()))
                .andReturn();

        verify(mockFlightsService).getFlight(flightsList.get(0).getFlightId());
    }
    @Test
    public void shouldReturnFlightByDistance() throws Exception{
        flightsList = Arrays.asList(flight2);
        when(mockFlightsService.getFlightsByDistance(flightsList.get(0).getFlightDistance())).thenReturn(flightsList);


        mockMvc.perform(get("/flights/distance/{distance}" + flightsList.get(0).getFlightDistance()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].distance", is(flightsList.get(0).getFlightDistance())));

        verify(mockFlightsService).getFlightsByDistance(flightsList.get(0).getFlightDistance());
    }
    @Test
    public void shouldReturnFlightByPrice() throws Exception{
        flightsList = Arrays.asList(flight2);
        when(mockFlightsService.getFlightsByPrice(flightsList.get(0).getFlightPrice())).thenReturn(flightsList);


        mockMvc.perform(get("/flights/price/{price}" + flightsList.get(0).getFlightPrice()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].price", is(flightsList.get(0).getFlightPrice())));

        verify(mockFlightsService).getFlightsByPrice(flightsList.get(0).getFlightPrice());
    }

    @Test
    public void shouldReturnFlightByAirlineIdAndAirportId() throws Exception{
        flightsList = Arrays.asList(flight1, flight2);
        when(mockFlightsService
                .getFlightByAirlineIdAndAirportId(flightsList.get(0).getAirlineId(), flightsList.get(0).getAirportId()))
                .thenReturn(flightsList);


        mockMvc.perform(get("/flights/airlines/{id}/airport/{id}" + flightsList.get(0).getAirlineId() + "/" + flightsList.get(0).getAirportId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].flight", is(flightsList.get(1).getFlightId())));

        verify(mockFlightsService).getFlightByAirlineIdAndAirportId(flightsList.get(0).getAirlineId(), flightsList.get(0).getAirportId());
    }*/
}
