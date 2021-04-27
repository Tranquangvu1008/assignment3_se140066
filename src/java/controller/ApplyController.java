/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import dtos.CodeDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class ApplyController extends HttpServlet {

    private final static String SUCCESS = "showcart.jsp";

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

        CarDAO dao = new CarDAO();
        CartDTO cart = (CartDTO) session.getAttribute("CART");
        CarDTO car = new CarDTO();
        List<CodeDTO> list = new ArrayList<>();
        try {
            String action = request.getParameter("btnAction");
            boolean check = false;
            String code = request.getParameter("txtCode");
            String active = (String) session.getAttribute("ACTIVE");
            request.setAttribute("WARNING", null);
            request.setAttribute("CONFIRM", null);

            long millis = System.currentTimeMillis();
            java.sql.Date dateNow = new java.sql.Date(millis);

            if (!code.isEmpty()) {
                if (action == null) {
                    action = "";
                }

                if (action.equalsIgnoreCase("X")) {
                    for (CarDTO dto : cart.getCart().values()) {
                        list = dao.checkDiscountCode(dto.getCarBrand());

                        if (list != null) {
                            for (CodeDTO ls : list) {
                                if (ls.getDiscountCode().equalsIgnoreCase(code)) {
                                    if (dto.getCarBrand().equalsIgnoreCase(ls.getCarBrand())) {

                                        float price = dto.getPrice() * 100 / (100 - ls.getDiscount());

                                        car = new CarDTO(dto.getCarID(), dto.getCarBrand(), dto.getCarName(), dto.getColor(),
                                                dto.getSlot(), dto.getImage(), price, dto.getCategoryID(), dto.getQuantity(), dto.getStartDay(), dto.getEndDay(), "");

                                        cart.update(car);
                                        session.setAttribute("PRICE", null);
                                        session.setAttribute("DISCOUNT", null);
                                        session.setAttribute("CODE", null);
                                        session.setAttribute("ACTIVE", null);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (active != null && code != null) {
                        request.setAttribute("WARNING", "warning");
                        return;
                    }
                    for (CarDTO dto : cart.getCart().values()) {
                        list = dao.checkDiscountCode(dto.getCarBrand());

                        if (list != null) {
                            for (CodeDTO ls : list) {
                                if (ls.getDiscountCode().equalsIgnoreCase(code)) {
                                    if (dateNow.after(ls.getStartDay()) && dateNow.before(ls.getEndDay())) {
                                        
                                        if (dto.getCarBrand().equalsIgnoreCase(ls.getCarBrand())) {
                                            session.setAttribute("PRICE", dto.getPrice());
                                            float price = dto.getPrice() - (dto.getPrice() * ls.getDiscount() / 100);

                                            car = new CarDTO(dto.getCarID(), dto.getCarBrand(), dto.getCarName(), dto.getColor(),
                                                    dto.getSlot(), dto.getImage(), price, dto.getCategoryID(), dto.getQuantity(), dto.getStartDay(), dto.getEndDay(), "");

                                            cart.update(car);
                                            check = true;
                                            session.setAttribute("DISCOUNT", ls.getDiscount());
                                            session.setAttribute("CARBRAND", dto.getCarBrand());
                                            session.setAttribute("CODE", ls.getDiscountCode());
                                            session.setAttribute("ACTIVE", "active");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!check) {
                        request.setAttribute("CONFIRM", "This coupon does not exist!");
                    }
                }
            } else {
                request.setAttribute("CONFIRM", "This coupon is empty!");
            }

        } catch (Exception e) {
            log("Error at ApplyController: " + e.toString());
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
