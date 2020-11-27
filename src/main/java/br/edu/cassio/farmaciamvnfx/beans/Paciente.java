
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;

public class Paciente extends Pessoa{
    private int gravidade;

    public Paciente() {
    }
    
    
    public Paciente(String nome, int rg, int cpf, String sexo, LocalDate dataNascimento, int telefone, int gravidade){
        super(nome, rg, cpf, sexo, dataNascimento, telefone);
        this.gravidade = gravidade;
    }

    public int getGravidade() {
        return gravidade;
    }

    public void setGravidade(int gravidade) {
        this.gravidade = gravidade;
    }
    
    
    
}
