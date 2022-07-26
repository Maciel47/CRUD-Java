/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;

import br.com.controledeusuario.model.Cidade;
import br.com.controledeusuario.model.Estado;
import br.com.controledeusuario.util.ConnectionFactory;
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
public class CidadeDAOImpl implements GenericDAO {

    private Connection conn;

    public CidadeDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        PreparedStatement stmt = null;
        Cidade oCidade = (Cidade) object;
        String sql = "INSERT INTO cidade("
                + " nomecidade, idestado)"
                + " VALUES(?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oCidade.getNomeCidade());
            stmt.setInt(2, oCidade.getEstado().getIdEstado());
            stmt.execute();
            return true;
        } catch (SQLException ex) {//Caso dê problema
            System.out.println("Problemas ao cadastrar cidade \n Erro:" + ex.getMessage());
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
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT idcidade, nomecidade, siglaestado, nomeestado"
                + " from estado, cidade where cidade.idestado = estado.idestado ";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cidade oCidade = new Cidade();
                oCidade.setIdCidade(rs.getInt("idcidade"));
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                //Adicionar construtor com os atributos especificos na classe Estado
                oCidade.setEstado(new Estado(rs.getString("nomeestado"), rs.getString("siglaestado")));

                resultado.add(oCidade);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao Listar Cidade \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            //Fecha a conexão
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar parametros de conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM cidade WHERE idcidade=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro no ExcluirCidadeDAOImpl: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fechar(conn, stmt, null);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cidade oCidade = null;

        String sql = "SELECT idcidade, nomecidade, siglaestado, nomeestado, cidade.idestado "
                + "FROM estado, cidade "
                + "WHERE cidade.idestado = estado.idestado "
                + "AND cidade.idcidade = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                oCidade = new Cidade();
                oCidade.setIdCidade(rs.getInt("idcidade"));
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                //Adicionar construtor com os atributos especificos na classe Estado
                Estado oEstado = new Estado(rs.getInt("idestado"),
                        rs.getString("nomeestado"),
                        rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao Carregar EstadoDAOImpl \nErro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar Conexão: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCidade;
    }

    @Override
    public Boolean alterar(Object object) {
        Cidade oCidade = (Cidade) object;
        PreparedStatement stmt = null;
        String sql = "UPDATE cidade SET"
                + " nomecidade = ?,"
                + " idestado = ?"
                + " WHERE idcidade = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oCidade.getNomeCidade());
            //parametro chave
            stmt.setInt(2, oCidade.getEstado().getIdEstado());
            stmt.setInt(3, oCidade.getIdCidade());
            stmt.execute();
            return true;
            //Dicaaaaaa copiar do catch para baixo
        } catch (Exception ex) {
            System.out.println("Erro ao alterarCidade (DAO) \n Erro: " + ex.getMessage());
            ex.printStackTrace();//detalha o erro
            return false;
        } finally {
            //é executado de qualquer forma //Obrigatoriamente devemos fechar a conexão e parametros
            try {
                ConnectionFactory.fechar(conn, stmt, null);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar parametros de conexão Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}
