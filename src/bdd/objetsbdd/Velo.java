package bdd.objetsbdd;

public class Velo {

	private int id,etat;
	private Abonne abonneCourant;
	private StationBD stationCourante;
	
	public Velo() {
		// TODO Auto-generated constructor stub
	}
	
	public Velo(int id, int etat) {
		this.id = id;
		this.etat = etat;
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
		return "V�lo n�" + id + ", �tat : " + etat;
	}
}
