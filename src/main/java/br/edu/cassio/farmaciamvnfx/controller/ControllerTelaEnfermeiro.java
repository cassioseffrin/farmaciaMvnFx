package br.edu.cassio.farmaciamvnfx.controller;

 
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.beans.Enfermeiro;
import br.edu.cassio.farmaciamvnfx.dao.EnfermeiroDao;
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

public class ControllerTelaEnfermeiro implements Initializable {

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
    private DatePicker datePickerAdmissao;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Enfermeiro> tableViewEnfermeiro;

    @FXML
    private TableColumn<Enfermeiro, Integer> tableColumnId;

    @FXML
    private TableColumn<Enfermeiro, String> tableColumnNome;

    @FXML
    private TableColumn<Enfermeiro, Integer> tableColumnRg;

    @FXML
    private TableColumn<Enfermeiro, Integer> tableColumnCpf;

    @FXML
    private TableColumn<Enfermeiro, String> tableColumnSexo;

    @FXML
    private TableColumn<Enfermeiro, LocalDate> tableColumnNascimento;

    @FXML
    private TableColumn<Enfermeiro, Integer> tableColumnTelefone;

    @FXML
    private TableColumn<Enfermeiro, LocalDate> tableColumnAdmissao;

    @FXML
    private Button buttonExcluir;

    private ObservableList<Enfermeiro> olEnfermeiro;
    private Integer idEnfermeiroSelecionado;
    private Enfermeiro enfermeiroSelecionado;
    @FXML
    private Button buttonNovo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popularTabelaEnfermeiro();
        tableViewEnfermeiro.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarEnfermeiro(newValue));
    }

    @FXML
    private void handlerSalvarEnfermeiro(ActionEvent event) {
        try {
            String nome = textFieldNome.getText();
            int rg = Integer.parseInt(textFieldRg.getText());
            int cpf = Integer.parseInt(textFieldCpf.getText());
            String sexo = textFieldSexo.getText();
            LocalDate nascimento = datePickerNascimento.getValue();
            int telefone = Integer.parseInt(textFieldTelefone.getText());
            LocalDate admissao = datePickerAdmissao.getValue();
            Enfermeiro enfermeiro = new Enfermeiro(nome, rg, cpf, sexo, nascimento, telefone, admissao);

            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            EnfermeiroDao edao = new EnfermeiroDao();
            edao.setConnection((Connection) con);
            edao.inserir(enfermeiro);
            popularTabelaEnfermeiro();
        } catch (Exception e) {
            System.out.println("Alguma coisa errada!");
        }
    }

    public void popularTabelaEnfermeiro() {
        DatabaseMySQL db = new DatabaseMySQL();
        Connection con = db.conectar();
        EnfermeiroDao edao = new EnfermeiroDao();
        edao.setConnection((Connection) con);
        List<Enfermeiro> lst = edao.listar();
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableColumnNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnAdmissao.setCellValueFactory(new PropertyValueFactory<>("dataAdmissao"));

        olEnfermeiro = FXCollections.observableArrayList(lst);
        tableViewEnfermeiro.setItems(olEnfermeiro);
    }

    private void selecionarEnfermeiro(Enfermeiro enfermeiro) {
        idEnfermeiroSelecionado = enfermeiro.getId();
        enfermeiroSelecionado = enfermeiro;

        textFieldId.setText(String.valueOf(enfermeiro.getId()));
        textFieldNome.setText(enfermeiro.getNome());
        textFieldRg.setText(String.valueOf(enfermeiro.getRg()));
        textFieldCpf.setText(String.valueOf(enfermeiro.getCpf()));
        textFieldSexo.setText(enfermeiro.getSexo());
        datePickerNascimento.setValue(enfermeiro.getDataNascimento());
        textFieldTelefone.setText(String.valueOf(enfermeiro.getTelefone()));
        datePickerAdmissao.setValue(enfermeiro.getDataAdmissao());
    }

    @FXML
    private void handlerExcluirEnfermeiro(ActionEvent event) {
        if (idEnfermeiroSelecionado != null) {
            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();
            EnfermeiroDao edao = new EnfermeiroDao();
            edao.setConnection((Connection) con);
            edao.remover(enfermeiroSelecionado);
            popularTabelaEnfermeiro();
        }

    }

    @FXML
    private void handlerNovoEnfermeiro(ActionEvent event) {
        textFieldId.setText(null);
        textFieldNome.setText(null);
        textFieldRg.setText(null);
        textFieldCpf.setText(null);
        textFieldSexo.setText(null);
        datePickerNascimento.setValue(null);
        textFieldTelefone.setText(null);
        datePickerAdmissao.setValue(null);

    }
}
