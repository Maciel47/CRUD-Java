/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.controller;


import br.com.controledeusuario.dao.EstadoDAOImpl;
import br.com.controledeusuario.dao.GenericDAO;
import br.com.controledeusuario.model.Estado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tassia
 */
@WebServlet(name = "SalvarEstado", urlPatterns = {"/SalvarEstado"})
public class SalvarEstado extends HttpServlet {

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
        //RECEBER DADOS
        Estado objetoEstado = new Estado();//Instancia de um objeto
        
        //objetoEstado.setIdEstado(Integer.parseInt(request.getParameter("id")));
        objetoEstado.setNomeEstado(request.getParameter("nome"));
        objetoEstado.setSiglaEstado(request.getParameter("sigla"));
        String mensagem ="" ;
        //PROCESSAR | ARMAZENA
        try{
            GenericDAO dao = new EstadoDAOImpl();            
            
            if(request.getParameter("id").equals("")){            
                if(dao.cadastrar(objetoEstado)){
                   mensagem = objetoEstado.getSiglaEstado() + ", Cadastrado com Sucesso";
                }else{
                   mensagem = objetoEstado.getSiglaEstado() + ", Erro ao cadastrar Estado";        
                }
            }else{ 
                objetoEstado.setIdEstado(Integer.parseInt(request.getParameter("id")));
                if(dao.alterar(objetoEstado)){                   
                   mensagem = objetoEstado.getSiglaEstado() + ", Alterado com Sucesso";
                }else{
                   mensagem = objetoEstado.getSiglaEstado() + ", Erro ao alterar Estado";        
                }
            }   
        }catch(Exception ex){
            System.out.println("Erro no Servlet SalvarEstado \n Erro "+ ex.getMessage());
            ex.printStackTrace();
        }
        //SAÍDA
        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("ListarEstado").forward(request, response);
        
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
