package utils;

import gestionnaire.moteur.Gestionnaire;
import gestionnaire.moteur.ihm.TechnicienIHM;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Timestamp;

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsdao.AbonneDAO;
import bdd.objetsdao.StationDAO;
import bdd.objetsdao.VeloDAO;
import station.Station;
import station.ihm.StationIHM;

public class LanceurStation {

	public static void main(String[] args) {
		try {
			/*LocateRegistry.createRegistry(1099);
			Gestionnaire gestionnaire = new Gestionnaire();
			Naming.rebind("GestionStat", gestionnaire ); // Choix du nom du*/

			StationDAO daoStation = new StationDAO();
			for (StationBD s : daoStation.getInstances()) {
					Station station = new Station(s.getId());
					StationIHM ihm = new StationIHM(station);
					ihm.setVisible(true);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
