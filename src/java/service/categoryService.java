/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import static service.productService.excuteQuery;

/**
 *
 * @author macbookpro
 */
public class categoryService {
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
    
    public static List<Category> getAllCategory() throws ClassNotFoundException, SQLException{
        
         List<Category> list = new ArrayList<Category>();
         ResultSet rs= excuteQuery("SELECT * FROM Category");
         while(rs.next()){
             int id = rs.getInt("idCate");
             String name  = rs.getString("cateName");
             Category cate = new Category(id, name);
             list.add(cate);
         }
         return list;
         
    }
    public static Category getCategoryById(int id) throws ClassNotFoundException, SQLException{
        
        Connection conn = ConnectDB();
        PreparedStatement stmt = conn.prepareStatement("Select * from Category WHERE idCate = ?");
                stmt.setInt(1, id);
         ResultSet rs= stmt.executeQuery();
         while(rs.next()){
             String name  = rs.getString("cateName");
             Category cate = new Category(id, name);
             return cate;
         }
         return null;
         
    }

    public static boolean addCategory(String cateName) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectDB();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Category(cateName) VALUES(?)");
        stmt.setString(1, cateName);
        int row = stmt.executeUpdate();
        if(row >=1){
            return  true;
        }
        return false;
    }
}
