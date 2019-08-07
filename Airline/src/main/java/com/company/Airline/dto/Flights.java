package com.company.Airline.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightId;
    @NotNull
    private String flightTotalTime;
    @NotNull
    private Double flightPrice;
    @NotNull
    private Double flightDistance;
    @NotNull
    private Boolean flightDelay;

    @NotNull
    private Integer airlineId;
    @NotNull
    private Integer airportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airportId", updatable = false, insertable = false)
    private Airports airport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airlineId", updatable = false, insertable = false)
    private Airlines airline;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightTotalTime() {
        return flightTotalTime;
    }

    public void setFlightTotalTime(String flightTotalTime) {
        this.flightTotalTime = flightTotalTime;
    }

    public Double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Double getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Boolean getFlightDelay() {
        return flightDelay;
    }

    public void setFlightDelay(Boolean flightDelay) {

        this.flightDelay = flightDelay;
    }
    public Airlines getAirline() {
        return airline;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public Airports getAirport() {
        return airport;
    }

    public void setAirport(Airports airport) {
        this.airport = airport;
    }

    public Double delayPrice(){
        if(flightDelay.equals(true)){
            setFlightPrice(flightPrice - 50.00);
            return flightPrice;

        }return flightPrice;
    }
    public String delayTime(){
        if(flightDelay.equals(true)){
            setFlightTotalTime(Integer.toString(Integer.parseInt(flightTotalTime) + 004500));
            return flightTotalTime;
        }return flightTotalTime;
    }


   /* public boolean delay(){
        flightDelay = new Random();
        return flightDelay.nextBoolean();
    }

    public Double delayPrice(){
        if(flightDelay.equals(true)){
            setFlightPrice(flightPrice - 50.00);
            return flightPrice;
        }
        return flightPrice;
    }
    public String delayTime(){
        if(flightDelay.equals(true)){
        setFlightTotalTime(
        */
}
