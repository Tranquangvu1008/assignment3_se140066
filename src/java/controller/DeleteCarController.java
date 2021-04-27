/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.CarDTO;
import dtos.CartDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class DeleteCarController extends HttpServlet {

    private static final String SUCCESS = "showcart.jsp";
    private static final String RETURN = "login.jsp";

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
        String url = SUCCESS;

        HttpSession session = request.getSession();

        try {
            String carID = request.getParameter("txtCarID");
            String carBrand = request.getParameter("txtCarBrand");
            String code = request.getParameter("txtCode");
            CarDTO dao = new CarDTO();
            CartDTO cart = (CartDTO) session.getAttribute("CART");

            for (CarDTO dto : cart.getCart().values()) {
                if (carBrand.equalsIgnoreCase(dto.getCarBrand())) {
                    if (code.equalsIgnoreCase(dto.getCode())){
                        session.setAttribute("ACTIVE", null);
                        session.setAttribute("DISCOUNT", null);
                        session.setAttribute("CODE", null);
                    }
                }
            }

            cart.delete(code);
            session.setAttribute("CART", cart);
        } catch (Exception e) {
            log("Error at DeleteCarController : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
