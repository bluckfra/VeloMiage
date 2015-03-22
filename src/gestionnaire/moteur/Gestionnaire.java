package gestionnaire.moteur;

import gestionnaire.GestionnaireProxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import bdd.DAO;
import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsdao.AbonneDAO;
import bdd.objetsdao.StationDAO;
	
	
	public class Gestionnaire extends UnicastRemoteObject implements GestionnaireProxy {
	//HashMap<String, Station> listeStation;
	HashMap<Integer, Abonne> listeAbonne;
	HashMap<String, String> bikeList;
	
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public Gestionnaire() throws RemoteException {
		super();
		//listeStation = new HashMap<String, Station>();
		listeAbonne = new HashMap<Integer, Abonne>();
		bikeList = new HashMap<String, String>();
	}


	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public synchronized int[] creerAbonnement(boolean isTech) throws RemoteException {
		int abo[] = new int[2];
		Random rand = new Random();
		// creation mdp
		int codeConf = rand.nextInt(9999) + 1000;
		
		// récupération date du jour
		Calendar cal = Calendar.getInstance();
		Date sqlDate = new Date(cal.getTimeInMillis());
		// +1 jour
		Date sqlDateFin = new Date(cal.getTimeInMillis() + 86400000);
		
		System.out.println("test de la date: " + sqlDate.toString() + "date fin = " + sqlDateFin.toString());
		// creation de l'abonne
		DAO<Abonne> daoAbonne = new AbonneDAO();
		Abonne abonne = new Abonne();
		abonne.setCode(codeConf);
		abonne.setDateAboDebut(sqlDate);
		abonne.setTechnicien(isTech);		
		abonne.setDateAboFin(sqlDateFin);
		
		abonne = daoAbonne.create(abonne);

		// ajout dans la liste
		listeAbonne.put(abonne.getId(), abonne);
		
		System.out.println("info abonné créé : id = " + abonne.getId() + " code = " + abonne.getCode() + " dateDebt = " + abonne.getDateAboDebut().toString() + " date fin = " + abonne.getDateAboFin() + " is tech = " + abonne.isTechnicien());
		//Abonne abo = new Abonne(codeConf);	
		abo[0] =	abonne.getId();
		abo[1] =	abonne.getCode();
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
	public boolean idValidation(int id) throws RemoteException {
		
		boolean res = false;
		DAO<Abonne> daoAbonne = new AbonneDAO();
		Abonne abonne = new Abonne();
		abonne = daoAbonne.find(id);

		if(abonne.getId() == id){
			// récupération date du jour
			java.util.Calendar cal = java.util.Calendar.getInstance();
			java.util.Date utilDate = cal.getTime();
			Date dateNow = new Date(utilDate.getTime());
			
			res = (dateNow.before(listeAbonne.get(id).getDateAboFin())) ? true : false;
		}
		return res;
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
