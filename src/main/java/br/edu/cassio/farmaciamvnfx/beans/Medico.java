
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;

public class Medico extends Pessoa{
    private String especialidade;
    
    public Medico(String nome, int rg, int cpf, String sexo, LocalDate dataNascimento, int telefone, String especialidade){
        super(nome, rg, cpf, sexo, dataNascimento, telefone);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    
}
