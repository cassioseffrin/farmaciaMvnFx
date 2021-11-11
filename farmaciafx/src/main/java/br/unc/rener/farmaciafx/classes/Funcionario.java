package br.unc.rener.farmaciafx.classes;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable, Comparable<Object>{

	private static final long serialVersionUID = 1L;
		private String pis;
		private String pasep;
		private String carteiraTrabalho;
		private String crf;


		
		public Funcionario() {};
		
		@Override
		public int compareTo(Object o) {
			Cliente e = (Cliente) o;
		
			return this.getNome().compareTo(e.getNome());
		
		}
		
		public Funcionario(int id,String nome, String cpf, String rg, String endereco, String pis, String pasep, String carteiraTrabalho, String crf) {
			super(id, nome, cpf, rg, endereco, pis);
			this.pis=pis;
			this.pasep=pasep;
			this.carteiraTrabalho=carteiraTrabalho;	 
			this.crf=crf;
		}
		
		public String getPis() {
			return this.pis;
		}
		
		public String getPasep() {
			return this.pasep;
		}
		
		public String getCarteiraTrabalho() {
			return this.carteiraTrabalho;
		}
		
		public void setPis(String pis) {
			this.pis = pis;
		}
		
		public void setPasep(String pasep) {
			this.pasep = pasep;
		}
		
		public void setCarteiraTrabalho(String carteiraTrabalho) {
			this.carteiraTrabalho = carteiraTrabalho;
		}
		
		public String getCrf() {
			return crf;
		}

		public void setCrf(String crf) {
			this.crf = crf;
		}
		
		@Override
		public String toString () {
			return super.toString() + ":" + getPis() + ":" + getPasep() + ";" + getCarteiraTrabalho() + ";" + getCrf() + "\n";
		}
}


