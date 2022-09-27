
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ali Hussein
 */
public class ReviewsDAO {
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "projectdb";
    private String userName = "root";
    private String password = "";
    
    private static final String SELECT_REVIEW_BY_USERNAME = "SELECT username, rating, comment FROM reviews WHERE username = ?";
    private static final String SELECT_ALL_REVIEWS = "SELECT * FROM reviews";
    private static final String DELETE_REVIEW = "DELETE FROM reviews WHERE username = ?;";
    private static final String UPDATE_REVIEW = "UPDATE reviews SET rating = ?, comment = ? WHERE username = ?;";
    
    protected Connection getConnection(){
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    //select review by username
    public Reviews selectReview(String username){
        Reviews review = null;
        //Create a connection to database
        try(Connection conn = getConnection();
                //Create PreparedStatement using the connection object
                PreparedStatement statement = conn.prepareStatement(SELECT_REVIEW_BY_USERNAME);){
            statement.setString(1, username);
     
            ResultSet result = statement.executeQuery();
            //Start while loop for result set
            while(result.next()){
                String rating = Integer.toString(result.getInt("rating"));
                String comment = result.getString("comment");
                review = new Reviews(username, rating, comment);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return review;
    }
    
    //select all reviews
    public List<Reviews> selectAllReviews(){
        List<Reviews> reviews = new ArrayList<>();
        try(Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(SELECT_ALL_REVIEWS);){
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                String username = result.getString("username");
                String rating = Integer.toString(result.getInt("rating"));
                String comment = result.getString("comment");
                reviews.add(new Reviews(username, rating, comment));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reviews;
    }
    
    //Delete Review
    public boolean deleteReview(String username) throws SQLException{
        boolean rowDeleted;
        try(Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_REVIEW);){
            statement.setString(1, username);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    //Update Review
    public boolean updateReview(Reviews review) throws SQLException{
        boolean rowUpdated;
        
        try(Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_REVIEW);){
            statement.setString(1, review.getUsername());
            statement.setString(2, review.getRating());
            statement.setString(3, review.getComment());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
