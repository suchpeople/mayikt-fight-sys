package com.zhang.entity;

import java.util.Date;

/**
 * @author z2545
 * @date 2023/9/12 19:39
 * @description FlightEntity
 */
public class FlightEntity {
    private Integer id;
    private String flightId;
    private String company;
    private String departureAirport;
    private String arriveAirport;
    private Date departureTime;
    private Date arriveTime;
    private String model;
    private Integer isDelete;

    //无参构造方法
    public FlightEntity() {

    }
    //有参构造方法

    public FlightEntity(Integer id, String flightId, String company, String departureAirport, String arriveAirport, Date departureTime, Date arriveTime, String model, Integer isDelete) {
        this.id = id;
        this.flightId = flightId;
        this.company = company;
        this.departureAirport = departureAirport;
        this.arriveAirport = arriveAirport;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.model = model;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
