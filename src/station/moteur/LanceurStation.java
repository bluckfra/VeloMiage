package station.moteur;

import station.moteur.ihm.StationIHM;

public class LanceurStation {
	public static void main(String[] args) {
		if (args.length > 0) {
			for (String id : args) {	
				try {
					int idStation = Integer.parseInt(id);
					Station s = new Station(idStation, 5);
					StationIHM ihm = new StationIHM(s);
				} catch (Exception e) {
					System.err.println("Erreur : pas d'entier en entrée");
					e.getStackTrace();
				}
			}
		}
	}
}
