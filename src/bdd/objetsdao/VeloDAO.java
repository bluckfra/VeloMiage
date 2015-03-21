package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdd.DAO;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

public class VeloDAO extends DAO<Velo> {

	@Override
	public Velo find(int id) {
		Velo velo = new Velo();
		
		try {
			// r�cup�ration de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM velo WHERE idvelo = " + id);
			if (result.first()) {
				// cr�ation de la station avec les donn�es de la base
				velo = new Velo(
						id, 
						result.getInt(1));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return velo;
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
			// r�cup�ration des valeurs de l'insert
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

}
