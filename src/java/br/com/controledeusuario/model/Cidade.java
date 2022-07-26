/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controledeusuario.model;

/**
 *
 * @author Tassia
 */
public class Cidade {
    private Integer idCidade;
    private String nomeCidade;
   
    private Estado estado;
    //Botão Direito >>> Inserir Código >>Construtor
    public Cidade() {
    }

    public Cidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
    

    public Cidade(Integer idCidade, String nomeCidade, Estado estado) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public Cidade(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Botão Direito >>> Inserir Código >>Getters e Setters
    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
