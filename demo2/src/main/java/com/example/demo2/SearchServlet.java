package com.example.demo2;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name= "search",value = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        UserId.User user = null;
        try {
            user= UserId.find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user!=null){
            Gson gson = new Gson();
            String json= gson.toJson(user);
            out.println("<html><body>");
            out.println("<h1>" + json + "</h1>");
            out.println("</body></html>");
        }else {
            out.println("<html><body>");
            out.println("<h1>" + "no found" + "</h1>");
            out.println("</body></html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
