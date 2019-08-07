package com.company.Airline.ControllerTests;

import com.company.Airline.Controllers.AirlinesController;
import com.company.Airline.Service.AirlinesService;
import com.company.Airline.dto.Airlines;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AirlineControllerTests {


    private MockMvc mockMvc;

    @Mock
    AirlinesService mockAirlinesService;

    @InjectMocks
    AirlinesController airlinesController;

    Airlines airline1;
    Airlines airline2;

    List<Airlines> airlinesList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airlinesController).build();

        airline1 = new Airlines();
        airline1.setAirlineName("American Airlines");

        airline2 = new Airlines();
        airline2.setAirlineName("Southwest Airlines");

        airlinesList = Arrays.asList(airline1, airline2);
    }

    @Test
    public void rootContext_ShouldRespondWith404() throws Exception {


        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldAddAirline() throws Exception{

        Airlines airline = new Airlines();

        airline.setAirlineName("Avianca");

        when(mockAirlinesService.addAirline(airline)).thenReturn(airline);

        mockMvc.perform(post("/airlines")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnAirline() throws Exception{
        airlinesList = Arrays.asList(airline1);
        when(mockAirlinesService.getAirline(airlinesList.get(0).getAirlineId())).thenReturn(airline1);


        mockMvc.perform(get("/airlines/{id}" + airlinesList.get(0).getAirlineName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(airlinesList.get(0).getAirlineId())));

        verify(mockAirlinesService).getAirline(airlinesList.get(0).getAirlineId());
    }
}

