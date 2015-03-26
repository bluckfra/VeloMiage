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
		s = daoS.addVelo(s,daoV.find(1) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(2) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(3) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(4) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(5) , new Timestamp(System.currentTimeMillis()));
		
		s = daoS.find(2);
		s = daoS.addVelo(s,daoV.find(6) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(7) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(8) , new Timestamp(System.currentTimeMillis()));

		s = daoS.find(3);
		s = daoS.addVelo(s,daoV.find(9) , new Timestamp(System.currentTimeMillis()));
		s = daoS.addVelo(s,daoV.find(10) , new Timestamp(System.currentTimeMillis()));

	}
}
