
package br.edu.cassio.farmaciamvnfx.dao;

 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.cassio.farmaciamvnfx.beans.Acompanhante;
import br.edu.cassio.farmaciamvnfx.beans.Paciente;
 

public class AcompanhanteDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Acompanhante acompanhante){
        String sql = "INSERT INTO acompanhante (id_acompanhante, nome_acompanhante, rg_acompanhante, cpf_acompanhante, "
                + "sexo_acompanhante, dataNascimento_acompanhante, telefone_acompanhante, id_paciente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, acompanhante.getId());
            stmt.setString(2, acompanhante.getNome());
            stmt.setInt(3, acompanhante.getRg());
            stmt.setInt(4, acompanhante.getCpf());
            stmt.setString(5, acompanhante.getSexo());
            stmt.setDate(6,  new Date(acompanhante.getDataNascimento().getDayOfMonth(), acompanhante.getDataNascimento().getMonthValue(), acompanhante.getDataNascimento().getYear()));
            stmt.setInt(7, acompanhante.getTelefone());
            stmt.setObject(8, (Paciente)acompanhante.getPaciente());
            return true;
        }catch(Exception e){
            return false;
        }
        
    }
    
        public boolean alterar(Acompanhante acompanhante) {
        String sql = "UPDATE acompanhante SET id_acompanhante=?, nome_acompanhante=?, rg_acompanhante=?, cpf_acompanhante=?, sexo_acompanhante=?, dataNascimento_acompanhante=?, "
                + "telefone_acompanhante=?, id_paciente=? WHERE id_acompanhante=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, acompanhante.getId());
            stmt.setString(2, acompanhante.getNome());
            stmt.setInt(3, acompanhante.getRg());
            stmt.setInt(4, acompanhante.getCpf());
            stmt.setString(5, acompanhante.getSexo());
            stmt.setDate(6, Date.valueOf(acompanhante.getDataNascimento()));
            stmt.setInt(7, acompanhante.getTelefone());
            stmt.setObject(8, (Paciente)acompanhante.getPaciente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AcompanhanteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
