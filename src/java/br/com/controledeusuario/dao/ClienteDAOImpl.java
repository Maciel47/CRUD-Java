/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.dao;
import br.com.controledeusuario.model.Cidade;
import br.com.controledeusuario.model.Cliente;
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
public class ClienteDAOImpl implements GenericDAO {
    Connection conn;
    //construtor com a conexão
    public ClienteDAOImpl() throws Exception {
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
        Cliente oCliente = (Cliente) object;
        
        String sql = "INSERT INTO cliente (observacao, situacao, permitelogin, idpessoa) VALUES (?, ?, ?, ?)";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oCliente.getObservacao());
            stmt.setString(2, oCliente.getSituacao());
            stmt.setString(3, oCliente.getPermiteLogin());
            try {
                //Cadastra a pessoa e retorna o id para a filha
                stmt.setInt(4, new PessoaDAOImpl().cadastrar(oCliente));
            }catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa (cliente)");
            }
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Erro ao salvar cliente(Pessoa) Erro: " + ex.getMessage());
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
        String sql = "select c.idcliente, c.observacao, p.idpessoa, p.nome, p.cpf, ci.nomecidade, e.siglaestado "
                + "from pessoa p, cliente c, estado e, cidade ci "
                + "where c.idpessoa = p.idpessoa and p.idcidade=ci.idcidade and ci.idestado=e.idestado "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente oCliente = new Cliente();
                oCliente.setIdCliente(rs.getInt("idcliente"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setObservacao(rs.getString("observacao"));
                Cidade oCidade = new Cidade();
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCidade.setEstado(oEstado);
                oCliente.setCidade(oCidade);
                
                resultado.add(oCliente);                
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao Listar Cliente \nErro: " + ex.getMessage());
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
        String sql = "delete from cliente where idpessoa = ?;"
                + "commit;"
                + "delete from pessoa where idpessoa = ?;";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch(Exception ex) {
            System.out.println("Erro ao Excluir Cliente \nErro: " + ex.getMessage());
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
        Cliente oCliente = new Cliente();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select p.*, c.*, ci.*, e.* "
                + "from pessoa p, cliente c, cidade ci, estado e "
                + "where p.idpessoa = c.idpessoa and p.idcidade = ci.idcidade and ci.idestado = e.idestado and c.idpessoa = ? "
                + "order by p.nome;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                //Atributos Pessoa - Classe Pai
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setDatanascimento(rs.getDate("datanascimento"));
                oCliente.setLogin(rs.getString("login"));
                oCliente.setSenha(rs.getString("senha"));
                //Criar um construtor na Classe Cidade (Selecionar somente idcidade, nomecidade)
                Cidade oCidade = new Cidade();
                oCidade.setIdCidade(Integer.parseInt(rs.getString("idcidade")));
                oCidade.setNomeCidade(rs.getString("nomecidade"));
                Estado oEstado = new Estado();
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                oCidade.setEstado(oEstado);
                oCliente.setCidade(oCidade);
                oCliente.setObservacao(rs.getString("observacao"));
                oCliente.setSituacao(rs.getString("situacao"));
                oCliente.setPermiteLogin(rs.getString("permitelogin"));
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao listar Cliente \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCliente;
    }

    @Override
    public Boolean alterar(Object object) {
     PreparedStatement stmt = null;
     Cliente oCliente = (Cliente) object;
     String sql = "update cliente set observacao = ?, situacao = ?, permitelogin = ? where idpessoa = ?;";
     try {
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, oCliente.getObservacao());
         stmt.setString(2, oCliente.getSituacao());
         stmt.setString(3, oCliente.getPermiteLogin());
         stmt.setInt(4, oCliente.getIdPessoa());
         try {
             if (new PessoaDAOImpl().alterar(oCliente)) {
                 stmt.executeUpdate();
                 return true;
             }else{
                 return false;
             }
         }catch (Exception ex) {
             System.out.println("Erro ao salvar Cliente(Pessoa) \nErro: " + ex.getMessage());
             ex.printStackTrace();
             return false;
         }
     }catch (Exception ex) {
         System.out.println("Erro ao salvar Cliente(Pessoa) \nErro: " + ex.getMessage());
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
    
    public Cliente logarCliente (String login, String senha) {
        
        Cliente oCliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from pessoa, cliente where pessoa.idpessoa = cliente.idpessoa " 
                + "and pessoa.login = ? "
                + "and pessoa.senha = ? "
                + "and cliente.permitelogin = 'S';";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()) {
                oCliente = new Cliente();
                //Atributos Pessoa - Classe Pai
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setLogin(rs.getString("login"));
                //Atributos Cliente
                oCliente.setIdCliente(rs.getInt("idcliente"));
                oCliente.setObservacao(rs.getString("observacao"));
            }
        }catch (SQLException ex) {
            System.out.println("Erro ao logar cliente \nErro: " + ex.getMessage());
            ex.printStackTrace();
        }finally {
            try {
                ConnectionFactory.fechar(conn, stmt, rs);
            }catch (Exception ex) {
                System.out.println("Erro ao fechar conexão \nErro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCliente;
    } 
    
}
