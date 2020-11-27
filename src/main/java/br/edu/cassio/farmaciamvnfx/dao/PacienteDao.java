package br.edu.cassio.farmaciamvnfx.dao;

 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.cassio.farmaciamvnfx.beans.Paciente;

public class PacienteDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome_paciente, rg_paciente, cpf_paciente, sexo_paciente, dataNascimento_paciente, telefone_paciente, gravidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getRg());
            stmt.setInt(3, paciente.getCpf());
            stmt.setString(4, paciente.getSexo());
            stmt.setDate(5, Date.valueOf(paciente.getDataNascimento()));
            stmt.setInt(6, paciente.getTelefone());
            stmt.setInt(7, paciente.getGravidade());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean remover(Paciente paciente) {
        String sql = "DELETE FROM paciente WHERE id_paciente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Paciente buscar(Paciente paciente) {
        String sql = "SELECT * FROM paciente WHERE id_paciente=?";
        Paciente retorno = new Paciente(sql, 0, 0, sql, LocalDate.MIN, 0, 0);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setRg(resultado.getInt("rg_paciente"));
                paciente.setCpf(resultado.getInt("cpf_paciente"));
                paciente.setSexo("sexo_paciente");
                paciente.setDataNascimento((resultado.getDate("dataNascimento_paciente").toLocalDate()));
                paciente.setTelefone(resultado.getInt("telefone_paciente"));
                paciente.setGravidade(resultado.getInt("gravidade"));
                retorno = paciente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Paciente> listar() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Paciente paciente = new Paciente(sql, 0, 0, sql, LocalDate.MIN, 0, 0);

                paciente.setId(resultado.getInt("id_paciente"));
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setRg(resultado.getInt("rg_paciente"));
                paciente.setCpf(resultado.getInt("cpf_paciente"));
                paciente.setSexo(resultado.getString("sexo_paciente"));
                paciente.setDataNascimento((resultado.getDate("dataNascimento_paciente").toLocalDate()));
                paciente.setTelefone(resultado.getInt("telefone_paciente"));
                paciente.setGravidade(resultado.getInt("gravidade"));
                retorno.add(paciente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean alterar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome_paciente=?, rg_paciente=?, cpf_paciente=?, sexo_paciente=?, "
                + "dataNascimento_paciente=?, telefone_paciente=?, gravidade=? WHERE id_paciente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getRg());
            stmt.setInt(3, paciente.getCpf());
            stmt.setString(4, paciente.getSexo());
            stmt.setDate(5, Date.valueOf(paciente.getDataNascimento()));
            stmt.setInt(6, paciente.getTelefone());
            stmt.setInt(7, paciente.getGravidade());
            //stmt.setInt(8, paciente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<String> listarNomePacientes() {
        String sql = "SELECT nome_paciente FROM paciente ORDER BY nome_paciente ASC";
        List<String> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Paciente paciente = new Paciente();
                paciente.setNome(resultado.getString("nome_paciente"));
                retorno.add(paciente.getNome());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
