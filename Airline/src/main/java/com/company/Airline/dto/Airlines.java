package com.company.Airline.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="airlines")
public class Airlines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer airlineId;
    @NotNull
    private String airlineName;

    @OneToMany(mappedBy="airline", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<Flights> flights;

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Set<Flights> getFlights(){
        return flights;
    }
    public void setFlights(Set<Flights> flights){
        this.flights = flights;
    }
}


