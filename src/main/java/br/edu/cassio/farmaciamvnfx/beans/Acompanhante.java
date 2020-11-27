
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;

public class Acompanhante extends Pessoa{
    private Paciente paciente;
    
    public Acompanhante(String nome, int rg, int cpf, String sexo, LocalDate dataNascimento, int telefone, 
            Paciente paciente){
        super(nome, rg, cpf, sexo, dataNascimento, telefone);
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
    
}
