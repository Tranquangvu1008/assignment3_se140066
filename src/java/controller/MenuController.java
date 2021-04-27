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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class MenuController extends HttpServlet {

    private static final String SUCCESS = "menu.jsp";
    private static final String ERROR = "menu.jsp";
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
        String url = ERROR;
        HttpSession session = request.getSession();
        CarDAO dao = new CarDAO();

        try {
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            List<String> listCategory = dao.getListCategory();
            request.setAttribute("LIST_CATEGORY", listCategory);

            //Lay ngay hien tai
            String checkIn = String.valueOf(java.time.LocalDate.now());
            session.setAttribute("START_DATE", checkIn);

            //Lay ngay tiep theo + them 1 ngay hien tai
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(cal.getTime());
            session.setAttribute("END_DATE", strDate);

            String search = request.getParameter("txtSearch");
            String category = request.getParameter("txtCategory");
            String quantity = request.getParameter("txtQuantity");

            String startDay = request.getParameter("startDay");
            String endDay = request.getParameter("endDay");

            if (cart != null && startDay == null && endDay == null) {
                startDay = (String) session.getAttribute("DAYSTART");
                endDay = (String) session.getAttribute("DAYEND");

            } 
            else if (startDay != null && endDay != null) {
                session.setAttribute("DAYSTART", startDay);
                session.setAttribute("DAYEND", endDay);

//                if (cart != null) {
//                    List<String> list = dao.getListCarID();
//                    for (int i = 0; i < list.size(); i++) {
//                        cart.delete(list.get(i));
//                    }
//                }
            }
            
            int totalDate = 2;
            if (startDay != null && endDay != null) {
                Date date1 = formatter.parse(startDay);
                Date date2 = formatter.parse(endDay);
                long difference = date2.getTime() - date1.getTime();
                totalDate = (int) (difference / 86400000) + 1;
                
                session.setAttribute("totalDay", totalDate);
            }

            if (totalDate > 1) {

                int numOfCar;
                String index = request.getParameter("index");
                int indexPage = 0;
                int numberPage = 0;
                List<CarDTO> list = new ArrayList<>();
                if (search == null) {
                    search = "";
                }

                if (quantity == null || quantity == "") {
                    numOfCar = 0;
                } else {
                    numOfCar = Integer.parseInt(quantity);
                }

                if (category == null) {
                    category = "";
                }
                if (index != null) {
                    indexPage = Integer.parseInt(index);
                    numberPage = dao.getNumberPage(search, category);
                    list = dao.getPaging(indexPage, search, category, numOfCar);
                } else if (indexPage == 0) {
                    indexPage = 1;
                    numberPage = dao.getNumberPage(search, category);
                    list = dao.getPaging(indexPage, search, category, numOfCar);
                }

                if (list != null) {
                    request.setAttribute("LIST", list);
                    session.setAttribute("SEARCH", search);
                    session.setAttribute("CATEGORY", category);
                    request.setAttribute("NUMBER_PAGE", numberPage);
                    request.setAttribute("CURRENT_PAGE", indexPage);

                    url = SUCCESS;

                } else {
                    request.setAttribute("MESSAGE", "Not found!");
                }
            } else {
                request.setAttribute("MESSAGE", "Date error!");
                session.setAttribute("DAYSTART", null);
                session.setAttribute("DAYEND", null);
            }
        } catch (Exception e) {
            log("Error at MenuController : " + e.toString());
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
