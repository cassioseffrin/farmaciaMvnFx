package br.unc.rener.farmaciafx.classes;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable, Comparable<Object> {


	private static final long serialVersionUID = 1L;
	private String telefone;
	private String endereco;

	public Cliente() {
	}

	public Cliente(int id, String nome, String rg, String cpf, String endereco, String telefone) {
		super(id, nome, cpf, rg,endereco, telefone);
		this.telefone = telefone;
		this.endereco = endereco;

	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public void setTelefone(String f) {
		this.telefone = f;
	}

	public String getTelefone() {
		return this.telefone;
	}
	
	@Override
	public String toString() {
		return super.toString() + ";" + this.telefone + "\n";
	}

	public String imprimir() {
		return String.format("Nome %s\nCPF: %s\nRG: %s\nFone: %s\nEndereco: %s\n ********************\n", getNome(),
				getCpf(), getRg(), getTelefone(), getEndereco());
	}

	@Override
	public int compareTo(Object o) {
		Cliente e = (Cliente) o;

		return this.getNome().toLowerCase().compareTo(e.getNome().toLowerCase());

	}
}
