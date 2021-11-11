package br.unc.rener.farmaciafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unc.rener.farmaciafx.classes.Funcionario;
import br.unc.rener.farmaciafx.database.*;



public class FuncionarioDao {

	private Connection connection;
	public FuncionarioDao(){
		this.connection = MysqlSingleton.getConnection();
		
	}
	public boolean inserir(Funcionario funcionario) {
		String sql = "INSERT INTO farmaciafx.funcionario (nome, cpf, rg, endereco, pis, pasep, carteiratrabalho, crf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getRg());
			stmt.setString(4, funcionario.getEndereco());
			stmt.setString(5, funcionario.getPis());
			stmt.setString(6, funcionario.getPasep());
			stmt.setString(7, funcionario.getCarteiraTrabalho());
			stmt.setString(8, funcionario.getCrf());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}
	
	public List<Funcionario> listar() {
		String sql = "SELECT * FROM  funcionario";
		List<Funcionario> listaFuncionarios = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(resultado.getString("nome"));
				funcionario.setRg(resultado.getString("rg"));
				funcionario.setCpf(resultado.getString("cpf"));
				funcionario.setEndereco(resultado.getString("endereco"));
				funcionario.setPis(resultado.getString("pis"));
				funcionario.setPasep(resultado.getString("pasep"));
				funcionario.setCarteiraTrabalho(resultado.getString("carteira trabalho"));
				funcionario.setCrf(resultado.getString("crf"));
				listaFuncionarios.add(funcionario);
			}
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaFuncionarios;
	}

public boolean alterar(Funcionario funcionario) {
		String sql = "UPDATE funcionario SET nome=?, rg=?, cpf=?, endereco=?, pis=?, pasep=?, carteiratrabalho=?, crf=?  WHERE id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getRg());
			stmt.setString(3, funcionario.getCpf());
			stmt.setString(5, funcionario.getEndereco());
			stmt.setString(4, funcionario.getPis());
			stmt.setString(4, funcionario.getPasep());
			stmt.setString(4, funcionario.getCarteiraTrabalho());
			stmt.setString(4, funcionario.getCrf());
			stmt.setInt(6, funcionario.getId());
			stmt.execute();
			return true;
	} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
		return false;
		}
	}



	public boolean remover(Funcionario funcionario) {
	String sql = "DELETE FROM funcionario WHERE id=?";
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, funcionario.getId());
		stmt.execute();
		return true;
	} catch (SQLException ex) {
		Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
		return false;
	}	
}

	public Funcionario buscar(Integer id) {
		String sql = "SELECT * FROM funcionario WHERE id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Funcionario funcionario = new Funcionario();
			if (resultado.next()) {
				funcionario.setId(resultado.getInt("id"));
				funcionario.setNome(resultado.getString("nome"));
				funcionario.setRg(resultado.getString("rg"));
				funcionario.setCpf(resultado.getString("cpf"));
				funcionario.setEndereco(resultado.getString("endereco"));
				funcionario.setPis(resultado.getString("pis"));
				funcionario.setPasep(resultado.getString("pasep"));
				funcionario.setCarteiraTrabalho(resultado.getString("carteiratrabalho"));
				funcionario.setCrf(resultado.getString("crf"));
				return funcionario;
			}
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		return null;
		}
}
