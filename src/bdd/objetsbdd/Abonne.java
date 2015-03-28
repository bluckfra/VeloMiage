package bdd.objetsbdd;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Abonne implements Serializable{

	private int id,code;
	private Timestamp dateAboDebut,dateAboFin;
	private boolean isTechnicien,hasVelo;
	private Velo veloCourant;
	
	public Abonne() {
		// TODO Auto-generated constructor stub
	}
	
	public Abonne(int id, int code, Timestamp dateAboDebut, Timestamp dateAboFin,
			boolean isTechnicien) {
		this.id = id;
		this.code = code;
		this.dateAboDebut = dateAboDebut;
		this.dateAboFin = dateAboFin;
		this.isTechnicien = isTechnicien;
		this.hasVelo = false;
	}
	
	public int getId() {
		return id;
	}
	
	public Velo getVeloCourant() {
		return veloCourant;
	}

	public void setVeloCourant(Velo veloCourant) {
		this.veloCourant = veloCourant;
		this.hasVelo = true ;
	}


	public boolean hasVelo() {
		return hasVelo;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public boolean isTechnicien() {
		return isTechnicien;
	}


	public void setTechnicien(boolean isTechnicien) {
		this.isTechnicien = isTechnicien;
	}
	
	public Timestamp getDateAboDebut() {
		return dateAboDebut;
	}

	public void setDateAboDebut(Timestamp dateAboDebut) {
		this.dateAboDebut = dateAboDebut;
	}

	public Timestamp getDateAboFin() {
		return dateAboFin;
	}

	public void setDateAboFin(Timestamp dateAboFin) {
		this.dateAboFin = dateAboFin;
	}

	@Override
	public String toString() {
		String s = "Abonne n°" + id + " , code : " + code;
		if (veloCourant != null) {
			s+= "\n - vélo : " + veloCourant.toString();
		}
		return s;
	}
	
}
