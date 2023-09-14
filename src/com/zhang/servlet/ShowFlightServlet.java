package com.zhang.servlet;

import com.zhang.entity.FlightEntity;
import com.zhang.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author z2545
 * @date 2023/9/13 0:40
 * @description FlightServlet
 */
@WebServlet("/showFlight")
public class ShowFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.控制层调用业务逻辑层获取数据
        List<FlightEntity> all = flightService.getByAll();
        //2、转发到jsp中
        req.setAttribute("flightEntities",all);
        req.getRequestDispatcher("showFlight.jsp").forward(req,resp);
    }
}
