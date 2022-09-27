/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ali Hussein
 */
@WebServlet(urlPatterns = {"/Form"})
public class Form extends HttpServlet {    
    Connection conn;
    Statement stat;
    PreparedStatement prepStat;
    
    static String name;
    static String address;
    static String mobile;
    static String email;
    static String memberStatus;
    static String query;
    
    public static void sendMail(String recipient) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        //This will define if authentication is required for email service
        //gmail requires.. set to true
        properties.put("mail.smtp.auth", "true");
        //tls encryption to true
        properties.put("mail.smtp.starttls.enable", "true");
        //providing smtp host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //providing smtp port 587 (gmail)
        properties.put("mail.smtp.port", "587");
        
        //System.out.println("This is still working from here");
        
        final String myEmail = "technologyfinestirl@gmail.com";
        final String myPassword = "alihussein05112001";
        
        /**
         * logging into technologyfinestirl Gmail account
         */
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });
        
        System.out.println("Successfully logged in");
        
        Message message = prepareMessage(session, myEmail, recipient);
        
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    
    private static Message prepareMessage(Session session, String myEmail, String recipient){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Query Automated Reply");
            message.setText("Hey " + name + ", \n\nThank you for sending the following query: \n" + query + "\n\nDue to COVID-19 restrictions, we have less employees on site to send a quick reply you are used to from us but we should get back to you in the next 48 hours! \n\nThank you for your patience. \n\nWith kind regards, \nTechnologies Finest IRL");
            return message;
        }catch(Exception ex){
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(ex);
        }
        return null;
    }
    
    public void init() throws ServletException{
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "projectdb";
        String userName = "root";
        String password = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            stat = (Statement) conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS customer_queries "
                    + "(name CHAR(40), address CHAR(255), mobile CHAR(255), email VARCHAR(255), memberStatus VARCHAR(255), comment VARCHAR(255))");
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        name = request.getParameter("name");
        address = request.getParameter("address");
        mobile = request.getParameter("mobile");
        email = request.getParameter("email");
        memberStatus = request.getParameter("member");
        query = request.getParameter("query");
        
        try{
            String statement = "INSERT INTO customer_queries VALUES (?,?,?,?,?,?)";
            prepStat = (PreparedStatement) conn.prepareStatement(statement);
            prepStat.setString(1, name);
            prepStat.setString(2, address);
            prepStat.setString(3, mobile);
            prepStat.setString(4, email);
            prepStat.setString(5, memberStatus);
            prepStat.setString(6, query);
            prepStat.execute();
            
            Form.sendMail(email);

            response.sendRedirect("index.jsp");
        }catch(Exception e){
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
