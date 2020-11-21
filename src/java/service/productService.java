/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author macbookpro
 */
public class productService {
      public static Connection connectDB () throws ClassNotFoundException, SQLException
    {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Product?characterEncoding=utf8";
            Connection conn = DriverManager.getConnection(url, "root", "chinhdeptrai");
            return conn; 
    }
    
    public static ResultSet excuteQuery (String sql ) throws ClassNotFoundException, SQLException
    {
         
          Connection conn = connectDB();
          Statement stmt = conn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(sql);
          return rs;
    }
    
    public static List<Product> getAllProduct() throws ClassNotFoundException, SQLException{
         List<Product> list = new ArrayList<Product>();
         ResultSet rs= excuteQuery("SELECT * FROM Product");
         while(rs.next()){
             int id = rs.getInt("id");
             Category cate = categoryService.getCategoryById(rs.getInt("idCate"));
             String name  = rs.getString("name");
             String des = rs.getString("des");
             int quantity = rs.getInt("quantity");
             int unitPrice = rs.getInt("unitPrice");
             Product p = new Product(id, name, des, quantity, unitPrice, cate);
             list.add(p);
         }
         return list;
         
    }
    
    
    public static Product getProductById(int  id) throws SQLException, ClassNotFoundException{
        Connection conn = connectDB();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Product WHERE id = ? ");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
           
             Category cate = categoryService.getCategoryById(rs.getInt("idCate"));
             String name  = rs.getString("name");
             String des = rs.getString("des");
             int quantity = rs.getInt("quantity");
             int unitPrice = rs.getInt("unitPrice");
             Product p = new Product(id, name, des, quantity, unitPrice, cate);
             return p;
        }
        return null;
    }

    public static boolean addProduct(Product p) throws ClassNotFoundException, SQLException {
        try {
             Connection conn = connectDB();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Product(idCate,name,des,quantity,unitPrice) VALUES(?,?,?,?,?)");
//        System.out.println(p.getCategory().getIdCate());
//        System.out.println(p.getName());
//        System.out.println(p.getDes());
//        System.out.println(p.getQuantity());
//        System.out.println(p.getUnitPrice());
        stmt.setInt(1, p.getCategory().getIdCate());
        stmt.setString(2, p.getName());
        stmt.setString(3, p.getDes());
        stmt.setInt(4,p.getQuantity());
        stmt.setInt(5,p.getUnitPrice());
        int row = stmt.executeUpdate();
        
        if(row >= 1){
            return true;
        }
       
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
         return false;
    }
    public static boolean deleteProduct(int id) throws ClassNotFoundException, SQLException {
        try {
             Connection conn = connectDB();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Product WHERE id = ?");

        stmt.setInt(1, id);
        
        int row = stmt.executeUpdate();
        
        if(row >= 1){
            return true;
        }
       
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
         return false;
    }

    public static boolean updateProduct(Product p) throws ClassNotFoundException , SQLException{
        Connection conn = connectDB();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Product SET idCate=? , name=? , des=? , quantity=? , unitPrice=? WHERE id=? ");
        stmt.setInt(1, p.getCategory().getIdCate());
        stmt.setString(2, p.getName());
        stmt.setString(3, p.getDes());
        stmt.setInt(4, p.getQuantity());
        stmt.setInt(5, p.getUnitPrice());
        stmt.setInt(6, p.getId());
        int row = stmt.executeUpdate();
        if(row >=1){
            return true;
        }
        return false;
    }
}
