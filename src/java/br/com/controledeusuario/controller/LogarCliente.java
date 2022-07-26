/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.controller;



import br.com.controledeusuario.dao.ClienteDAOImpl;
import br.com.controledeusuario.model.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ghost
 */
@WebServlet(name = "LogarCliente", urlPatterns = {"/LogarCliente"})
public class LogarCliente extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        //Receber dados do login
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        if(!login.equals("") && !senha.equals("")) {
            try {
                ClienteDAOImpl dao = new ClienteDAOImpl();
                Cliente oCliente = dao.logarCliente(login, senha);
                if (oCliente != null) {
                    //deu certo
                    HttpSession session = request.getSession(true);
                    session.setAttribute("oClienteLogado", oCliente);
                    request.setAttribute("mensagem", "Seja bem vindo, ");
                    request.getRequestDispatcher("ListarCliente").forward(request, response);
                }else {
                    //deu errado
                    request.setAttribute("mensagem", "Login e/ou senha inválidos");
                    request.getRequestDispatcher("logincliente.jsp").forward(request, response);
                }
            }catch (Exception ex) {
                System.out.println("Erro no Servlet LogarCliente" + ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("mensagem", "Erro Interno, entre em contato com o responsável");
                request.getRequestDispatcher("logincliente.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("mensagem", "Usuario ou senha não preenchidos");
            request.getRequestDispatcher("logincliente.jsp").forward(request, response);
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
