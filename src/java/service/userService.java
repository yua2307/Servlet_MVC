/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;


/**
 *
 * @author macbookpro
 */
public class userService {
    public static Connection ConnectDB () throws ClassNotFoundException, SQLException
    {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Product?characterEncoding=utf8";
            Connection conn = DriverManager.getConnection(url, "root", "chinhdeptrai");
            return conn; 
    }
    
    public static ResultSet ExcuteQuery (String sql ) throws ClassNotFoundException, SQLException
    {
         
          Connection conn = ConnectDB();
          Statement stmt = conn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(sql);
          return rs;
    }
    
    public static boolean checkLogin(String username , String password) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE userName = ? AND passWord = ? ");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        int rowCount = 0;
        
        
         while(rs.next()){
       
            rowCount+=1;
        }
        if(rowCount >=1){
            return true;
        }
        return false;        
    }
    
    public static User getUserByUsername(String userName) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE userName = ? ");
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        
        
         while(rs.next()){
             int id = rs.getInt("id");
             String name = rs.getString("name");
             String username = rs.getString("userName");
             String password = rs.getString("password");
             String role = rs.getString("role");
             User u = new User(id, name,username, password, role);
             return u;
        }
        return null;     
    }
}
