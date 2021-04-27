/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.EmailUtils;

/**
 *
 * @author SE140066
 */
public class RegisterController extends HttpServlet {

    private static final String SUCCESS = "confirmemail.jsp";
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
        String message = null;
        request.setAttribute("ERROR", null);

        try {
            String userID = request.getParameter("txtUserID");
            String fullName = request.getParameter("txtFullName");
            String roleID = request.getParameter("txtRoleID");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String address = request.getParameter("txtAddress");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            boolean check = true;

            if (!email.trim().equals("")) {
                if (!email.matches("(\\w*\\d*)+@(\\w+\\.\\w+){1}(\\.\\w+)?")) {
                    message = "Wrong format Email!";
                    check = false;
                }
            }

            if (!confirm.equals(password)) {
                message = "Confirm Password not match";
                check = false;
            }
            UserDAO dao = new UserDAO();
            boolean checkID = dao.checkID(userID);
            if (checkID) {
                message = "Dupplicate ID";
                check = false;
            }
            if (check) {
                EmailUtils mail = new EmailUtils();
                String code = mail.getAlphaNumericString(6);
                UserDTO user = new UserDTO(userID, fullName, password, roleID, phoneNumber, email, address);
                dao.create(user);
                mail.sendMail(email, code);
                HttpSession session = request.getSession();
                session.setAttribute("EMAIL", email);
                session.setAttribute("cCODE", code);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", message);
            }
        } catch (Exception e) {
            System.out.println("Error at RegisterController: " + e.toString());
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
