package br.edu.cassio.farmaciamvnfx.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerRelatorio implements Initializable {

	@FXML
	private Button btnSalvar;

	@FXML
	private void handleRelatorioJSONURL(ActionEvent event) throws JRException {
		System.out.println("ok");

		URL url = getClass().getResource("/relatorios/alunos.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
 
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

		jasperViewer.setVisible(true);

	}

	
	
 
	
	
	@FXML
	private void handleRelatorio() throws JRException {

		String objetoJsonFormatoString = "[{\"nome\":\"Nome\", \"valor\":\"Cassio\"},"
				+ "{\"nome\":\"Sexo\", \"valor\": \"Masculino\"}," + "{\"nome\":\"CPF\", \"valor\": \"02443342930\"}"
				+ "]";
		 
		URL url = getClass().getResource("/relatorios/jsonteste.jasper");
		JasperReport report = (JasperReport) JRLoader.loadObject(url);

		// Converta a string json em uma matriz de bytes.
		ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(objetoJsonFormatoString.getBytes());

		// Cria a fonte de json dados
		JsonDataSource ds = new JsonDataSource(jsonDataStream);
		Map listaParametros = new HashMap();
		listaParametros.put("titulo", "Teste de relatorio com JSON");
		listaParametros.put("subTitulo", "valor do parametro subTitulo");
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, listaParametros, ds);

		// Abre o Jasper Viewer
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		jasperViewer.setVisible(true);
	}

	@FXML
	private void handleRelatorioDB(ActionEvent event) throws JRException {
//		System.out.println("ok");
//
//		DatabaseMySQL db = new DatabaseMySQL();
//		Connection conexao = db.conectar();
//
//		PacienteDao pdao = new PacienteDao();
//		pdao.setConnection((Connection) conexao);
//		List<Paciente> lst = pdao.listar();
//
//		URL url = getClass().getResource("/relatorios/paciente.jasper");
//
//		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
//
//		JRBeanCollectionDataSource wrapBean = new JRBeanCollectionDataSource(lst);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, wrapBean);
//		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//
//		jasperViewer.setVisible(true);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
