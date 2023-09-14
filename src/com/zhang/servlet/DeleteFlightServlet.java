package com.zhang.servlet;

import com.zhang.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author z2545
 * @date 2023/9/13 10:27
 * @description DeleteFlightServlet
 */
@WebServlet("/deleteFlight")
public class DeleteFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取jsp传递参数值
        String idStr = req.getParameter("id");
        if (idStr==null || idStr==""){
            //跳转到错误页面 客户端传递参数有问题
            req.setAttribute("errorMsg","id的值不能为空!");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            int result = flightService.updateDelete(id);
            //如果删除成功的情况下 跳转到showFilght
            if(result>0){
//                req.getRequestDispatcher("showFlight").forward(req,resp);
                resp.sendRedirect("showFlight");
            }else{
                //删除失败 跳转到错误jsp
                req.setAttribute("errorMsg","删除失败！");
                req.getRequestDispatcher("error.jsp").forward(req,resp);
            }

        }catch(NumberFormatException e){
            //类型转化异常
            e.printStackTrace();
            //跳转到错误页面
            req.setAttribute("errorMsg","类型转化异常");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }catch(Exception e){
            req.setAttribute("errorMsg","系统异常");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}
