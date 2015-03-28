package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.security.sasl.SaslException;

import bdd.DAO;
import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;


public class StationDAO extends DAO<StationBD> {

	@Override
	public StationBD find(int id) {
		StationBD station = new StationBD();
		
		try {
			// récupération de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM station WHERE idstation = " + id);
			if (result.first()) {
				// création de la station avec les données de la base
				station = new StationBD(
						id, 
						result.getDouble(2),
						result.getDouble(3),
						result.getInt(4));	
				
				// récupération des vélos liés à la station
				ResultSet resultVelo = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM posseder WHERE idstation = " + id + "AND dateRetrait is null");

				if (resultVelo.first()) {
					// ajout de chaque vélo dans la station
					DAO<Velo> daoVelo = new VeloDAO();
					station.putVelo(daoVelo.find(resultVelo.getInt(3)));
					while (resultVelo.next()) {
						station.putVelo(daoVelo.find(resultVelo.getInt(3)));						
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return station;
	}

	@Override
	/**
	 * Crée une station 
	 * Attention : aucun vélo dans la station au départ, il faut les rajouter
	 */
	public StationBD create(StationBD obj) {
		try {
			// insertion de l'objet
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO station (lat,lon) VALUES(?, ?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setDouble(2, obj.getLon());
			prepare.setDouble(1, obj.getLat());
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
	public StationBD update(StationBD obj) {
		try {
			this.connect	
			.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    ResultSet.CONCUR_UPDATABLE
			 ).executeUpdate(
				"UPDATE Station SET lat = '" + obj.getLat() + "',"+
				" lon = '" + obj.getLon() + "'" +
				" WHERE idStation = " + obj.getId()
			 );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public StationBD removeVelo(StationBD obj, Velo v, Timestamp dateRetraitVelo) {
		try {
			this.connect	
			.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    ResultSet.CONCUR_UPDATABLE
			 ).executeUpdate(
				" UPDATE posseder SET dateRetrait = '" + dateRetraitVelo + "'"+
				" WHERE idStation = " + obj.getId() + 
				" AND idVelo = " + v.getId() + 
				" AND dateRetrait is null"
			 );
			
			obj = this.find(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public StationBD addVelo(StationBD obj, Velo v, Timestamp dateDepotVelo) {
		try {				
			// insertion de l'objet
			PreparedStatement prepare = 
				this.connect.prepareStatement(
						"INSERT INTO posseder (idStation, idVelo, dateDepot) VALUES(?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS
						);
			System.out.println("id :" + obj.getId());
			prepare.setInt(1, obj.getId());
			prepare.setInt(2, v.getId());
			prepare.setTimestamp(3, dateDepotVelo);
			prepare.executeUpdate();

			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	@Override
	public void delete(StationBD obj) {
		
	}

	@Override
	public ArrayList<StationBD> getInstances() {
		ArrayList<StationBD> stations = new ArrayList<StationBD>();
		try {
	
			// récupération de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM station");
			while (result.next()) {
				StationBD s = this.find(result.getInt(1));
				stations.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stations;
	}

}
