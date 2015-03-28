package bdd.objetsdao;

import java.sql.Date;
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
						result.getTimestamp(3),
						result.getTimestamp(4),
						result.getBoolean(5));	
				
				// récupération du vélo lié à l'abonné
				ResultSet resultVelo = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM louer WHERE idAbonne = " + id + "AND dateFin is null");

				if (resultVelo.first()) {
					// ajout du vélo chez l'abonné
						DAO<Velo> daoVelo = new VeloDAO();
						abonne.setVeloCourant(resultVelo.getInt(3));
				}

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
			prepare.setTimestamp(2, obj.getDateAboDebut());
			prepare.setTimestamp(3, obj.getDateAboFin());
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
		try {				
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Abonne SET code = '" + obj.getCode() + "',"+
                	" dateAboDebut = '" + obj.getDateAboDebut() + "',"+
                	" dateAboFin = '" + obj.getDateAboFin()
                	+ "'"+
                	" WHERE idAbonne = " + obj.getId()
                 );

			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	public Abonne addVelo(Abonne obj, Velo v, Timestamp dateDebutLocation) {
		try {				
			// insertion de l'objet
			PreparedStatement prepare = 
				this.connect.prepareStatement(
						"INSERT INTO louer (idAbonne, idVelo, dateDebut) VALUES(?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS
						);
			System.out.println("id :" + obj.getId());
			prepare.setInt(1, obj.getId());
			prepare.setInt(2, v.getId());
			prepare.setTimestamp(3, dateDebutLocation);
			prepare.executeUpdate();
			
			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}


	@Override
	public void delete(Abonne obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Abonne> getInstances() {
		ArrayList<Abonne> abonnes = new ArrayList<Abonne>();
		try {
	
			// récupération de la station
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM abonne");
			while (result.next()) {
				Abonne a = this.find(result.getInt(1));
				abonnes.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return abonnes;
	}


}
