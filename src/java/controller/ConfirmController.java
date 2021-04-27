/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import dtos.RenterDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class ConfirmController extends HttpServlet {

    private static final String SUCCESS = "confirm.jsp";
    private static final String ERROR = "showcart.jsp";
    private static final String RETURN = "MenuController";

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String url = ERROR;
        HttpSession session = request.getSession();

        try {
            CartDTO cart = (CartDTO) session.getAttribute("CART"); //lay gio hang 
            CarDAO dao = new CarDAO();

            String renter = request.getParameter("txtFullName");
            String phone = request.getParameter("txtPhoneNumber");
            String email = request.getParameter("txtEmail");

            String startDay = (String) session.getAttribute("DAYSTART");
            String endDay = (String) session.getAttribute("DAYEND");

            int totalDay = (int) session.getAttribute("totalDay");
            RenterDTO rent = new RenterDTO(renter, phone, email, startDay, endDay);

            String code = (String) session.getAttribute("CODE");
            if (code == null) {
                code = "";
            }

            if (renter != null) {
                boolean check = true;
                for (CarDTO dto : cart.getCart().values()) {
                    int num = dao.checkQuantityOfCar(dto.getCarID());
                    int remainQuantity = num - dao.calculateQuantityOrder(Date.valueOf(dto.getStartDay()), Date.valueOf(dto.getEndDay()), dto.getCarID());
                    if (remainQuantity - dto.getQuantity() < 0) {
                        check = false;
                    }
                }
                for (CarDTO car1 : cart.getCart().values()) {
                    for (CarDTO car2 : cart.getCart().values()) {
                        if (car1.getCarID().equalsIgnoreCase(car2.getCarID()) && !car1.getCode().equalsIgnoreCase(car2.getCode())) {
                            if (Date.valueOf(car1.getStartDay()).getTime() <= Date.valueOf(car2.getEndDay()).getTime()
                                    && Date.valueOf(car1.getEndDay()).getTime() >= Date.valueOf(car2.getStartDay()).getTime()) {
                                int totalCar = car1.getQuantity() + car2.getQuantity();
                                if (totalCar > dao.checkQuantityOfCar(car1.getCarID())) {
                                    check = false;
                                }
                            }
                        }
                    }
                }
                if (check) {
                    if (dao.createRent(cart, rent, code, totalDay)) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("CONFIRM", "Your order is Error!");
                    url = ERROR;
                }

            } else {
                List<String> list = dao.getListCarID();
                for (int i = 0; i < list.size(); i++) {
                    cart.delete(list.get(i));
                }
                session.setAttribute("PRICE", null);
                session.setAttribute("DISCOUNT", null);
                session.setAttribute("CODE", null);
                session.setAttribute("ACTIVE", null);
                url = RETURN;
                return;
            }

        } catch (Exception e) {
            log("Error at ConfirmController: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
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
