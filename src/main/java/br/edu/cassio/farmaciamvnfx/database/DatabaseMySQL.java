
package br.edu.cassio.farmaciamvnfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMySQL implements Database{

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null){
            return conectar();
        }else{
            return connection;
        }
    }
    
    public static Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "cassio","123");
            return connection;
        }catch(SQLException | ClassNotFoundException e){
            return null;
        }
    }
    
    
    
    
    
    @Override
    public void desconectar(Connection conn) {
        try{
            connection.close();
        }catch(SQLException e){
        }
    }
    
    
    
}
