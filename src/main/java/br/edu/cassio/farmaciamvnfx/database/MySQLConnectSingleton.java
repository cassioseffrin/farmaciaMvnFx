
package br.edu.cassio.farmaciamvnfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectSingleton {
    
    public Connection conn;
    private Statement statement;
    public static MySQLConnectSingleton db;
    private MySQLConnectSingleton() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "hospital";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "cassio";
        String password = "123";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static synchronized MySQLConnectSingleton getDbCon() {
        if ( db == null ) {
            db = new MySQLConnectSingleton();
        }
        return db;
 
    }
 
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
 
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
}
