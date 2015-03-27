package bdd.objetsbdd;

import java.sql.Timestamp;

public class Velo {

	private int id,etat;
	private Abonne abonneCourant;
	private StationBD stationCourante;
	private Timestamp dateDerniereLocation;
	private Timestamp dateDernierRetour;
	
	public Velo() {
		// TODO Auto-generated constructor stub
	}
	
	public Velo(int id, int etat) {
		this.id = id;
		this.etat = etat;
	}
	
	public Velo(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	@Override
	public String toString() {
		return "Vélo n°" + id + ", état : " + etat;
	}
	
	public Timestamp getDateDerniereLocation() {
		return dateDerniereLocation;
	}

	public void setDateDerniereLocation(Timestamp dateDerniereLocation) {
		this.dateDerniereLocation = dateDerniereLocation;
	}

	public Timestamp getDateDernierRetour() {
		return dateDernierRetour;
	}

	public void setDateDernierRetour(Timestamp dateDernierRetour) {
		this.dateDernierRetour = dateDernierRetour;
	}

}
