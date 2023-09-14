package com.zhang.service;

import com.zhang.dao.FlightDao;
import com.zhang.entity.FlightEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author z2545
 * @date 2023/9/13 0:36
 * @description FlightService
 */
public class FlightService {
    private FlightDao flightDao = new FlightDao();
    public List<FlightEntity> getByAll(){
        return flightDao.getByAll();
    }
    public int deleteFlight(Integer id){
        return flightDao.deleteFlight(id);
    }
    public FlightEntity getByIdFlight(Integer id){
        return flightDao.getByIdFlight(id);
    }
    public int updateFlight(FlightEntity flightEntity){
        return flightDao.updateFlight(flightEntity);
    }
    public int insertFlight(FlightEntity flightEntity){
        return flightDao.insertFlight(flightEntity);
    }
    public int updateDelete(Integer id){
        return flightDao.updateDelete(id);
    }
}
