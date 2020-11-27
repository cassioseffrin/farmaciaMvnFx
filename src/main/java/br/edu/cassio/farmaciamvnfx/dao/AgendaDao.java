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

import br.edu.cassio.farmaciamvnfx.beans.Agenda;

public class AgendaDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Agenda agenda) {
        String sql = "INSERT INTO agenda (paciente, data_agenda, horario) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, agenda.getPaciente());
            stmt.setDate(2, Date.valueOf(agenda.getData()));
            stmt.setString(3, agenda.getHorario());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Agenda> listar() {
        String sql = "SELECT * FROM agenda";
        List<Agenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Agenda agenda = new Agenda(LocalDate.MAX, sql, sql);

                agenda.setId(resultado.getInt("id_agenda"));
                agenda.setPaciente(resultado.getString("paciente"));
                agenda.setData(resultado.getDate("data_agenda").toLocalDate());
                agenda.setHorario(resultado.getString("horario"));
                retorno.add(agenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
        public boolean remover(Agenda agenda) {
        String sql = "DELETE FROM agenda WHERE id_agenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, agenda.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
