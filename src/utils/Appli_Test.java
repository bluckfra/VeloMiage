package utils;

import gestionnaire.moteur.Gestionnaire;
import station.moteur.Station;
import station.moteur.ihm.StationIHM;


public class Appli_Test {

	public static void main(String[] args) throws Exception {
		/*
		 * Remplace l'IHM en attendant
		 */
		Station station = new Station(1,30);
		StationIHM ihm = new StationIHM(station);
		ihm.setVisible(true);

		//station.demanderAbo(false);
		//station.locationVelo(3);
		//station.retourVelo(4);
	}

}
