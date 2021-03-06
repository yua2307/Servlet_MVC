/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Product;
import service.TCPService;

/**
 *
 * @author macbookpro
 */
public class addServlet extends HttpServlet {

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
            out.println("<title>Servlet addServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addServlet at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();
            session.removeAttribute("message");

            try {
                String role = (String) request.getSession().getAttribute("role");
                if (role.equalsIgnoreCase("") || role == null) {
                    response.sendRedirect("loginServlet");
                } else {
                    try {
                        Socket socket = TCPService.getConnection("localhost", 9000);
                        HashMap<Integer, String> sendToServer = new HashMap<Integer, String>();
                        sendToServer.put(0, "getAllCategory");

                        // send to Server
                        TCPService.writeObject(sendToServer, socket);
                        List<Category> listCate = (List<Category>) TCPService.readObject(socket);
                        //  List<Category> listCate = categoryService.getAllCategory();
                        request.setAttribute("listCate", listCate);
                        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(addServlet.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } catch (NullPointerException e) {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
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
        HttpSession session = request.getSession();
        try {

            String productName = (String) request.getParameter("proName");
            String des = (String) request.getParameter("description");
            int idCate = Integer.valueOf(request.getParameter("categoryId"));

            // Send to Server 
            List<Object> list1 = new ArrayList<Object>();
            list1.add("getCategoryById");
            list1.add(idCate);

            // Send to Server 
            Socket socket1 = TCPService.writeObject(list1, "localhost", 9000);

            Category cateFind = (Category) TCPService.readObject(socket1);

            //Category cateFind = categoryService.getCategoryById(idCate);
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            int unitPrice = Integer.valueOf(request.getParameter("unitPrice"));
            Product p = new Product(productName, des, quantity, unitPrice, cateFind);

            // Send to Server 
            Socket socket2 = TCPService.getConnection("localhost", 9000);
            List<Object> list = new ArrayList<Object>();
            list.add("addProduct");
            list.add(p);

            TCPService.writeObject(list, socket2);
            // receive from Server 

            boolean check = (boolean) TCPService.readObject(socket2);

            //boolean check = productService.addProduct(p);
            if (check) {
                session.setAttribute("message", "Add Success");
                response.sendRedirect("listServlet");
            } else {
                session.setAttribute("message", "Add Failed");
                doGet(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (NullPointerException e) {
            System.out.println("Error : " + e.getMessage());
            response.sendRedirect("403.jsp");
        }catch (NumberFormatException e) {
            response.sendRedirect("listServlet");
        } 

//        try {
//            Category cate = categoryService.getCategoryById(idCate);
//            Product p = new Product(productName, des, quantity, unitPrice, cate);
//           // if(productService.addProduct(p) == true){
//               // request.
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(addServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(addServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
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
