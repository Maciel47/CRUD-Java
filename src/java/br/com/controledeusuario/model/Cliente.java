/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.model;

import java.util.Date;

/**
 *
 * @author Tassia
 */
//extends representa então a Herança
public class Cliente extends Pessoa {

    private Integer idCliente;
    private String observacao;
    private String situacao;
    private String permiteLogin;

    //Botão Direito > Inserir Código > Construtores

    public Cliente(Integer idCliente, String observacao, String situacao, String permiteLogin) {
        this.idCliente = idCliente;
        this.observacao = observacao;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
    }

    public Cliente(Integer idCliente, String observacao, String situacao, String permiteLogin, Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha, Cidade cidade) {
        super(idPessoa, cpf, nome, datanascimento, login, senha, cidade);
        this.idCliente = idCliente;
        this.observacao = observacao;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
    }
    

    public Cliente() {
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the permiteLogin
     */
    public String getPermiteLogin() {
        return permiteLogin;
    }

    /**
     * @param permiteLogin the permiteLogin to set
     */
    public void setPermiteLogin(String permiteLogin) {
        this.permiteLogin = permiteLogin;
    }

    public void setEstado(Estado estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
