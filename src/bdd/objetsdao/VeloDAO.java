package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import bdd.DAO;
import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

public class VeloDAO extends DAO<Velo> {

	@Override
	public Velo find(int id) {
		Velo velo = new Velo();
		
		try {
			// récupération de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM velo WHERE idvelo = " + id);
			if (result.first()) {
				// création de la station avec les données de la base
				velo = new Velo(
						id, 
						result.getInt(1));		
			}
			
			// recherche de locations précédentes
			result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM louer WHERE idvelo = " + id);
			if (result.first()) {
				// création de la station avec les données de la base
				velo = new Velo(
						id, 
						result.getInt(1));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return velo;
	}
	
	public Velo depositVelo(Velo obj, Timestamp dateFinLocation) {
		try {	
			/*ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT idAbonne FROM louer WHERE idvelo = " + obj.getId());
			if (result.first()) {
				Abonne a = new AbonneDAO().find(result.getInt(1));
				//a.setVe
			}*/

			this.connect	
            .createStatement(
            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE
             ).executeUpdate(
            	"UPDATE louer SET dateFin = '" + dateFinLocation + "'"+
            	" WHERE idVelo = " + obj.getId() + " AND dateFin is null "
             );
			
			obj.setDateDerniereLocation(dateFinLocation);

			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}

	@Override
	public Velo create(Velo obj) {
		try {
			// insertion de l'objet
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO velo (etat) VALUES (?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getEtat());
			prepare.executeUpdate();
			// récupération des valeurs de l'insert
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(1));			
		}
		catch (SQLException e) {
				e.printStackTrace();
			}
		return obj;
	}

	@Override
	public Velo update(Velo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Velo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Velo> getInstances() {
		ArrayList<Velo> velos = new ArrayList<Velo>();
		try {
	
			// récupération de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM velo");
			while (result.next()) {
				Velo v = this.find(result.getInt(1));
				velos.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return velos;
	}

}
