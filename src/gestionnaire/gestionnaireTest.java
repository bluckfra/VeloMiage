package gestionnaire;

import gestionnaire.moteur.Gestionnaire;
import station.moteur.Station;


public class gestionnaireTest {

	public static void main(String[] args) throws Exception {
		/*
		 * Remplace l'IHM en attendant
		 */
		Gestionnaire g = new Gestionnaire();
		Station station = new Station(1,30);
		//station.demanderAbo(false);
		//station.locationVelo(3);
		//station.retourVelo(4);
	}

}
