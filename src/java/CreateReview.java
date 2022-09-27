/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ali Hussein
 */
@WebServlet(urlPatterns = {"/CreateReview"})
public class CreateReview extends HttpServlet {
    Connection conn;
    Statement stat;
    PreparedStatement prepStat;
    
    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "projectdb";
        String userName = "root";
        String password = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            stat = (Statement) conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS reviews "
                    + "(username VARCHAR(20), rating INT, comment VARCHAR(255), FOREIGN KEY(username) REFERENCES login(username) ON UPDATE CASCADE)");
        } catch (Exception e) {
            System.err.println(e);
        }
    }  //  end of init() method
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String rating = request.getParameter("rating");
        String comment = request.getParameter("comment");

        try{
            String statement = "INSERT INTO reviews VALUES (?,?,?)";
            prepStat = (PreparedStatement) conn.prepareStatement(statement);
            prepStat.setString(1, username);
            prepStat.setString(2, rating);
            prepStat.setString(3, comment);
            prepStat.execute();

            response.sendRedirect("reviews.jsp");
            
        } catch (Exception e) {
            System.err.println(e);
        }


       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
