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
public class Pessoa {
    private Integer idPessoa;
    private String cpf;
    private String nome;
    private Date datanascimento;
    private String login;
    private String senha;
    private Cidade idCidade;

    public Pessoa() {
    }

    public Pessoa(Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha, Cidade idCidade) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.login = login;
        this.senha = senha;
        this.idCidade = idCidade;
    }

    public Cidade getCidade() {
        return idCidade;
    }

    public void setCidade(Cidade idCidade) {
        this.idCidade = idCidade;
    }   

    /**
     * @return the idPessoa
     */
    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the datanascimento
     */
    public Date getDatanascimento() {
        return datanascimento;
    }

    /**
     * @param datanascimento the datanascimento to set
     */
    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
