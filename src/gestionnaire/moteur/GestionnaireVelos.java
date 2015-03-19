package gestionnaire.moteur;

import gestionnaire.GestionnaireStationProxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

	public class GestionnaireVelos extends UnicastRemoteObject implements GestionnaireStationProxy {
	//HashMap<String, Station> listeStation;
	//HashMap<String, Abonne> listeAbonne;
	
	public GestionnaireVelos() throws RemoteException {
		super();
		//listeStation = new HashMap<String, Station>();
		//listeAbonne = new HashMap<String, Abonne>();
	}

 

	public synchronized String[] creerAbonnement() throws RemoteException {
		// TODO Auto-generated method stub
		String abo[] = new String[2];
		Random rand = new Random();
		//
		int codeConf = rand.nextInt(9999) + 1000;
		//Abonne abo = new Abonne(codeConf);	
		abo[0] = "testId1";
		abo[1] =  String.valueOf(codeConf);
		return abo;
	}
	
	public static void main(String[] args) throws Exception {
		// creation du proxy sur le port 1099 (choisi=)
		LocateRegistry.createRegistry(1099);
		// Choix du nom du proxy
		Naming.rebind("GestionStat", new GestionnaireVelos());
		System.out.println("Gestionnaire est enregistrée");
		}
}
