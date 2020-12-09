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
import model.User;
import org.apache.tomcat.util.digester.ArrayStack;
import service.TCPService;

/**
 *
 * @author macbookpro
 */
public class loginServlet extends HttpServlet {

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
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
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
       // request.setAttribute("messageLogin", "You must login first");
      //  request.getRequestDispatcher("login.jsp").forward(request, response);
      response.sendRedirect("login.jsp");
      request.getSession().removeAttribute("messageLogin");
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


       String username = (String) request.getParameter("username");
        System.out.println(username);
       String password = (String) request.getParameter("password");   
       System.out.println(password);

       boolean check =false;
        try {
            List<String> listPara = new ArrayList<String>();
            listPara.add(username);
            listPara.add(password);
            
            
            List<Object> sendToServer = new ArrayList<Object>();
            sendToServer.add("loginServlet");
            sendToServer.add(listPara);
            // send To Server
            Socket socket = TCPService.writeObject(sendToServer, "localhost", 9000);
            // receive from Server
            check = (boolean)TCPService.readObject(socket);
            
           //  check = userService.checkLogin(username, password);
            if (check) {
                List<Object> sendToServer2 = new ArrayList<Object>();
                sendToServer2.add("getUserByUsername");
                sendToServer2.add(username);
                
                // send To Server
                Socket socket2 = TCPService.writeObject(sendToServer2, "localhost", 9000);
                // receive from Server
                 User userFind= (User) TCPService.readObject(socket2);
           //     User userFind = userService.getUserByUsername(username);
                request.getSession().setAttribute("role", userFind.getRole());
                request.getSession().setAttribute("userNameForHello", userFind.getName());
                response.sendRedirect("listServlet");
            } else {
                
                 //  response.sendRedirect("login.jsp");
                     request.setAttribute("message", "username or password not correct !!");
                     request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e){
            response.sendRedirect("403.jsp");
        }catch(java.net.ConnectException e){
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
