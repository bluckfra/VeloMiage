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
						result.getInt(2));		
			}
			
			if (velo.getEtat() == 1) {
				// recherche de locations en cours
				result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM louer WHERE idvelo = " + id 
						+ " AND dateFin is null ");
				if (result.first()) {
					// le vélo courant est en location, il possède un abonné
					velo.setAbonneCourant(result.getInt(2));
				}
			}
			
			if (velo.getEtat() == 2) {
				// recherche de stations liées
				result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM posseder WHERE idvelo = " + id 
						+ " AND dateRetrait is null ");
				if (result.first()) {
					// le vélo courant est dans une station
					velo.setStationCourante(result.getInt(2));
				}	
				
				// recherche de locations précédentes
				result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM louer WHERE idvelo = " + id 
						+ " ORDER BY idLouer DESC LIMIT 1 ");
				if (result.first()) {
					// le vélo courant a eu une location, on initialise les dates
					velo.setDateDerniereLocDebut(result.getTimestamp(4));
					velo.setDateDerniereLocFin(result.getTimestamp(5));
				}
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
			
			obj.setEtat(2);
			obj = this.update(obj);
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
		try {				
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Velo SET etat = " + obj.getEtat() +
                	" WHERE idVelo = " + obj.getId()
                 );

			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
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
