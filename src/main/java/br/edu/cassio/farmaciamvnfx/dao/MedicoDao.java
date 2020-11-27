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

import br.edu.cassio.farmaciamvnfx.beans.Medico;

public class MedicoDao {
    

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Medico medico) {
        String sql = "INSERT INTO medico (nome_medico, rg_medico, cpf_medico, sexo_medico, dataNascimento_medico, telefone_medico, especialidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setInt(2, medico.getRg());
            stmt.setInt(3, medico.getCpf());
            stmt.setString(4, medico.getSexo());
            stmt.setDate(5, Date.valueOf(medico.getDataNascimento()));
            stmt.setInt(6, medico.getTelefone());
            stmt.setString(7, medico.getEspecialidade());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MedicoDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterar(Medico medico) {
        String sql = "UPDATE medico SET id_medico=?, nome_medico=?, rg_medico=?, cpf_medico=?, sexo_medico=?, dataNascimento_medico=?, "
                + "telefone_medico=?, especialidade=? WHERE id_medico=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getId());
            stmt.setString(2, medico.getNome());
            stmt.setInt(3, medico.getRg());
            stmt.setInt(4, medico.getCpf());
            stmt.setString(5, medico.getSexo());
            stmt.setDate(6, Date.valueOf(medico.getDataNascimento()));
            stmt.setInt(7, medico.getTelefone());
            stmt.setString(8, medico.getEspecialidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Medico> listar() {
        String sql = "SELECT * FROM medico";
        List<Medico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Medico medico = new Medico(sql, 0, 0, sql, LocalDate.MIN, 0, sql);
                
                medico.setId(resultado.getInt("id_medico"));
                medico.setNome(resultado.getString("nome_medico"));
                medico.setRg(resultado.getInt("rg_medico"));
                medico.setCpf(resultado.getInt("cpf_medico"));
                medico.setSexo(resultado.getString("sexo_medico"));
                medico.setDataNascimento((resultado.getDate("dataNascimento_medico")).toLocalDate());
                medico.setTelefone(resultado.getInt("telefone_medico"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                retorno.add(medico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean remover(Medico medico) {
        String sql = "DELETE FROM medico WHERE id_medico=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Medico buscar(Medico medico) {
        String sql = "SELECT * FROM medico WHERE id_medico=?";
        Medico retorno = new Medico(sql, 0, 0, sql, LocalDate.MIN, 0, sql);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                medico.setId(resultado.getInt("id_medico"));
                medico.setNome(resultado.getString("nome_medico"));
                medico.setRg(resultado.getInt("rg_medico"));
                medico.setCpf(resultado.getInt("cpf_medico"));
                medico.setSexo("sexo_medico");
                medico.setDataNascimento((resultado.getDate("dataNascimento_medico").toLocalDate()));
                medico.setTelefone(resultado.getInt("telefone_medico"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                retorno = medico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
