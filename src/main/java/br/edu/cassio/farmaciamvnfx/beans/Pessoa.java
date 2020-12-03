
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;

public  class Pessoa {
    private int id;
    private String nome;
    private int rg;
    private int cpf;
    private String sexo;
    LocalDate dataNascimento;
    int telefone;

    public Pessoa() {
    }
    
    
    
    public Pessoa(String nome, int rg, int cpf, String sexo, LocalDate dataNascimento, int telefone){
        this.nome = nome;
        this.rg = rg;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    
    
}
