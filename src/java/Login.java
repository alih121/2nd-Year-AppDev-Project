/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ali Hussein
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    Connection conn;
    Statement stat;
    PreparedStatement prepStat;
    PreparedStatement prepStat2;
    ResultSet result;
    ResultSet result2;

    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "projectdb";
        String userName = "root";
        String password = "";

        System.out.println("in Login.java");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected");
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        System.out.println("before try");
        try {
            String query = "SELECT * from login WHERE username = ?";
            String query2 = "SELECT * FROM reviews WHERE username = ?";
            prepStat = (PreparedStatement) conn.prepareStatement(query);
            prepStat.setString(1, username);

            result = prepStat.executeQuery();
            System.out.println("after select");
            // Extract data from result set
            while (result.next()) {
                //Retrieve by column name
                String fullName = result.getString("name");
                String email = result.getString("email");
                String pwd = result.getString("password");
                if (pwd.equals(password)) {
                    session.setAttribute("fullName", fullName);
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    
                    prepStat2 = (PreparedStatement) conn.prepareStatement(query2);
                    prepStat2.setString(1, username);

                    result2 = prepStat2.executeQuery();
                    while(result2.next()){
                        String rating = Integer.toString(result2.getInt("rating"));
                        String comment = result2.getString("comment");
                        if(username.equals("username")){
                            session.setAttribute("rating", rating);
                            session.setAttribute("comment", comment);
                        }
                    }
                    response.sendRedirect("index.jsp");
                } 
                else {
                    System.out.println("incorrect password");
                    String errorMessage = "Incorrect password entered";
                    request.setAttribute("error", errorMessage);
                    RequestDispatcher rd
                            = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }

            }
            System.out.println("incorrect username");
            String errorMessage = "This username does not exist";
            request.setAttribute("error", errorMessage);
            RequestDispatcher rd
                    = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);

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
