package br.edu.cassio.farmaciamvnfx.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlSingleton  {
	
	private static Connection singletonConnection;

	public static Connection getConnection() {
		if (singletonConnection == null) {
			return conectar();
		} else {
			return singletonConnection;
		}
	}

	public static Connection conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			singletonConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia", "root", "123123123");
			return singletonConnection;
		} catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}
 
	public void desconectar(Connection conn) {
		try {
			singletonConnection.close();
		} catch (SQLException e) {
		}
	}

}