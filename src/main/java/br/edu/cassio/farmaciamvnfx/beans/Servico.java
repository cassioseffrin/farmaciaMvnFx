
package br.edu.cassio.farmaciamvnfx.beans;

public class Servico {
    private int id;
    private String descricao;
    private Medico medico;
    private Paciente paciente;
    private Enfermeiro enfermeiro;

    public Servico(int id, String descricao, Medico medico, Paciente paciente, Enfermeiro enfermeiro){
        this.id = id;
        this.descricao = descricao;
        this.medico = medico;
        this.paciente = paciente;
        this.enfermeiro = enfermeiro;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Enfermeiro getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(Enfermeiro enfermeiro) {
        this.enfermeiro = enfermeiro;
    }
    
    
}
