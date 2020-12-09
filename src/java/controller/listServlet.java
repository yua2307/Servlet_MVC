/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import service.TCPService;

/**
 *
 * @author macbookpro
 */
public class listServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
//        try {
//            
//            List<Product> list = productService.getAllProduct();
//            request.setAttribute("list", list);
//            request.getRequestDispatcher("listProduct.jsp").forward(request, response);
//            request.getSession().removeAttribute("message");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(listServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(listServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
          
        try {
            
             HashMap<Integer, String> sendToClient = new HashMap<Integer, String>();
            sendToClient.put(0,"listAllServlet");
//             Socket socket = new Socket("localhost", 9000);
//            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            
            Socket socket = TCPService.getConnection("localhost", 9000);
            // to TCP Server 
            TCPService.writeObject(sendToClient,socket);
            // receive from Server
            List<Product> list = (List<Product>) TCPService.readObject(socket);
            request.setAttribute("list", list);
            request.getRequestDispatcher("listProduct.jsp").forward(request, response);
            request.getSession().removeAttribute("message");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(listServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(java.net.SocketException e){
             System.out.println("Error :" + e.getMessage());
             response.sendRedirect("403.jsp");
        }
        catch(NullPointerException e){
             System.out.println("Error :" + e.getMessage());
             response.sendRedirect("403.jsp");
        }
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
        doGet(request, response);
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
