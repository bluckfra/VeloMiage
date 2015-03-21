package bdd;

import java.util.Random;

import bdd.objetsbdd.StationBD;
import bdd.objetsdao.StationDAO;

public class testBDD {
	
	public static void main(String[] args) {
		DAO<StationBD> daoStation = new StationDAO();
		StationBD test = new StationBD();
		test.setLat(new Random().nextDouble());
		test.setLon(new Random().nextDouble());
		test = daoStation.create(test);
		//test = daoStation.find(9);
		System.out.println(test.getLat() + " / " + test.getLon());
	}
}
