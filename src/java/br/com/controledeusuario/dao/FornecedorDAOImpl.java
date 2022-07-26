/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;

import br.com.controledeusuario.model.Cidade;
import br.com.controledeusuario.model.Estado;
import br.com.controledeusuario.model.Fornecedor;
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
public class FornecedorDAOImpl implements GenericDAO{


    Connection conn;
    //construtor com a conexão
    public FornecedorDAOImpl() throws Exception {
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
       Fornecedor oFornecedor = (Fornecedor) object;
       
       String sql = "INSERT INTO FORNECEDOR (url, observacao, situacao, permitelogin, idpessoa)" 
               + "VALUES (?, ?, ?, ?, ?)";
       
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oFornecedor.getUrl());
            stmt.setString(2, oFornecedor.getObservacao());
            stmt.setString(3, oFornecedor.getSituacao());
            stmt.setString(4, oFornecedor.getPermiteLogin());
            try {
                stmt.setInt(5, new PessoaDAOImpl().cadastrar(oFornecedor));
            }catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa (Fornecedor)");
            }
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Erro ao salvar Fornecedor (Pessoa) Erro: " + ex.getMessage());
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
        String sql = "select f.idfornecedor, f.url, p.idpessoa, p.nome, p.cpf, ci.nomecidade, e.siglaestado "
                + "from pessoa p, fornecedor f, estado e, cidade ci "
                + "where f.idpessoa = p.idpessoa and p.idcidade = ci.idcidade and ci.idestado = e.idestado "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor oFornecedor = new Fornecedor();
                oFornecedor.setIdFornecedor(rs.getInt("idfornecedor"));
                oFornecedor.setIdPessoa(rs.getInt("idpessoa"));
                oFornecedor.setNome(rs.getString("nome"));
                oFornecedor.setCpf(rs.getString("cpf"));
                Cidade oCidade = new Cidade();
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
                oFornecedor.setCidade(oCidade);
                oFornecedor.setUrl (rs.getString("url"));
                
                resultado.add(oFornecedor);                
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao listar Fornecedor \nErro: " + ex.getMessage());
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
        String sql = "delete from fornecedor where idpessoa = ?; commit; delete from pessoa where idpessoa = ?;";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch(Exception ex) {
            System.out.println("Erro ao Excluir Fornecedor \nErro: " + ex.getMessage());
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
        Fornecedor oFornecedor = new Fornecedor();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select p.*, f.*, ci.*, e.* "
                + "from pessoa p, fornecedor f, cidade ci, estado e "
                + "where p.idpessoa = f.idpessoa and p.idcidade = ci.idcidade and ci.idestado = e.idestado and f.idpessoa = ? "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oFornecedor.setIdPessoa(rs.getInt("idpessoa"));
                oFornecedor.setNome(rs.getString("nome"));
                oFornecedor.setCpf(rs.getString("cpf"));
                oFornecedor.setDatanascimento(rs.getDate("datanascimento"));
                oFornecedor.setLogin(rs.getString("login"));
                oFornecedor.setSenha(rs.getString("senha"));
                Cidade oCidade = new Cidade();
                oCidade.setIdCidade(Integer.parseInt(rs.getString("idcidade")));
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
                oFornecedor.setCidade(oCidade);
                oFornecedor.setUrl(rs.getString("url"));
                oFornecedor.setSituacao(rs.getString("situacao"));
                oFornecedor.setObservacao(rs.getString("observacao"));
                oFornecedor.setPermiteLogin(rs.getString("permitelogin"));

            }
        }catch (SQLException ex) {
            System.out.println("Erro ao listar Fornecedor \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oFornecedor;
    }

    @Override
    public Boolean alterar(Object object) {
     PreparedStatement stmt = null;
     Fornecedor oFornecedor = (Fornecedor) object;
     String sql = "update administrador set caminhofoto = ?, situacao = ?, permitelogin = ? where idpessoa = ?;";
     try {
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, oFornecedor.getUrl());
         stmt.setString(2, oFornecedor.getSituacao());
         stmt.setString(3, oFornecedor.getPermiteLogin());
         stmt.setInt(4, oFornecedor.getIdPessoa());
         try {
             if (new PessoaDAOImpl().alterar(oFornecedor)) {
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
    
 public Fornecedor logarForn (String login, String senha) {
        
        Fornecedor oFornecedor = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from pessoa, fornecedor where pessoa.idpessoa = fornecedor.idpessoa " 
                + "and pessoa.login = ? "
                + "and pessoa.senha = ? "
                + "and fornecedor.permitelogin = 'S';";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()) {
                oFornecedor = new Fornecedor();
                //Atributos Pessoa - Classe Pai
                oFornecedor.setIdPessoa(rs.getInt("idpessoa"));
                oFornecedor.setNome(rs.getString("nome"));
                oFornecedor.setLogin(rs.getString("login"));
                //Atributos ADM
                oFornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                oFornecedor.setUrl(rs.getString("url"));
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao logar Fornecedor \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oFornecedor;
    }
    
 }
    
