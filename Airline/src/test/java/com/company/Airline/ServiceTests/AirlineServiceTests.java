package com.company.Airline.ServiceTests;

import com.company.Airline.Service.AirlinesService;
import com.company.Airline.dao.AirlinesRepository;
import com.company.Airline.dto.Airlines;
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
public class AirlineServiceTests {

    @Mock
    @Autowired
    AirlinesRepository airlinesRepoMock;

    @InjectMocks
    AirlinesService airlinesService;

    Airlines airline1;
    Airlines airline2;

    List<Airlines> airlinesList;

    @Before
    public void setUp(){
        airline1 = new Airlines();
        airline1.setAirlineName("American Airlines");

        airline2 = new Airlines();
        airline2.setAirlineName("Southwest Airlines");

        airlinesList = Arrays.asList(airline1,airline2);
    }

    @Test
    public void shouldGetAirline(){
        List<Airlines> expectedList = Arrays.asList(airline2);
        List<Airlines> airlinesList = Arrays.asList(airline2);

        when(airlinesRepoMock.getOne(airline2.getAirlineId())).thenReturn(airline2);

        assertEquals(airline2, airlinesRepoMock.getOne(airline2.getAirlineId()));
    }
}

