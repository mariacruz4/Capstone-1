package com.company.Airline.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="airports")
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer airportId;
    @NotNull
    private String airportName;
    @NotNull
    private String airportAddress;

    @OneToMany(mappedBy="airport", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<Flights> flights;

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportAddress() {
        return airportAddress;
    }

    public void setAirportAddress(String airportAddress) {
        this.airportAddress = airportAddress;
    }

    public Set<Flights> getFlights(){
        return flights;
    }
    public void setFlights(Set<Flights> flights) {
        this.flights = flights;
    }
}


