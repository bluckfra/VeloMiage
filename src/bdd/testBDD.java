package bdd;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Random;

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;
import bdd.objetsdao.AbonneDAO;
import bdd.objetsdao.StationDAO;
import bdd.objetsdao.VeloDAO;

public class testBDD {
	
	public static void main(String[] args) {
		VeloDAO daoVelos = new VeloDAO();
		StationDAO daoStation = new StationDAO();
		
		Velo v = daoVelos.find(1);
		StationBD s = daoStation.find(1);
		s = daoStation.addVelo(s, v, new Timestamp(System.currentTimeMillis()));
		System.out.println(s.toString());
		s = daoStation.removeVelo(s, v, new Timestamp(System.currentTimeMillis()));
		System.out.println(s.toString());		
	}
}
