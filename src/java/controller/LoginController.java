/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.VerifyUtils;

/**
 *
 * @author SE140066
 */
public class LoginController extends HttpServlet {

    private static final String SUCCESSUS = "MenuController";
    private static final String SUCCESSAD = "ManageController";
    private static final String ERROR = "login.jsp";

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
        Boolean valid = false;
        
        try {
            HttpSession session = request.getSession();
            request.setAttribute("MESSAGE", null);
            
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            valid = VerifyUtils.verify(gRecaptchaResponse);
            
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            
            UserDAO dao = new UserDAO();
            String name = dao.checkLogin(userID, password);
            String role = dao.checkRole(userID);
            if (valid) {
            if (!"".equals(name)) {
                if (role.equals("us")) {
                    url = SUCCESSUS;
                } else {
                    url = SUCCESSAD;
                }
                
                List<UserDTO> list = dao.getInformation(userID);
                
                session.setAttribute("INFO", list);
                session.setAttribute("USERID", userID);
                session.setAttribute("ROLEID", role);
                session.setAttribute("FULLNAME", name);
                
                String checkIn = String.valueOf(java.time.LocalDate.now());
                session.setAttribute("DATE", checkIn);
            } else {
                request.setAttribute("MESSAGE", "Login information is incorrect");
            }
            } else {
                request.setAttribute("MESSAGE", "Login information is incorrect");
            }

        } catch (Exception e) {
            log("Error at LoginController : " + e.toString());
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
