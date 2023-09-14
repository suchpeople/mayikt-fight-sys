package com.zhang.dao;

import com.zhang.entity.FlightEntity;
import com.zhang.utils.MayiktJdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author z2545
 * @date 2023/9/12 23:51
 * @description FlightDao
 */
public class FlightDao {
    /**
     * 获取全部航班信息
     *
     * @return
     */
    public List<FlightEntity> getByAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from mayikt_flight where is_delete=0 ");
            resultSet = preparedStatement.executeQuery();
            ArrayList<FlightEntity> flightEntities = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String flightId = resultSet.getString("flight_id");
                String company = resultSet.getString("company");
                String departureAirport = resultSet.getString("departure_airport");
                String arriveAirport = resultSet.getString("arrive_airport");
                Date departureTime = resultSet.getDate("departure_time");
                Date arriveTime = resultSet.getDate("arrive_time");
                String model = resultSet.getString("model");
                int isDelete = resultSet.getInt("is_delete");
                flightEntities.add(new FlightEntity(id, flightId, company, departureAirport,
                        arriveAirport, departureTime, arriveTime, model, isDelete));
            }
            return flightEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 根据序号删除航班信息
     * <p>
     * !!!增删改需要回滚
     */
    public int deleteFlight(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            MayiktJdbcUtils.beginTransaction(connection);//开启事务
            preparedStatement = connection.prepareStatement("delete from mayikt_flight where id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            MayiktJdbcUtils.commitTransaction(connection);//代码无问题的情况下 直接接提交事务
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            MayiktJdbcUtils.rollBackTransaction(connection);//代码有问题 回滚事务
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

    /**
     * 根据id查询航班信息
     *
     * @return
     */
    public FlightEntity getByIdFlight(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 1.获取连接
            connection = MayiktJdbcUtils.getConnection();
            //2.获取执行者对象
            preparedStatement = connection.prepareStatement("select * from mayikt_flight where id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            // 连接到db查询数据
            Integer dbId = resultSet.getInt("id");
            String flightId = resultSet.getString("flight_id");
            String company = resultSet.getString("company");
            String departureAirport = resultSet.getString("departure_airport");
            String arriveAirport = resultSet.getString("arrive_airport");
            Date departureTime = resultSet.getDate("departure_time");
            Date arriveTime = resultSet.getDate("arrive_time");
            String model = resultSet.getString("model");
            Integer isDelete = resultSet.getInt("is_delete");
            FlightEntity flightEntity = new FlightEntity(dbId, flightId, company, departureAirport, arriveAirport, departureTime, arriveTime, model,
                    isDelete);
            return flightEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 释放资源
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 更新航班信息
     *
     * @param flightEntity
     * @return
     */
    public int updateFlight(FlightEntity flightEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            MayiktJdbcUtils.beginTransaction(connection);//开启事务
            preparedStatement = connection.prepareStatement("UPDATE `flight`.`mayikt_flight` SET `flight_id` = ?, `company` = ?, `departure_airport` = ?, `arrive_airport` = ?, `departure_time` = ?, `arrive_time` = ?, `model` = ?, `is_delete` = 0 WHERE `id` = ?;");
            preparedStatement.setString(1, flightEntity.getFlightId());
            preparedStatement.setString(2, flightEntity.getCompany());
            preparedStatement.setString(3, flightEntity.getDepartureAirport());
            preparedStatement.setString(4, flightEntity.getArriveAirport());
            preparedStatement.setDate(5, new Date(flightEntity.getDepartureTime().getTime()));
            preparedStatement.setDate(6, new Date(flightEntity.getArriveTime().getTime()));
            preparedStatement.setString(7, flightEntity.getModel());
            preparedStatement.setInt(8, flightEntity.getId());
            int result = preparedStatement.executeUpdate();

            MayiktJdbcUtils.commitTransaction(connection);//代码无问题的情况下 直接提交事务
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            MayiktJdbcUtils.rollBackTransaction(connection);//代码有问题 回滚事务
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

    public int insertFlight(FlightEntity flightEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MayiktJdbcUtils.getConnection();
            MayiktJdbcUtils.beginTransaction(connection);
            preparedStatement = connection.prepareStatement("INSERT INTO `flight`.`mayikt_flight` (`id`, `flight_id`, `company`, " +
                    "`departure_airport`, `arrive_airport`, `departure_time`, `arrive_time`, `model`, `is_delete`)" +
                    " VALUES (null, ?, ?, ?, ?, ?, ?, ?, 0);");
            preparedStatement.setString(1, flightEntity.getFlightId());
            preparedStatement.setString(2, flightEntity.getCompany());
            preparedStatement.setString(3, flightEntity.getDepartureAirport());
            preparedStatement.setString(4, flightEntity.getArriveAirport());
            preparedStatement.setDate(5, new Date(flightEntity.getDepartureTime().getTime()));
            preparedStatement.setDate(6, new Date(flightEntity.getArriveTime().getTime()));
            preparedStatement.setString(7, flightEntity.getModel());
            int result = preparedStatement.executeUpdate();
            MayiktJdbcUtils.commitTransaction(connection); // 无问题直接提交事务
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            MayiktJdbcUtils.rollBackTransaction(connection);//有问题回滚事务
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement,connection);
        }

    }

    /**
     * 逻辑删除
     */
    public int updateDelete(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            MayiktJdbcUtils.beginTransaction(connection);//开启事务
            preparedStatement = connection.prepareStatement("UPDATE `flight`.`mayikt_flight` SET `is_delete` = 1 WHERE `id` = ?;");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();

            MayiktJdbcUtils.commitTransaction(connection);//代码无问题的情况下 直接提交事务
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            MayiktJdbcUtils.rollBackTransaction(connection);//代码有问题 回滚事务
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

}
