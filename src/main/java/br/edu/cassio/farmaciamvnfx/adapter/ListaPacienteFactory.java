package br.edu.cassio.farmaciamvnfx.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.edu.cassio.farmaciamvnfx.beans.Paciente;

public class ListaPacienteFactory {

	public static Collection<Paciente> listaPacientes() {

		Paciente p1 = new Paciente("Cassio1", 23413123, 234234, "masculino", LocalDate.now(), 99990093, 1);
		Paciente p2 = new Paciente("Cassio2", 23413123, 234234, "masculino", LocalDate.now(), 99990093, 1);
		Paciente p3 = new Paciente("Cassio3", 23413123, 234234, "masculino", LocalDate.now(), 99990093, 1);
		Paciente p4 = new Paciente("Cassio4", 23413123, 234234, "masculino", LocalDate.now(), 99990093, 1);
		List<Paciente> lst = new ArrayList<>();
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);
		lst.add(p4);
		return lst;

	}

}
