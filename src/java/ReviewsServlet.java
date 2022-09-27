/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author husse
 */
@WebServlet(urlPatterns = {"/ReviewsServlet"})
public class ReviewsServlet extends HttpServlet {
    private ReviewsDAO reviewsDAO;
    
    public ReviewsServlet(){
        this.reviewsDAO = new ReviewsDAO();
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
        String operation = request.getServletPath();
        
        switch(operation){
            case "ReviewsServlet/new":
                newReviewForm(request, response);
                break;
            case "ReviewsServlet/delete":
                try{
                    deleteReview(request, response);
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            case "ReviewsServlet/edit":
                try{
                    editReview(request, response);
                }catch(SQLException e){
                    e.printStackTrace();
                }
            case "ReviewsServlet/update":
                try{
                    updateReview(request, response);
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            default:
                try{
                    listReviews(request, response);   
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
        }
    }
    
    
    private void listReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List<Reviews> listReviews = reviewsDAO.selectAllReviews();
        request.setAttribute("listReviews", listReviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reviews.jsp");
        dispatcher.forward(request, response);
    }

    private void newReviewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //forwards a new request to reviews.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("createReview.jsp");
        dispatcher.forward(request, response);
    }

    private void editReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String username = request.getParameter("username");
        Reviews currentReview = reviewsDAO.selectReview(username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("createReview.jsp");
        request.setAttribute("username", currentReview);
        dispatcher.forward(request, response);
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        String username = request.getParameter("username");
        String rating = request.getParameter("rating");
        String comment = request.getParameter("comment");

        Reviews review = new Reviews(username, rating, comment);
        reviewsDAO.updateReview(review);
        response.sendRedirect("list");
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        String username = request.getParameter("username");
        reviewsDAO.deleteReview(username);
        response.sendRedirect("list");
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
        this.doGet(request, response);
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
