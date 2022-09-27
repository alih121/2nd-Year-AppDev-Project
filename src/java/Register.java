/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Connection conn;
    Statement stat;
    PreparedStatement prepStat;
    PreparedStatement prepStat2;
    ResultSet result;
    boolean duplicateUser = false;
    
    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "projectdb";
        String userName = "root";
        String password = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            stat = (Statement) conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS login "
                    + "(username VARCHAR(20), name CHAR(40), email CHAR(40), password CHAR(20), PRIMARY KEY (username))");
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
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String query = "SELECT * FROM login WHERE username = ?";
            prepStat = (PreparedStatement) conn.prepareStatement(query);
            prepStat.setString(1, username);
            //prepStat.setString(2, email);
            
            result = prepStat.executeQuery();
            while(result.next()){
                String checkUsername = result.getString("username");
                //String checkEmail = result.getString("email");
                
                if(checkUsername.equals(username)){
                    System.out.println("Username already exists");
                    duplicateUser = true;
                    String errorMessage = "User already exists. Please log in";
                    request.setAttribute("error", errorMessage);
                    RequestDispatcher rd
                            = request.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }/*else if(checkEmail.equals(email)){
                    System.out.println("email already exists");
                    String errorMessage = "Email already exists";
                    request.setAttribute("error", errorMessage);
                    RequestDispatcher rd
                            = request.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }*/
            }
            
            if(duplicateUser == false){
                String query2 = "INSERT INTO login VALUES (?,?,?,?)";
                prepStat2 = (PreparedStatement) conn.prepareStatement(query2);
                prepStat2.setString(1, username);
                prepStat2.setString(2, name);
                prepStat2.setString(3, email);
                prepStat2.setString(4, password);
                prepStat2.execute();

                response.sendRedirect("login.jsp");
            }
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
