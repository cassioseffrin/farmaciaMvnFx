package br.edu.cassio.farmaciamvnfx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.adapter.ListaPacienteFactory;
import br.edu.cassio.farmaciamvnfx.beans.Medico;
import br.edu.cassio.farmaciamvnfx.dao.MedicoDao;
import br.edu.cassio.farmaciamvnfx.database.DatabaseMySQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerRelatorioMedico implements Initializable {

	@FXML
	private Button btnSalvar;

	@FXML
	private void handleRelatorio(ActionEvent event) throws JRException {
		System.out.println("ok");

		DatabaseMySQL db = new DatabaseMySQL();
		Connection conexao = db.conectar();
 
		MedicoDao pdao = new MedicoDao();
		pdao.setConnection((Connection) conexao);
		List<Medico> lst = pdao.listar();

		URL url = getClass().getResource("/relatorios/medico.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("Empresa", new String("Lista de Medicos"));
		
		JRBeanCollectionDataSource dsMedicos = new JRBeanCollectionDataSource(lst);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapa, dsMedicos);
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		jasperViewer.setVisible(true);
	}
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
