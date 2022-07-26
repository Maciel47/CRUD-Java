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
public class Fornecedor extends Pessoa{
    private Integer idFornecedor;
    private String url;
    private String observacao;
    private String situacao;
    private String permiteLogin;

       
    public Fornecedor(){
        
    }

    public Fornecedor(Integer idFornecedor, String url, String observacao, String situacao, String permiteLogin) {
        this.idFornecedor = idFornecedor;
        this.url = url;
        this.observacao = observacao;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
    }

    public Fornecedor(Integer idFornecedor, String url, String observacao, String situacao, String permiteLogin, Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha, Cidade cidade) {
        super(idPessoa, cpf, nome, datanascimento, login, senha, cidade);
        this.idFornecedor = idFornecedor;
        this.url = url;
        this.observacao = observacao;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
    }
    
    
    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPermiteLogin() {
        return permiteLogin;
    }

    public void setPermiteLogin(String permiteLogin) {
        this.permiteLogin = permiteLogin;
    }
    
    
    
    
}
