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
		StationDAO daoS = new StationDAO();
		VeloDAO daoV = new VeloDAO();
		
		StationBD s = daoS.find(1);
		for (Velo v : s.getVelosStation()) {
			System.out.println(v.toString());
		}
	}
}
