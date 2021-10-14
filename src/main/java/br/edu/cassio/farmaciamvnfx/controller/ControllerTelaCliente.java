package br.edu.cassio.farmaciamvnfx.controller;

 
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.cassio.farmaciamvnfx.beans.Cliente;
import br.edu.cassio.farmaciamvnfx.dao.ClienteDao;
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
 * @author Cassio
 */
public class ControllerTelaCliente implements Initializable {

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
    private Button buttonSalvar;

    @FXML
    private TableView<Cliente> tableViewCliente;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnId;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnRg;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnCpf;

    @FXML
    private TableColumn<Cliente, String> tableColumnSexo;

    @FXML
    private TableColumn<Cliente, LocalDate> tableColumnNascimento;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnTelefone;

    @FXML
    private Button buttonExcluir;

    private ObservableList<Cliente> olCliente;
    private Integer idClienteSelecionado;
    private Cliente pacienteSelecionado;
    @FXML
    private Button buttonNovo;
    
    
    private ClienteDao clienteDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	clienteDao = new  ClienteDao();
        popularTabelaCliente();
        tableViewCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarCliente(newValue));
    }

    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
            int id = Integer.valueOf(textFieldId.getText());
            String nome = textFieldNome.getText();
            int rg = Integer.valueOf(textFieldRg.getText());
            int cpf = Integer.valueOf(textFieldCpf.getText());
            String sexo = textFieldSexo.getText();
            LocalDate nascimento = datePickerNascimento.getValue();
            String telefone =  textFieldTelefone.getText();
            
           
           
            Cliente cliente = new Cliente(id, nome, rg, cpf, sexo, "End", telefone,  nascimento);
	 
 
            if ( id != 0) {
            	clienteDao.alterar(cliente);
            }else {
            	
            	clienteDao.inserir(cliente);
            }
            limpar();
            popularTabelaCliente();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }

    public void popularTabelaCliente() {
 
  
        List<Cliente> lst = clienteDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableColumnNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
 

        olCliente = FXCollections.observableArrayList(lst);
        tableViewCliente.setItems(olCliente);
    }

    private void selecionarCliente(Cliente cliente) {
        idClienteSelecionado = cliente.getId();
        pacienteSelecionado = cliente;

        textFieldId.setText(String.valueOf(cliente.getId()));
        textFieldNome.setText(cliente.getNome());
        textFieldRg.setText(String.valueOf(cliente.getRg()));
        textFieldCpf.setText(String.valueOf(cliente.getCpf()));
        textFieldSexo.setText(cliente.getSexo());
        datePickerNascimento.setValue(cliente.getDataNascimento());
        textFieldTelefone.setText(String.valueOf(cliente.getTelefone()));
 
    }

    @FXML
    private void handlerExcluirCliente(ActionEvent event) {
//        if (idClienteSelecionado != null){
//            DatabaseMySQL db = new DatabaseMySQL();
//            Connection con = db.conectar();
//            ClienteDao pdao = new ClienteDao();
//            pdao.setConnection((Connection) con);
//            pdao.remover(pacienteSelecionado);
//            popularTabelaCliente();
//        }
        
    }

    @FXML
    private void handlerNovoCliente(ActionEvent event) {
        limpar();
 
    }

	private void limpar() {
		textFieldId.setText(null);
        textFieldNome.setText(null);
        textFieldRg.setText(null);
        textFieldCpf.setText(null);
        textFieldSexo.setText(null);
        datePickerNascimento.setValue(null);
        textFieldTelefone.setText(null);
	}

}
