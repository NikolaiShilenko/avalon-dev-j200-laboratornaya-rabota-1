/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikolai Shilenko
 */
public class StringParser extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String stringReq = request.getParameter("text");
        String stringResp = null;
        if (stringReq != null) {
            if (stringReq.trim().isEmpty()) {
                stringResp = "Please, repeat enter";
            } else if (Pattern.compile("\\d+").matcher(stringReq).matches()) {
                try {
                    // решение по условию лабораторной работы
                    //long num = Long.parseLong(stringReq);
                    //stringResp = Long.toString(((long) (Math.random() * (Long.MAX_VALUE - num) + 1)) + num);

                    // изменил условие и вычисляю квадратный корень из введённого числа
                    double num = (double) Long.parseLong(stringReq);
                    stringResp = "Square root of the entered integer: "
                            + "<font color=red >"
                            + Math.sqrt(num)
                            + "</font>";
                } catch (NumberFormatException e) {
                    stringResp = "Please, enter another integer";
                }
            } else {
                int countWords = new java.util.StringTokenizer(stringReq, " \t\n\r,.!?:;").countTokens();
                stringResp = "The text: "
                        + "<p>" + stringReq + "</p>"
                        + "contains words: "
                        + "<font color=red >"
                        + String.valueOf(countWords)
                        /*+ String.valueOf(stringReq.split("\\P{L}+").length*/ // не учитывает числа и создаёт массив, что затратно и не требуется по условию задачи
                        + "</font>";
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StringParser</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form method=post action=StringParser>");
            out.println("<b>Enter any text or integer greater than 0 and less than <b>" + Long.MAX_VALUE);
            out.println("<br>");
            out.println("<p><textarea name=text rows=1 cols=45></textarea></p>");
            //out.println("<p><input type = text name = text size = 40></p>");
            out.println("<br>");
            out.println("<p><input name=send type=submit  value=Send></p>");
            out.println("</form>");
            out.println("<hr>");
            out.println("<br>");
            if (stringResp != null) {
                out.println(stringResp);
            }

            out.println("</body>");
            out.println("</html>");
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
