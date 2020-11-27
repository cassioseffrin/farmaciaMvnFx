package br.edu.cassio.farmaciamvnfx.controller;

 
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.beans.Medico;
import br.edu.cassio.farmaciamvnfx.dao.MedicoDao;
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

public class ControllerTelaMedico implements Initializable {

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
    private TextField textFieldEspecialidade;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Medico> tableViewMedico;

    @FXML
    private TableColumn<Medico, Integer> tableColumnId;

    @FXML
    private TableColumn<Medico, String> tableColumnNome;

    @FXML
    private TableColumn<Medico, Integer> tableColumnRg;

    @FXML
    private TableColumn<Medico, Integer> tableColumnCpf;

    @FXML
    private TableColumn<Medico, String> tableColumnSexo;

    @FXML
    private TableColumn<Medico, LocalDate> tableColumnNascimento;

    @FXML
    private TableColumn<Medico, Integer> tableColumnTelefone;

    @FXML
    private TableColumn<Medico, String> tableColumnEspecialidade;
       
    @FXML
    private Button buttonExcluir;

    private ObservableList<Medico> olMedico;
    private Integer idMedicoSelecionado;
    private Medico medicoSelecionado;
    @FXML
    private Button buttonNovo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popularTabelaMedico();
        tableViewMedico.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarMedico(newValue));
    }

    @FXML
    private void handlerSalvarMedico(ActionEvent event) {
        try {
            String nome = textFieldNome.getText();
            int rg = Integer.parseInt(textFieldRg.getText());
            int cpf = Integer.parseInt(textFieldCpf.getText());
            String sexo = textFieldSexo.getText();
            LocalDate nascimento = datePickerNascimento.getValue();
            int telefone = Integer.parseInt(textFieldTelefone.getText());
            String especialidade = textFieldEspecialidade.getText();
            Medico medico = new Medico(nome, rg, cpf, sexo, nascimento, telefone, especialidade);

            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            MedicoDao mdao = new MedicoDao();
            mdao.setConnection((Connection) con);
            mdao.inserir(medico);
            popularTabelaMedico();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }

    public void popularTabelaMedico() {
        DatabaseMySQL db = new DatabaseMySQL();
        Connection con = db.conectar();
        MedicoDao mdao = new MedicoDao();
        mdao.setConnection((Connection) con);
        List<Medico> list = mdao.listar();
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableColumnNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        olMedico = FXCollections.observableArrayList(list);
        tableViewMedico.setItems(olMedico);
    }

    private void selecionarMedico(Medico medico) {
        idMedicoSelecionado = medico.getId();
        medicoSelecionado = medico;

        textFieldId.setText(String.valueOf(medico.getId()));
        textFieldNome.setText(medico.getNome());
        textFieldRg.setText(String.valueOf(medico.getRg()));
        textFieldCpf.setText(String.valueOf(medico.getCpf()));
        textFieldSexo.setText(medico.getSexo());
        datePickerNascimento.setValue(medico.getDataNascimento());
        textFieldTelefone.setText(String.valueOf(medico.getTelefone()));
        textFieldEspecialidade.setText(medico.getEspecialidade());
    }

    @FXML
    private void excluirMedico(ActionEvent event) {
        if (idMedicoSelecionado != null){
            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            MedicoDao mdao = new MedicoDao();
            mdao.setConnection((Connection) con);
            mdao.remover(medicoSelecionado);
            popularTabelaMedico();
        }        
    }

    @FXML
    private void handlerNovoMedico(ActionEvent event) {
        textFieldId.setText(null);
        textFieldNome.setText(null);
        textFieldRg.setText(null);
        textFieldCpf.setText(null);
        textFieldSexo.setText(null);
        datePickerNascimento.setValue(null);
        textFieldTelefone.setText(null);
        textFieldEspecialidade.setText(null);
    }

}
