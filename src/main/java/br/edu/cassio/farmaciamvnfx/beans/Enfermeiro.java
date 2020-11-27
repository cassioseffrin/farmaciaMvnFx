
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;

public class Enfermeiro extends Pessoa{
    private LocalDate dataAdmissao;
    
    public Enfermeiro(String nome, int rg, int cpf, String sexo, LocalDate dataNascimento, int telefone,
            LocalDate dataAdmissao){
        super(nome, rg, cpf, sexo, dataNascimento, telefone);
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    
    
    
}
