/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class OrderController extends HttpServlet {

    private final static String SUCCESS = "MenuController";
    private final static String ERROR = "login.jsp";

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
        String url = ERROR;

        HttpSession session = request.getSession();
        CarDAO dao = new CarDAO();
        CartDTO cart = (CartDTO) session.getAttribute("CART");

        try {
            request.setAttribute("MESSAGE", null);
            String userID = (String) session.getAttribute("USERID");

            String carID = request.getParameter("txtCarID");
            String carBrand = request.getParameter("txtCarBrand");
            String carName = request.getParameter("txtCarName");
            String color = request.getParameter("txtColor");
            int slot = Integer.parseInt(request.getParameter("txtSlot"));
            String image = request.getParameter("txtImage");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            String categoryID = request.getParameter("txtCategoryID");
            String customerName = (String) session.getAttribute("FULLNAME");

            int num = dao.checkQuantityOfCar(carID);

            String startDay = (String) session.getAttribute("DAYSTART");
            String endDay = (String) session.getAttribute("DAYEND");

            String code = carID + startDay + endDay;

            if (startDay == null || endDay == null) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Input start && end day!");
            } else {
                if (userID != null) {
                    if (cart == null) {
                        CarDTO car = new CarDTO(carID, carBrand, carName, color, slot, image, price, categoryID, 1, startDay, endDay, code);
                        if (cart == null) {
                            cart = new CartDTO(customerName, null);
                        }
                        cart.add(car);
                        session.setAttribute("CART", cart);
                    } else {
                        int quantity = cart.getMaxQuantity(carID, startDay, endDay);

                        if (quantity > 0) {
                            CarDTO car = new CarDTO(carID, carBrand, carName, color, slot, image, price, categoryID, 1, startDay, endDay, code);
                            cart.add(car);
                        } else {
                            request.setAttribute("MESSAGE", "Number of vehicles exceed the amount available for that day. ");
                        }
                        session.setAttribute("CART", cart);

                    }

                    url = SUCCESS;

                } else {
                    url = ERROR;
                }
            }

        } catch (Exception e) {
            log("Error at OrderController : " + e.toString());
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
