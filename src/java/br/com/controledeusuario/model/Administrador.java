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
public class Administrador extends Pessoa{
    private Integer idAdministrador;
    private String situacao;
    private String permiteLogin;
    private String caminhofoto;

    public Administrador(Integer idAdministrador, String situacao, String permiteLogin, String caminhofoto) {
        this.idAdministrador = idAdministrador;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
        this.caminhofoto = caminhofoto;
    }

    public Administrador(Integer idAdministrador, String situacao, String permiteLogin, String caminhofoto, Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha, Cidade cidade) {
        super(idPessoa, cpf, nome, datanascimento, login, senha, cidade);
        this.idAdministrador = idAdministrador;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
        this.caminhofoto = caminhofoto;
    }

    

    public String getCaminhofoto() {
        return caminhofoto;
    }

    public void setCaminhofoto(String caminhofoto) {
        this.caminhofoto = caminhofoto;
    }

    

    public Administrador(){
        
    }
    
    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
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
