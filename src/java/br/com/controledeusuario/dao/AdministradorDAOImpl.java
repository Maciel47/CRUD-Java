/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;

import br.com.controledeusuario.model.Administrador;
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
 * @author Ghost
 */
public class AdministradorDAOImpl implements GenericDAO {

    Connection conn;
    //construtor com a conexão
    public AdministradorDAOImpl() throws Exception {
        try{
            conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    @Override
    public Boolean cadastrar(Object object) {
       PreparedStatement stmt = null;
       Administrador oAdministrador = (Administrador) object;
       
       String sql = "INSERT INTO ADMINISTRADOR (situacao, permitelogin, caminhofoto, idpessoa)" 
               + "VALUES (?, ?, ?, ?)";
       
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oAdministrador.getSituacao());
            stmt.setString(2, oAdministrador.getPermiteLogin());
            stmt.setString(3, oAdministrador.getCaminhofoto());
            try {
                stmt.setInt(4, new PessoaDAOImpl().cadastrar(oAdministrador));
            }catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa (Administrador)");
            }
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Erro ao salvar Administrador (Pessoa) Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try{
                ConnectionFactory.fechar(conn, stmt, null);                
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexao Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList <>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.idpessoa, p.nome, p.cpf, a.idadministrador, a.caminhofoto, e.siglaestado, ci.nomecidade "
                + "from pessoa p, administrador a, cidade ci, estado e "
                + "where p.idpessoa = a.idpessoa and p.idcidade = ci.idcidade and ci.idestado = e.idestado "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador oAdministrador = new Administrador();
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setCpf(rs.getString("cpf"));
                Cidade oCidade = new Cidade();
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
                oAdministrador.setCidade(oCidade);
                oAdministrador.setCaminhofoto(rs.getString("caminhofoto"));
                
                resultado.add(oAdministrador);                
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao listar Administrador \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally {
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
        String sql = "delete from administrador where idpessoa = ?; commit; delete from pessoa where idpessoa = ?;";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch(Exception ex) {
            System.out.println("Erro ao Excluir Administrador \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try{
                ConnectionFactory.fechar(conn, stmt, null);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        Administrador oAdministrador = new Administrador();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select p.*, a.*, ci.*, e.* "
                + "from pessoa p, administrador a, cidade ci, estado e "
                + "where p.idpessoa = a.idpessoa and p.idcidade = ci.idcidade and ci.idestado = e.idestado and a.idpessoa = ? "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setCpf(rs.getString("cpf"));
                oAdministrador.setDatanascimento(rs.getDate("datanascimento"));
                oAdministrador.setLogin(rs.getString("login"));
                oAdministrador.setSenha(rs.getString("senha"));
                Cidade oCidade = new Cidade();
                oCidade.setIdCidade(Integer.parseInt(rs.getString("idcidade")));
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
                oAdministrador.setCidade(oCidade);
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setCaminhofoto(rs.getString("caminhofoto"));
                oAdministrador.setSituacao(rs.getString("situacao"));
                oAdministrador.setPermiteLogin(rs.getString("permitelogin"));
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao listar Administrador \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oAdministrador;
    }

    @Override
    public Boolean alterar(Object object) {
     PreparedStatement stmt = null;
     Administrador oAdministrador = (Administrador) object;
     String sql = "update administrador set caminhofoto = ?, situacao = ?, permitelogin = ? where idpessoa = ?;";
     try {
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, oAdministrador.getCaminhofoto());
         stmt.setString(2, oAdministrador.getSituacao());
         stmt.setString(3, oAdministrador.getPermiteLogin());
         stmt.setInt(4, oAdministrador.getIdPessoa());
         try {
             if (new PessoaDAOImpl().alterar(oAdministrador)) {
                 stmt.executeUpdate();
                 return true;
             }else{
                 return false;
             }
         }catch (Exception ex) {
             System.out.println("Erro ao salvar Administrador(Pessoa) \nErro: " + ex.getMessage());
             ex.printStackTrace();
             return false;
         }
     }catch (Exception ex) {
         System.out.println("Erro ao salvar Administrador(Pessoa) \nErro: " + ex.getMessage());
         ex.printStackTrace();
         return false;
     }finally{
         try {
             ConnectionFactory.fechar(conn, stmt, null);
         }catch (Exception ex) {
             System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
             ex.printStackTrace();
            }
        }
    }
    
    public Administrador logarAdm (String login, String senha) {
        
        Administrador oAdministrador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from pessoa, administrador where pessoa.idpessoa = administrador.idpessoa " 
                + "and pessoa.login = ? "
                + "and pessoa.senha = ? "
                + "and administrador.permitelogin = 'S';";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()) {
                oAdministrador = new Administrador();
                //Atributos Pessoa - Classe Pai
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setLogin(rs.getString("login"));
                //Atributos ADM
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setCaminhofoto(rs.getString("caminhofoto"));
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao logar ADM \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oAdministrador;
    }
}