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
import service.TCPService;

/**
 *
 * @author macbookpro
 */
public class addCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet addCategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addCategoryServlet at " + request.getContextPath() + "</h1>");
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
        
        try {
            String role = (String) request.getSession().getAttribute("role");
                if (role.equalsIgnoreCase("") || role == null) {
                    request.getSession().setAttribute("messageLogin", "You must login first");
                    response.sendRedirect("loginServlet");
                }
                else {
                        response.sendRedirect("addCategory.jsp");
                }
        } catch (NullPointerException e) {  
                response.sendRedirect("login.jsp");
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
        
        try {
            String cateName = request.getParameter("cateName");
            
            List<Object> list = new ArrayList<Object>();
            list.add("addCategory");
            list.add(cateName);
            
            //send to Server
            Socket socket = TCPService.writeObject(list, "localhost", 9000);
            // receive from server
            boolean check = (boolean) TCPService.readObject(socket);
            // boolean check = categoryService.addCategory(cateName);
            if(check){
                response.sendRedirect("listCategoryServlet");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException e){
            System.out.println("Error : " + e.getMessage());
            response.sendRedirect("403.jsp");
        }
        
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
