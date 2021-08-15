package com.example.demo2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        boolean flag = false;
        try {
            flag=UserId.insert(new UserId.User(id,name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag){
            out.println("<html><body>");
            out.println("<h1>" + "insert successfully" + "</h1>");
            out.println("</body></html>");
        }else{
            out.println("<html><body>");
            out.println("<h1>" + "insert failed(id may have existed)" + "</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
