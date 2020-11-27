package br.edu.cassio.farmaciamvnfx.dao;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.cassio.farmaciamvnfx.beans.Enfermeiro;
import br.edu.cassio.farmaciamvnfx.beans.Medico;
import br.edu.cassio.farmaciamvnfx.beans.Paciente;
import br.edu.cassio.farmaciamvnfx.beans.Servico;

public class ServicoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Servico servico) {
        String sql = "INSERT INTO servico (id_servico, descricao_servico, id_medico, id_paciente, id_enfermeiro) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            stmt.setString(2, servico.getDescricao());
            stmt.setObject(3, (Medico) servico.getMedico());
            stmt.setObject(4, (Paciente) servico.getPaciente());
            stmt.setObject(5, (Enfermeiro) servico.getEnfermeiro());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean alterar(Servico servico) {
        String sql = "UPDATE servico SET id_servico=?, descricao_servico=?, id_medico=?, id_paciente=?, id_enfermeiro=?, "
                + "WHERE id_servico=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            stmt.setString(2, servico.getDescricao());
            stmt.setObject(3, (Medico) servico.getMedico());
            stmt.setObject(4, (Paciente) servico.getPaciente());
            stmt.setObject(5, (Enfermeiro) servico.getEnfermeiro());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Servico> listar() {
        String sql = "SELECT * FROM servico";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico(0, sql, null, null, null);
                servico.setId(resultado.getInt("id_servico"));
                servico.setDescricao(resultado.getString("descrcao_servico"));
                servico.setMedico((Medico) resultado.getObject("id_medico"));
                servico.setPaciente((Paciente) resultado.getObject("id_paciente"));
                servico.setEnfermeiro((Enfermeiro) resultado.getObject("id_enfermeiro"));
                retorno.add(servico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean remover(Servico servico) {
        String sql = "DELETE FROM servico WHERE id_servico=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Servico buscar(Servico servico) {
        String sql = "SELECT * FROM servico WHERE id_servico=?";
        Servico retorno = new Servico(0, sql, null, null, null);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                servico.setId(resultado.getInt("id_servico"));
                servico.setDescricao(resultado.getString("nome_medico"));
                servico.setMedico((Medico) resultado.getObject("id_medico"));
                servico.setPaciente((Paciente) resultado.getObject("id_paciente"));
                servico.setEnfermeiro((Enfermeiro) resultado.getObject("id_enfermeiro"));
                retorno = servico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
