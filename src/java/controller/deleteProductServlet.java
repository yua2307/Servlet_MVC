/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.TCPService;

/**
 *
 * @author macbookpro
 */
public class deleteProductServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteProductServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
       
        try {
            
             String role = (String) session.getAttribute("role");
             if(role.equalsIgnoreCase("")|| role == null){
              request.getSession().setAttribute("messageLogin", "You must login first");
              response.sendRedirect("loginServlet");
            }
            int id = Integer.valueOf(request.getParameter("id"));

            boolean check;
            try {
               
                List<Object> sendToServer = new ArrayList<Object>();
                sendToServer.add("deleteProduct");
                sendToServer.add(id);
                // send 
                Socket socket = TCPService.getConnection("localhost", 9000);
                TCPService.writeObject(sendToServer,socket);
                 // receive
                 check = (boolean)TCPService.readObject(socket);
                 
               // check = productService.deleteProduct(id);
                if (check) {
                    session.setAttribute("message", "Delete Success");
                    response.sendRedirect("listServlet");
                } else {
                    request.setAttribute("message", "Delete Failed");
                    response.sendRedirect("listServlet");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(deleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NullPointerException e) {
            response.sendRedirect("login.jsp");
        }
        catch(Exception e){
            System.out.println("Error : "+ e.getMessage());
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
