package br.edu.cassio.farmaciamvnfx.controller;

 
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.beans.Agenda;
import br.edu.cassio.farmaciamvnfx.beans.Paciente;
import br.edu.cassio.farmaciamvnfx.dao.AgendaDao;
import br.edu.cassio.farmaciamvnfx.dao.PacienteDao;
import br.edu.cassio.farmaciamvnfx.database.DatabaseMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerTelaAgenda implements Initializable {

    @FXML
    private TableView<Agenda> tableViewAgenda;

    @FXML
    private TableColumn<Agenda, Integer> tableColumnId;

    @FXML
    private TableColumn<Agenda, Paciente> tableColumnPaciente;

    @FXML
    private TableColumn<Agenda, LocalDate> tableColumnData;

    @FXML
    private TableColumn<Agenda, LocalTime> tableColumnHorario;

    @FXML
    private TextField textFieldId;

    @FXML
    private DatePicker datePickerData;

    @FXML
    private ComboBox<Integer> comboBoxHoras;

    @FXML
    private ComboBox<Integer> comboBoxMinutos;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonExcluirAgenda;

    @FXML
    private ComboBox<String> comboBoxPaciente;

    private ObservableList<Agenda> olAgenda;
    private Integer idAgendaSelecionado;
    private Agenda agendaSelecionado;

    private List<Integer> listHoras = new ArrayList<>();
    private List<Integer> listMinutos = new ArrayList<>();
    private List<String> listPacientes = new ArrayList<>();
    private ObservableList<Integer> olHoras;
    private ObservableList<Integer> olMinutos;
    private ObservableList<String> olPacientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregaComboBox();
        carregaComboBoxPaciente();
        popularTabelaAgenda();
        tableViewAgenda.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarAgenda(newValue));
    }

    public void carregaComboBox() {
        listHoras.add(1);
        listHoras.add(2);
        listHoras.add(3);
        listHoras.add(4);
        listHoras.add(5);
        listHoras.add(6);
        listHoras.add(7);
        listHoras.add(8);
        listHoras.add(9);
        listHoras.add(10);
        listHoras.add(11);
        listHoras.add(12);
        listHoras.add(13);
        listHoras.add(14);
        listHoras.add(15);
        listHoras.add(16);
        listHoras.add(17);
        listHoras.add(18);
        listHoras.add(19);
        listHoras.add(20);
        listHoras.add(21);
        listHoras.add(22);
        listHoras.add(23);
        listHoras.add(24);

        listMinutos.add(00);
        listMinutos.add(15);
        listMinutos.add(30);
        listMinutos.add(45);

        olHoras = FXCollections.observableArrayList(listHoras);
        olMinutos = FXCollections.observableArrayList(listMinutos);
        comboBoxHoras.setItems(olHoras);
        comboBoxMinutos.setItems(olMinutos);
    }
    
    private void carregaComboBoxPaciente(){
        DatabaseMySQL db = new DatabaseMySQL();
        Connection con = db.conectar();
        PacienteDao pdao = new PacienteDao();
        pdao.setConnection((Connection) con);
        List<String> lstNomes = pdao.listarNomePacientes();
        olPacientes = FXCollections.observableArrayList(lstNomes);
        comboBoxPaciente.setItems(olPacientes);
    }


    @FXML
    private void handlerSalvarConsulta(ActionEvent event) {
        try {
            String paciente = comboBoxPaciente.getValue();
            LocalDate data = datePickerData.getValue();
            String horario = comboBoxHoras.getValue() + " : " + comboBoxMinutos.getValue();
            Agenda agenda = new Agenda(data, horario, paciente);

            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            AgendaDao adao = new AgendaDao();
            adao.setConnection((Connection) con);
            adao.inserir(agenda);
            popularTabelaAgenda();
        } catch (Exception e) {
            System.out.println("Alguma coisa errada!");
        }
    }

    public void popularTabelaAgenda() {
        DatabaseMySQL db = new DatabaseMySQL();
        Connection con = db.conectar();
        AgendaDao adao = new AgendaDao();
        adao.setConnection((Connection) con);
        List<Agenda> lst = adao.listar();
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        olAgenda = FXCollections.observableArrayList(lst);
        tableViewAgenda.setItems(olAgenda);
    }

    @FXML
    private void handlerExcluirAgenda(ActionEvent event) {
        if (idAgendaSelecionado != null) {
            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            AgendaDao adao = new AgendaDao();
            adao.setConnection((Connection) con);
            adao.remover(agendaSelecionado);
            popularTabelaAgenda();
        }
    }

    private void selecionarAgenda(Agenda agenda) {
        idAgendaSelecionado = agenda.getId();
        agendaSelecionado = agenda;

        textFieldId.setText(String.valueOf(agenda.getId()));
        comboBoxPaciente.setValue(agenda.getPaciente());
        datePickerData.setValue(agenda.getData());
        //comboBoxHoras.setValue(agenda.get);

    }

}
