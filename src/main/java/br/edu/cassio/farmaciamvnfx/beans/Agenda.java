
package br.edu.cassio.farmaciamvnfx.beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
    private int id;
    private LocalDate data;
    //private LocalTime horario;
    private String horario;
    private String paciente;

    public Agenda(LocalDate data, String horario, String paciente){
        this.data = data;
        this.horario = horario;
        this.paciente = paciente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
    
    
    
    
}
