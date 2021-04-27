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
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class UpdateCarController extends HttpServlet {

    private static final String SUCCESS = "showcart.jsp";
    private static final String ERROR = "error.jsp";

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
        CarDAO dao = new CarDAO();
        HttpSession session = request.getSession();

        try {
            String carID = request.getParameter("txtCarID");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String startDay = (String) session.getAttribute("DAYSTART");
            String endDay = (String) session.getAttribute("DAYEND");
            String code = request.getParameter("txtCode");
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            CarDTO dto = new CarDTO();

            int num = dao.checkQuantityOfCar(carID);
            Date start = Date.valueOf(startDay);
            Date end = Date.valueOf(endDay);

            if (cart != null) {
                int carLeft = num - dao.calculateQuantityOrder(start, end, carID);

                if (carLeft - quantity < 0) {
                    String error = "This product " + carID + " is out of stock or over available quantity";
                    request.setAttribute("ERROR_CART", error);
                }
                for (CarDTO car : cart.getCart().values()) {
                    if (car.getCode().equals(code)) {
                        dto = new CarDTO(carID, car.getCarBrand(), car.getCarName(), car.getColor(), car.getSlot(), car.getImage(), car.getPrice(),
                                car.getCategoryID(), quantity, startDay, endDay, code);
                        break;
                    }
                }
                cart.update(dto);
                session.setAttribute("CART", cart);
                url = SUCCESS;

            }

        } catch (Exception e) {
            log("Error at UpdateCarController : " + e.toString());
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
