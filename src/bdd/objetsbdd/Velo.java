package bdd.objetsbdd;

import java.io.Serializable;
import java.sql.Timestamp;

public class Velo implements Serializable {

	private int id, etat;
	private int abonneCourant;
	private int stationCourante;
	private Timestamp dateDerniereLocDebut;
	private Timestamp dateDerniereLocFin;
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
	
	public String getEnumEtat() {
		String ret = null;
		switch (etat) {
		case 1:
			ret = "En location";
			break;
		case 2 :
			ret = "En station";
			break;
		case 3 : 
			ret = "Défectueux";
			break;
		case 4 : 
			ret = "En réparation";
			break;
		}
		
		return ret;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public void setAbonneCourant(int abonneCourant) {
		etat = 1;
		this.abonneCourant = abonneCourant;
	}
	
	public Timestamp getDateDerniereLocDebut() {
		return dateDerniereLocDebut;
	}

	public void setDateDerniereLocDebut(Timestamp dateDerniereLocDebut) {
		this.dateDerniereLocDebut = dateDerniereLocDebut;
	}

	public Timestamp getDateDerniereLocFin() {
		return dateDerniereLocFin;
	}

	public void setDateDerniereLocFin(Timestamp dateDerniereLocFin) {
		this.dateDerniereLocFin = dateDerniereLocFin;
	}
	
	@Override
	public String toString() {
		return "Vélo n°" + id + ", état : " + etat;
	}
		
	public int getStationCourante() {
		return stationCourante;
	}
	
	public int getAbonneCourant() {
		return abonneCourant;
	}
	
	public void setStationCourante(int id) {
		etat = 2;
		stationCourante = id;
	}

}
