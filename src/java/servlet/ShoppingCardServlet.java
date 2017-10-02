/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.SQLControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;

/**
 *
 * @author DC
 */
public class ShoppingCardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SQLControl control = new SQLControl();
        //get session
        HttpSession session = request.getSession();
        //get cID,payMethod and date
        String cID = request.getParameter("slCustomer");
        String payMethod = request.getParameter("txtMethod");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(new Date());
        
        PrintWriter out = response.getWriter();
        if (session.getAttribute("cart") != null) {
            try {
                //insert order table
                control.insertOrder(date, payMethod, cID);
            } catch (SQLException ex) {
                System.out.println("SQL Error");
            }
            try {
                //insert orderline table
                int orderID = control.getlastedOrID();
                ArrayList<Cart> arrC = (ArrayList<Cart>) session.getAttribute("cart");
                for (int i = 0; i < arrC.size(); i++) {
                    String pID = arrC.get(i).getpID();
                    int quantity = arrC.get(i).getQuantity();
                    int price = arrC.get(i).getpPrice();

                    control.insertOrderLine(orderID, pID, quantity, price);
                }
            } catch (SQLException e) {
                System.out.println("SQL Error");
            }
            //destroy session
            session.invalidate();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Thank you for using service');");
            out.println("location='product.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('You dont buy anything!! Plz buy something before checkout');");
            out.println("location='product.jsp';");
            out.println("</script>");
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
        processRequest(request, response);
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
