
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

import br.edu.cassio.farmaciamvnfx.beans.Enfermeiro;

public class EnfermeiroDao {
    
    
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public boolean inserir(Enfermeiro enfermeiro){
        String sql = "INSERT INTO enfermeiro (nome_enfermeiro, rg_enfermeiro, cpf_enfermeiro, sexo_enfermeiro, "
                + "dataNascimento_enfermeiro, telefone_enfermeiro, dataAdmissao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, enfermeiro.getNome());
            stmt.setInt(2, enfermeiro.getRg());
            stmt.setInt(3, enfermeiro.getCpf());
            stmt.setString(4, enfermeiro.getSexo());
            stmt.setDate(5, Date.valueOf(enfermeiro.getDataNascimento()));
            stmt.setInt(6, enfermeiro.getTelefone());
            stmt.setDate(7, Date.valueOf(enfermeiro.getDataAdmissao()));
            stmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean alterar(Enfermeiro enfermeiro) {
        String sql = "UPDATE enfermeiro SET id_enfermeiro=?, nome_enfermeiro=?, rg_enfermeiro=?, cpf_enfermeiro=?, sexo_enfermeiro=?, dataNascimento_enfermeiro=?, "
                + "telefone_enfermeiro=?, dataAdmissao=? WHERE id_enfermeiro=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, enfermeiro.getId());
            stmt.setString(2, enfermeiro.getNome());
            stmt.setInt(3, enfermeiro.getRg());
            stmt.setInt(4, enfermeiro.getCpf());
            stmt.setString(5, enfermeiro.getSexo());
            stmt.setDate(6, Date.valueOf(enfermeiro.getDataNascimento()));
            stmt.setInt(7, enfermeiro.getTelefone());
            stmt.setDate(8, Date.valueOf(enfermeiro.getDataAdmissao()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Enfermeiro> listar() {
        String sql = "SELECT * FROM enfermeiro";
        List<Enfermeiro> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Enfermeiro enfermeiro = new Enfermeiro(sql, 0, 0, sql, LocalDate.MIN, 0, LocalDate.MIN);
                enfermeiro.setId(resultado.getInt("id_enfermeiro"));
                enfermeiro.setNome(resultado.getString("nome_enfermeiro"));
                enfermeiro.setRg(resultado.getInt("rg_enfermeiro"));
                enfermeiro.setCpf(resultado.getInt("cpf_enfermeiro"));
                enfermeiro.setSexo(resultado.getString("sexo_enfermeiro"));
                enfermeiro.setDataNascimento((resultado.getDate("dataNascimento_enfermeiro").toLocalDate()));
                enfermeiro.setTelefone(resultado.getInt("telefone_enfermeiro"));
                enfermeiro.setDataAdmissao((resultado.getDate("dataAdmissao").toLocalDate()));
                retorno.add(enfermeiro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
        public boolean remover(Enfermeiro enfermeiro) {
        String sql = "DELETE FROM enfermeiro WHERE id_enfermeiro=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, enfermeiro.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Enfermeiro buscar(Enfermeiro enfermeiro) {
        String sql = "SELECT * FROM enfermeiro WHERE id_enfermeiro=?";
        Enfermeiro retorno = new Enfermeiro(sql, 0, 0, sql, LocalDate.MIN, 0, LocalDate.MIN);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, enfermeiro.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                enfermeiro.setId(resultado.getInt("id_enfermeiro"));
                enfermeiro.setNome(resultado.getString("nome_enfermeiro"));
                enfermeiro.setRg(resultado.getInt("rg_enfermeiro"));
                enfermeiro.setCpf(resultado.getInt("cpf_enfermeiro"));
                enfermeiro.setSexo("sexo_enfermeiro");
                enfermeiro.setDataNascimento((resultado.getDate("dataNascimento_enfermeiro").toLocalDate()));
                enfermeiro.setTelefone(resultado.getInt("telefone_enfermeiro"));
                enfermeiro.setDataAdmissao((resultado.getDate("dataAdmissao").toLocalDate()));
                retorno = enfermeiro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
