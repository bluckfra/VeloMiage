package bdd.objetsbdd;

import java.sql.Date;

public class Abonne {

	private int id,code;
	private Date dateAboDebut,dateAboFin;
	private boolean isTechnicien;
	
	public Abonne() {
		// TODO Auto-generated constructor stub
	}
	
	public Abonne(int id, int code, Date dateAboDebut, Date dateAboFin,
			boolean isTechnicien) {
		this.id = id;
		this.code = code;
		this.dateAboDebut = dateAboDebut;
		this.dateAboFin = dateAboFin;
		this.isTechnicien = isTechnicien;
	}
	
	public int getId() {
		return id;
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


	public Date getDateAboDebut() {
		return dateAboDebut;
	}


	public void setDateAboDebut(Date dateAboDebut) {
		this.dateAboDebut = dateAboDebut;
	}


	public Date getDateAboFin() {
		return dateAboFin;
	}


	public void setDateAboFin(Date dateAboFin) {
		this.dateAboFin = dateAboFin;
	}


	public boolean isTechnicien() {
		return isTechnicien;
	}


	public void setTechnicien(boolean isTechnicien) {
		this.isTechnicien = isTechnicien;
	}
	

}
