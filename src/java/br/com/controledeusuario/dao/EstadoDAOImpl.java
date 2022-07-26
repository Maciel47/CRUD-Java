/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;

import br.com.controledeusuario.model.Estado;
import br.com.controledeusuario.util.ConnectionFactory;
import com.sun.xml.ws.tx.at.v10.types.PrepareResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tassia
 */
//Implementar todos os métodos abstratos
public class EstadoDAOImpl implements GenericDAO {

    //Conexão com o BD
    Connection conn;

    public EstadoDAOImpl() throws Exception {
        //Toda as vezes que chamar new EstadoDAOImpl()
        //ele se conecta no banco de dados
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override//Sobreescrito
    public Boolean cadastrar(Object object) {//Programação Orientado a Objeto (Modelo)
        Estado oEstado = (Estado) object; //Transforma o objeto em um estado
        PreparedStatement stmt = null;//Manipula SQL
        String sql = "insert into estado(siglaestado, nomeestado)"
                + "values (?, ?);";//? significa parametro, que será passado depois pelo objeto.

        try {//Tentar
            stmt = conn.prepareStatement(sql);//Prepara o SQL para ser executado.
            stmt.setString(1, oEstado.getSiglaEstado());
            stmt.setString(2, oEstado.getNomeEstado());
            //Executa o sql na conexão
            stmt.execute();
            //Se tudo der certo retorna verdadeiro
            return true;
        } catch (SQLException ex) {//Caso dê problema
            System.out.println("Problemas ao cadastrar estado \n Erro:" + ex.getMessage());
            ex.printStackTrace();
            //Se der algum erro retornar falso.
            return false;
        } finally {//Executa de qualquer forma       
            try {
                ConnectionFactory.fechar(conn, stmt, null);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \n Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        //Declarar quem vai manipular os dados
        PreparedStatement stmt = null;
        ResultSet rs = null;//add importação
        //Criação do Vetor
        List<Object> resultado = new ArrayList<>();//add importação
        //Comando resposável por buscar todos os dados
        String sql = "SELECT * FROM  estado";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Estado oEstado = new Estado();
                oEstado.setIdEstado(rs.getInt("idestado"));
                oEstado.setNomeEstado(rs.getString("nomeestado"));
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                resultado.add(oEstado);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Estado \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas para fechar a conexão \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM estado WHERE idestado=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao Excluir estado: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fechar(conn, stmt, null);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar Conexão: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;//TODOS OS MÉTODOS TEM STMT
        ResultSet rs = null;//ARMAZENA O RETORNO
        Estado oEstado = null;
        String sql = "SELECT * FROM estado WHERE idestado =  ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oEstado = new Estado();
                oEstado.setIdEstado(rs.getInt("idestado"));
                oEstado.setNomeEstado(rs.getString("nomeestado"));
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro no CarregarEstadoDAOImpl: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar parametros de conexão: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oEstado;
    }

    @Override
    public Boolean alterar(Object object) {
        PreparedStatement stmt = null;
        Estado oEstado = (Estado) object;        
        String sql = "UPDATE estado SET nomeestado = ?, siglaestado = ? WHERE idestado = ?";        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oEstado.getNomeEstado());
            stmt.setString(2, oEstado.getSiglaEstado());
            stmt.setInt(3, oEstado.getIdEstado());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Erro Alterar EstadoDAOImpl: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try{
                ConnectionFactory.fechar(conn, stmt, null);
            }catch(Exception ex){
                System.out.println("Erro ao fechar conexão " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        
    }

}
