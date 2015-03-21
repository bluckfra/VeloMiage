package gestionnaire;

import station.moteur.Station;


public class mainTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Station station = new Station();
		station.demanderAbo();
		station.bikeLocation("test");
		station.bikePayBack("test");
	}

}
