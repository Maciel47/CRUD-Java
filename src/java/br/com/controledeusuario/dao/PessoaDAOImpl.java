/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;

import br.com.controledeusuario.model.Pessoa;
import br.com.controledeusuario.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ghost
 */
public class PessoaDAOImpl {

    private Connection conn;
    
    public PessoaDAOImpl() throws Exception {
        try {
            conn = ConnectionFactory.conectar();
            System.out.println("Conectado com sucesso");         
        }catch (Exception ex){
            throw new Exception (ex.getMessage());
        }
    }
    
    //cadastrar
    public Integer cadastrar(Pessoa pessoa) {
       PreparedStatement stmt = null; //Permite manipular SQL
       ResultSet rs = null; //Armazena Retorno
       
       Integer idPessoa = null;
       
       String sql = "INSERT INTO PESSOA (cpf, nome, datanascimento, login, senha, idcidade)" 
               + "VALUES (?, ?, ?, ?, ?, ?) RETURNING (idpessoa)";
       try{
           stmt = conn.prepareStatement(sql);
           stmt.setString(1, pessoa.getCpf());
           stmt.setString(2, pessoa.getNome());
           stmt.setDate(3, new java.sql.Date(pessoa.getDatanascimento() .getTime()));//pega data e horario, depois converte para data sql
           stmt.setString(4, pessoa.getLogin());
           stmt.setString(5, pessoa.getSenha());
           stmt.setInt(6, pessoa.getCidade() .getIdCidade());
           rs = stmt.executeQuery();
           
           if(rs.next()) {
               idPessoa = rs.getInt("idpessoa");
           }
           
       }catch (Exception ex) {
            System.out.println("Erro ao SalvarPessoa Erro:" + ex.getMessage());
            ex.printStackTrace();
       }finally{
           try {
               ConnectionFactory.fechar(conn, stmt, rs);
           }catch (Exception ex) {
               System.out.println("Erro ao Fechar Conexão Erro:" + ex.getMessage());
               ex.printStackTrace();
           }
       }
       return idPessoa;
    }
    
    public Boolean alterar (Object object) {
        //Existe o alterar para não repertirmos códigos.
        Pessoa oPessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        
        String sql = "update pessoa set"
                + "nome = ?, cpf = ?, datanascimento = ?, login = ?, senha = ?, idcidade = ?"
                + "where idpessoa = ?;";
        try {
            //Passagem dos parametros
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNome());
            stmt.setString(2, oPessoa.getCpf());
            stmt.setDate(3, new java.sql.Date(oPessoa.getDatanascimento().getTime()));
            stmt.setString(4, oPessoa.getLogin());
            stmt.setString(5, oPessoa.getSenha());
            stmt.setInt(6, oPessoa.getCidade().getIdCidade());
            stmt.setInt(7, oPessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;         
        }catch (Exception ex){
            System.out.println("Erro ao alterar Pessoa \nErro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try {
                ConnectionFactory.fechar(conn, stmt, null);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
