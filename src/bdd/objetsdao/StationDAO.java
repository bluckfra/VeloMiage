package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
						result.getDouble(3));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return station;
	}

	@Override
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
			
			/*//Maintenant, nous devons créer les liens vers les vélos
			//Si le vélo n'existe pas en base, on le créé
			for (Velo v : obj.getVelosStation()) {
				if (v.getId() == 0) {
					DAO<Velo> veloDAO = new VeloDAO();
					v = veloDAO.create(v);
				}
				
					
					long id2 = result2.getLong("id");
					PreparedStatement prepare2 = this .connect
                                                        .prepareStatement(
                                                			"INSERT INTO j_soc_dev (jsd_id, jsd_soc_k, jsd_dev_k)"+
                                                			" VALUES(?, ?, ?)"
                                                		);
					prepare2.setLong(1, id2);
					prepare2.setLong(2, id);
					prepare2.setLong(3, dev.getId());
					prepare2.executeUpdate();
			

			}*/

		}
		catch (SQLException e) {
				e.printStackTrace();
			}
		return obj;
	}

	@Override
	public StationBD update(StationBD obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(StationBD obj) {
		// TODO Auto-generated method stub
		
	}

}
