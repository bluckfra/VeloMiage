package bdd.objetsbdd;

import java.io.Serializable;
import java.sql.Timestamp;

public class Velo implements Serializable {

	private int id, etat;
	private int abonneCourant;
	private int stationCourante;
	private Timestamp dateDerniereLocation;
	private Timestamp dateDernierRetour;
	private boolean isInLocation;
	
	public Velo() {
		// TODO Auto-generated constructor stub
	}
	
	public Velo(int id, int etat) {
		this.id = id;
		this.etat = etat;
		isInLocation = false;
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
	
	public EtatVelo getEnumEtat() {
		return switchEtat(etat);
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public void setAbonneCourant(int abonneCourant) {
		etat = 1;
		isInLocation = true ;
		this.abonneCourant = abonneCourant;
	}
	
	public boolean isInLocation() {
		return isInLocation;
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
	
	public int getStationCourante() {
		return stationCourante;
	}
	
	public int getAbonneCourant() {
		return abonneCourant;
	}
	
	private EtatVelo switchEtat(int e) {
		EtatVelo etat = EtatVelo.EnLocation;
		switch (e) {
		case 1:
			etat=  EtatVelo.EnLocation;
		case 2 :
			etat= EtatVelo.EnStation;
		case 3 : 
			etat= EtatVelo.Defectueux;
		case 4 : 
			etat= EtatVelo.EnReparation;
		}
		return etat;
	}

	public void setStationCourante(int id) {
		etat = 2;
		stationCourante = id;
	}

}
