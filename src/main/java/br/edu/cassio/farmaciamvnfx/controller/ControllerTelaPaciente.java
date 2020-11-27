package br.edu.cassio.farmaciamvnfx.controller;

 
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.beans.Paciente;
import br.edu.cassio.farmaciamvnfx.dao.PacienteDao;
import br.edu.cassio.farmaciamvnfx.database.DatabaseMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author User
 */
public class ControllerTelaPaciente implements Initializable {

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldRg;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textFieldSexo;

    @FXML
    private DatePicker datePickerNascimento;

    @FXML
    private TextField textFieldTelefone;

    @FXML
    private TextField textFieldGravidade;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Paciente> tableViewPaciente;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnId;

    @FXML
    private TableColumn<Paciente, String> tableColumnNome;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnRg;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnCpf;

    @FXML
    private TableColumn<Paciente, String> tableColumnSexo;

    @FXML
    private TableColumn<Paciente, LocalDate> tableColumnNascimento;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnTelefone;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnGravidade;
    
    @FXML
    private Button buttonExcluir;

    private ObservableList<Paciente> olPaciente;
    private Integer idPacienteSelecionado;
    private Paciente pacienteSelecionado;
    @FXML
    private Button buttonNovo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popularTabelaPaciente();
        tableViewPaciente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarPaciente(newValue));
    }

    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
            String nome = textFieldNome.getText();
            int rg = Integer.valueOf(textFieldRg.getText());
            int cpf = Integer.valueOf(textFieldCpf.getText());
            String sexo = textFieldSexo.getText();
            LocalDate nascimento = datePickerNascimento.getValue();
            int telefone = Integer.valueOf(textFieldTelefone.getText());
            int gravidade = Integer.valueOf(textFieldGravidade.getText());
            Paciente paciente = new Paciente(nome, rg, cpf, sexo, nascimento, telefone, gravidade);

            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            PacienteDao pdao = new PacienteDao();
            pdao.setConnection((Connection) con);
            pdao.inserir(paciente);
            popularTabelaPaciente();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }

    public void popularTabelaPaciente() {
        DatabaseMySQL db = new DatabaseMySQL();
        Connection con = db.conectar();
        PacienteDao pdao = new PacienteDao();
        pdao.setConnection(( Connection) con);
        List<Paciente> lst = pdao.listar();
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableColumnNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnGravidade.setCellValueFactory(new PropertyValueFactory<>("gravidade"));

        olPaciente = FXCollections.observableArrayList(lst);
        tableViewPaciente.setItems(olPaciente);
    }

    private void selecionarPaciente(Paciente paciente) {
        idPacienteSelecionado = paciente.getId();
        pacienteSelecionado = paciente;

        textFieldId.setText(String.valueOf(paciente.getId()));
        textFieldNome.setText(paciente.getNome());
        textFieldRg.setText(String.valueOf(paciente.getRg()));
        textFieldCpf.setText(String.valueOf(paciente.getCpf()));
        textFieldSexo.setText(paciente.getSexo());
        datePickerNascimento.setValue(paciente.getDataNascimento());
        textFieldTelefone.setText(String.valueOf(paciente.getTelefone()));
        textFieldGravidade.setText(String.valueOf(paciente.getGravidade()));
    }

    @FXML
    private void handlerExcluirPaciente(ActionEvent event) {
        if (idPacienteSelecionado != null){
            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            PacienteDao pdao = new PacienteDao();
            pdao.setConnection((Connection) con);
            pdao.remover(pacienteSelecionado);
            popularTabelaPaciente();
        }
        
    }

    @FXML
    private void handlerNovoPaciente(ActionEvent event) {
        textFieldId.setText(null);
        textFieldNome.setText(null);
        textFieldRg.setText(null);
        textFieldCpf.setText(null);
        textFieldSexo.setText(null);
        datePickerNascimento.setValue(null);
        textFieldTelefone.setText(null);
        textFieldGravidade.setText(null);
    }

}
