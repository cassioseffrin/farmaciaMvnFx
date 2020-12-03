package br.edu.cassio.farmaciamvnfx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.adapter.ListaPacienteFactory;
import br.edu.cassio.farmaciamvnfx.beans.Paciente;
import br.edu.cassio.farmaciamvnfx.dao.PacienteDao;
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

public class ControllerRelatorio implements Initializable {

	@FXML
	private Button btnSalvar;

	@FXML
	private void handleRelatorio(ActionEvent event) throws JRException {
		System.out.println("ok");

		DatabaseMySQL db = new DatabaseMySQL();
		Connection conexao = db.conectar();
//
//		PacienteDao pdao = new PacienteDao();
//		pdao.setConnection((Connection) conexao);
//		List<Paciente> lst = pdao.listar();
		

		URL url = getClass().getResource("/relatorios/p4.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

//		Map<String, Object> mapa = new HashMap<String, Object>();
//		mapa.put("lstPacientes", lst);

		JRBeanCollectionDataSource wrapBean = new JRBeanCollectionDataSource(ListaPacienteFactory.listaPacientes());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, wrapBean);
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

//		   JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);
		jasperViewer.setVisible(true);

	}

//	para salvar direto no disco
	// long inicioContagem = System.currentTimeMillis();
//	
//	//Compilacao no formato jasper para o jrprint
//	JasperFillManager.fillReportToFile("/relatorios/relatorioTeste.jasper", null, new JREmptyDataSource(1));
//	System.err.println("Tempo de compilacao jasper -> jrprint: " + (System.currentTimeMillis() - inicioContagem));
//	
//	//Reinicia o contador
//	inicioContagem = System.currentTimeMillis();
//	
//	//Geracao do PDF
//	JasperExportManager.exportReportToPdfFile("/relatorios/relatorioTeste.jrprint");
//	System.err.println("Tempo de geracao do PDF: " + (System.currentTimeMillis() - inicioContagem));

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
