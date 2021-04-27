/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CarDAO;
import dtos.HistoryDTO;
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
public class DetailHistoryController extends HttpServlet {

    private final static String SUCCESS = "detail.jsp";
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
        HttpSession session = request.getSession();
        CarDAO dao = new CarDAO();
        String url = SUCCESS;
        try {
            String userID = (String) session.getAttribute("USERID");
            
            String carName = request.getParameter("txtCarName");
            String rentID = request.getParameter("txtRentID");
            String startDay = request.getParameter("txtStartDay");
            
            List<HistoryDTO> list = new ArrayList<>();
            
            if(rentID != null){
                session.setAttribute("rentID", rentID);
            } else {
                rentID = (String)session.getAttribute("rentID");
                
            }
            Boolean check = true;
            
            if (userID != null) {
                
                if(carName == null){
                    carName = "";
                }
                if(startDay == null){
                    startDay = "";
                }
                
                if(startDay.equals("")){
                    list = dao.getHistory2(carName, userID, Integer.parseInt(rentID), startDay);
                } else {
                    list = dao.getHistory(carName, userID, Integer.parseInt(rentID), startDay);
                }
                
                
                
                if(list != null){
                    request.setAttribute("HISTORYDETAIL", list);
                } else if(list == null){
                    request.setAttribute("MESSAGE", "Not found!");
                }
            } else{
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at DetailHistoryController : " + e.toString());
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
