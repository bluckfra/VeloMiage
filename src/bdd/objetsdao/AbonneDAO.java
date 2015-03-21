package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdd.DAO;
import bdd.objetsbdd.Abonne;

public class AbonneDAO extends DAO<Abonne>{

	@Override
	public Abonne find(int id) {
		Abonne abonne = new Abonne();
		
		try {
			// récupération de l'abonné
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM abonne WHERE idabonne = " + id);
			if (result.first()) {
				// création de l'abonné avec les données de la base
				abonne = new Abonne(
						id, 
						result.getInt(2),
						result.getDate(3),
						result.getDate(4),
						result.getBoolean(5));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return abonne;
	}

	@Override
	public Abonne create(Abonne obj) {
		try {
			// insertion de l'objet
			PreparedStatement prepare = 
				this.connect.prepareStatement(
						"INSERT INTO abonne (code, dateabodebut,dateabofin,istechnicien) VALUES(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS
						);
			prepare.setInt(1, obj.getCode());
			prepare.setDate(2, obj.getDateAboDebut());
			prepare.setDate(3, obj.getDateAboFin());
			prepare.setBoolean(4, obj.isTechnicien());
			prepare.executeUpdate();
			// récupération des valeurs de l'insert
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(1));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public Abonne update(Abonne obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Abonne obj) {
		// TODO Auto-generated method stub
		
	}


}
