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
public class Estado {
    private int idEstado;
    private String nomeEstado;
    private String siglaEstado;

    //Criar espaço em memórias
    //Botao direito > Inserir Código > Construtor (Não marca nada)
    public Estado() {
    }

    //Criar espaço em memória
    //Botao direito > Inserir Código > Construtor (marca tudo)
    public Estado(int idEstado, String nomeEstado, String siglaEstado) {
        this.idEstado = idEstado;
        this.nomeEstado = nomeEstado;
        this.siglaEstado = siglaEstado;
    }

    public Estado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Estado(String nomeEstado, String siglaEstado) {
        this.nomeEstado = nomeEstado;
        this.siglaEstado = siglaEstado;
    }

   
    
    
    
    //Botão Direito > Inserir Código >> Getters e Setters (Marca tudo)

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
    
}
