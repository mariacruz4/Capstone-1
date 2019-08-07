package com.company.Airline.DaoTests;

import com.company.Airline.dao.AirportsRepository;
import com.company.Airline.dto.Airports;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportDaoTests {

    @Autowired
    AirportsRepository airportsRepo;

    Airports airport1;
    Airports airport2;

    @Before
    public void setUp(){

        airport1 = new Airports();
        airport1.setAirportName("Dallas/Fort Worth International Airport");
        airport1.setAirportAddress("2400 Aviation Dr, DFW Airport, TX 75261");

        airport2 = new Airports();
        airport2.setAirportName("Baltimore/Washington International Airport");
        airport2.setAirportAddress("Baltimore, MD 21240");
    }

    @Test
    @Transactional
    public void shouldGetAirportsById(){

        airportsRepo.save(airport1);

        airportsRepo.getOne(airport1.getAirportId());

        Optional<Airports> fromRepo = airportsRepo.findById(airport1.getAirportId());

        assertTrue(fromRepo.isPresent());

    }
    @After
    public void tearDown() {
        airportsRepo.deleteAll();
    }
}
