package bdd.objetsdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import bdd.DAO;
import bdd.connexionJDBC.ConnectionBDD;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

public class Statistiques {
	
	/**
	 * Link to the database connection
	 */
	public static Connection connect = ConnectionBDD.getInstance();

	public static ArrayList<Object[]> getHistoriqueLocationsAbonne(int id) {
		ArrayList<Object[]> velosAbos = new ArrayList<Object[]>();
		
		try {
			// récupération des vélos
			ResultSet result = connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM louer WHERE idAbonne = " + id);
			while (result.next()) {
				Object[] infosAbo = new Object[3];
				infosAbo[0] = result.getInt(3);
				infosAbo[1] = result.getTimestamp(4);
				infosAbo[2] = result.getTimestamp(5);
				velosAbos.add(infosAbo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return velosAbos;

	}
}
