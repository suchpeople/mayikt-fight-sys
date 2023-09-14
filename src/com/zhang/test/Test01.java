package com.zhang.test;

import com.zhang.entity.FlightEntity;
import com.zhang.service.FlightService;

import java.util.Date;

/**
 * @author z2545
 * @date 2023/9/13 19:13
 * @description Test01
 */
public class Test01 {
    public static void main(String[] args) {
        FlightService flightService = new FlightService();
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(11);
        flightEntity.setFlightId("111");
        flightEntity.setCompany("111");
        flightEntity.setDepartureAirport("111");
        flightEntity.setArriveAirport("111");
        flightEntity.setDepartureTime(new Date());
        flightEntity.setArriveTime(new Date());
        flightEntity.setModel("111");
        int result = flightService.updateFlight(flightEntity);
        System.out.println(result);


    }
}
