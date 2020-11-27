
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalTime;

public class Internacao {
    private int id;
    Paciente paciente;
    LocalTime horario;
    String quarto;
    Acompanhante acompanhante;

    public Internacao(int id, Paciente paciente, LocalTime horario, String quarto, Acompanhante acompanhante){
        this.id = id;
        this.paciente = paciente;
        this.horario = horario;
        this.quarto = quarto;
        this.acompanhante = acompanhante;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public Acompanhante getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(Acompanhante acompanhante) {
        this.acompanhante = acompanhante;
    }
    
    
    
}
