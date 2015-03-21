package bdd.connexionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBDD {

	private static String url = "jdbc:postgresql://localhost:5432/velomiage";

	private static String user = "postgres";

	private static String passwd = "a";

	private static Connection connect;
	
	/**
	 * Returns database connection instance (and creates it if it doesn't exist)
	 * 	 * @return Database connection link
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}
}
