package com.zhang.servlet;

import com.zhang.entity.FlightEntity;
import com.zhang.service.FlightService;
import com.zhang.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author z2545
 * @date 2023/9/13 14:02
 * @description UpdateFlightServlet
 */
@WebServlet("/updateFlight")
public class UpdateFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();
    /**
     * 用户点击修改该条数据------需要查询到该条数据（回显）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取需要修改的id值
        String idStr = req.getParameter("id");
        if (idStr==null || idStr==""){
            //跳转到错误页面 客户端传递参数有问题
            req.setAttribute("errorMsg","id的值不能为空!");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return;
        }
        try{
            int id = Integer.parseInt(idStr);
            FlightEntity flightEntity = flightService.getByIdFlight(id);
            if(flightEntity == null){
                req.setAttribute("errorMsg","无法查询到该数据！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;//不用else
            }
            req.setAttribute("flight",flightEntity);
            req.getRequestDispatcher("updateFlight.jsp").forward(req,resp);

        }catch (Exception e){
            req.setAttribute("errorMsg","出现异常！");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //验证id的值
            String idStr = req.getParameter("id");
            if(StringUtils.isEmpty(idStr)){
                req.setAttribute("errorMsg","id不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            Integer id = Integer.parseInt(idStr);

            String flightId = req.getParameter("flightId");
            if(StringUtils.isEmpty(flightId)){
                req.setAttribute("errorMsg","flightId不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            String company = req.getParameter("company");
            if(StringUtils.isEmpty(company)){
                req.setAttribute("errorMsg","company不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            String departureAirport = req.getParameter("departureAirport");
            if(StringUtils.isEmpty(departureAirport)){
                req.setAttribute("errorMsg","departureAirport不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            String arriveAirport = req.getParameter("arriveAirport");
            if(StringUtils.isEmpty(arriveAirport)){
                req.setAttribute("errorMsg","arriveAirport不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            //检差为空
            String departureTimeStr = req.getParameter("departureTime");
            if(StringUtils.isEmpty(departureTimeStr)){
                req.setAttribute("errorMsg","departureTime不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            Date departureTime = DateUtils.stringToDate(departureTimeStr);
            //检差为空
            String arriveTimeStr = req.getParameter("arriveTime");
            if(StringUtils.isEmpty(arriveTimeStr)){
                req.setAttribute("errorMsg","arriveTime不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            Date arriveTime = DateUtils.stringToDate(arriveTimeStr);
            String model = req.getParameter("model");
            if(StringUtils.isEmpty(model)){
                req.setAttribute("errorMsg","model不能为空！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            int result = flightService.updateFlight(new FlightEntity(id,flightId,company,departureAirport,arriveAirport,departureTime,arriveTime,model,0));
            if(result<0){
                req.setAttribute("errorMsg","修改失败！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
                return;
            }
            resp.sendRedirect("showFlight");

        }catch(Exception e){
            req.setAttribute("errorMsg","出现异常！");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}
