package gestionnaire.moteur;

import gestionnaire.GestionnaireProxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
	
	
	public class Gestionnaire extends UnicastRemoteObject implements GestionnaireProxy {
	//HashMap<String, Station> listeStation;
	HashMap<String, String> listeAbonne;
	HashMap<String, String> bikeList;
	
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public Gestionnaire() throws RemoteException {
		super();
		//listeStation = new HashMap<String, Station>();
		listeAbonne = new HashMap<String, String>();
		bikeList = new HashMap<String, String>();
	}


	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public synchronized String[] creerAbonnement() throws RemoteException {
		String abo[] = new String[2];
		Random rand = new Random();
		//
		int codeConf = rand.nextInt(9999) + 1000;
		//Abonne abo = new Abonne(codeConf);	
		abo[0] = "testId1";
		abo[1] =  String.valueOf(codeConf);
		return abo;
	}
	
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LocateRegistry.createRegistry(1099);						// creation du proxy sur le port 1099
		Naming.rebind("GestionStat", new Gestionnaire());			// Choix du nom du proxy
		System.out.println("Gestionnaire est enregistrée");
	}
	
	/**
	 * WIP
	 * <Stéfan> - 21/03/2015 - Step 2
	 * @throws RemoteException
	 */
	public boolean idValidation(String id) throws RemoteException {
		boolean res;
		if(listeAbonne.containsKey(id)){
			//return res = (LocalDateTime.now() < listeAbonne.get(id).getDateEndRegister()) ? true : false;	
		}
		return true;
	}

	/**
	 * WIP
	 * <Stéfan> - 21/03/2015 - Step 2
	 * @throws RemoteException
	 */
	public void location(String idVelo) throws RemoteException {
		// add location 
		// change value in bd --> MAJ CACHE
		System.out.println("Vélo retiré");
	}


	public void payBack(String idVelo) throws RemoteException {
		// change value in bd of the velo --> MAJ CACHE
		System.out.println("Vélo rendu");
	}
	
	public void getBikeCondition(String idVelo){
		
		//System.out.println("Vélo " + bikeList.get(idVelo).getEtat());
	}
}
