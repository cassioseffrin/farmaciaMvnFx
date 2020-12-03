package br.edu.cassio.farmaciamvnfx.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.cassio.farmaciamvnfx.beans.Medico;

public class ListaMedicoFactory {

	public static List<Medico> listarMedicos() {
		Medico m1 = new Medico("Cassio", 12341234, 1234123412, "Masculino", LocalDate.now(), 499990342, "Neurologista");
		Medico m2 = new Medico("Fernanda", 12341234, 1234123412, "Feminino", LocalDate.now(), 499990342,
				"Clinico Geral");
		Medico m3 = new Medico("Maria", 12341234, 1234123412, "Feminino", LocalDate.now(), 499990342, "Ginecologista");
		Medico m4 = new Medico("Joaquim", 12341234, 1234123412, "Masculino", LocalDate.now(), 499990342, "Ortorino");
		List<Medico> lst = new ArrayList<>();
		lst.add(m1);
		lst.add(m2);
		lst.add(m3);
		lst.add(m4);
		return lst;
	}

}
