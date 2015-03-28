package gestionnaire.moteur;

import gestionnaire.GestionnaireProxy;
import gestionnaire.moteur.ihm.GestionnaireIHM;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

import utils.*;
import utils.exceptions.IdVeloException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.demandeAboException;
import utils.exceptions.demandeStationException;
import utils.exceptions.listeVeloException;
import utils.exceptions.locationException;
import utils.exceptions.retourVeloException;
import bdd.objetsbdd.*;
import bdd.objetsdao.*;

public class Gestionnaire extends UnicastRemoteObject implements GestionnaireProxy {

	private StationDAO daoStationBD;
	private AbonneDAO daoAbonne;
	private VeloDAO daoVelo;
	private static double prixHeure = 2;
	private static GestionnaireIHM ihm;
	
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @throws RemoteException
	 */
	public Gestionnaire() throws RemoteException {
		super();
		// récupération de la liste de station
		daoAbonne = new AbonneDAO();
		daoStationBD = new StationDAO();
		daoVelo = new VeloDAO();
		ihm = new GestionnaireIHM(this);


	}

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @param isTech
	 *            technicien ou non
	 * @return abonne créé
	 * @throws RemoteException
	 * @throws abonnementException 
	 */
	public synchronized int[] creerAbonnement(boolean isTech) throws RemoteException {
		
		int abo[] = new int[2];
		Random rand = new Random();
		// creation mdp
		int codeConf = rand.nextInt(9999) + 1000;

		// récupération date du jour
		Timestamp sqlDate = new Timestamp(System.currentTimeMillis());
		// +1 jour
		Timestamp sqlDateFin = new Timestamp(
				System.currentTimeMillis() + 86400000);

		System.out.println("test de la date: " + sqlDate.toString()
				+ "date fin = " + sqlDateFin.toString());
		// creation de l'abonne
		Abonne abonne = new Abonne();
		abonne.setCode(codeConf);
		abonne.setDateAboDebut(sqlDate);
		abonne.setTechnicien(isTech);
		abonne.setDateAboFin(sqlDateFin);

		abonne = daoAbonne.create(abonne);

		System.out.println("info abonné créé : id = " + abonne.toString());
		abo[0] = abonne.getId();
		abo[1] = abonne.getCode();
			return abo;
	}

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LocateRegistry.createRegistry(1099); // creation du proxy sur le port
												// 1099
		Gestionnaire gestionnaire = new Gestionnaire();
		Naming.rebind("GestionStat", gestionnaire ); // Choix du nom du
															// proxy
		System.out.println("Gestionnaire est enregistrée");
	}

	
	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 2
	 * 
	 * @throws RemoteException
	 */
	public int validationIdClient(int id) throws RemoteException {

		Abonne abonne = daoAbonne.find(id);

		// récupération date du jour
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		return (now.before(abonne.getDateAboFin()) ? abonne.getCode() : 0);

	}

	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 2
	 * 
	 * @throws RemoteException
	 * @throws listeVeloException 
	 */
	public ArrayList<Velo> listeVelo(int idStation) throws RemoteException {
			StationBD st = daoStationBD.find(idStation);
			ArrayList<Velo> velos = st.getVelosStation();
			return velos;	
	}
	
	public Object[] caracteristiquesStation(int idStation) throws RemoteException{
		Object[] resultat = new Object[2];
		// ajout de la liste de vélo
		StationBD st = daoStationBD.find(idStation);
		ArrayList<Velo> velos = st.getVelosStation();
		resultat[0] = velos;
		
		// ajout de la taille de la station
		resultat[1] = st.getNbPlace();
		// retour du resultat
		return resultat;
	}
	
	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 2
	 * 
	 * @throws RemoteException
	 * @throws locationException 
	 */
	public boolean location(int idStation,int idClient, int idVelo, Timestamp dateLoc) throws RemoteException,LocationEnCoursException {
		Abonne ab = daoAbonne.find(idClient);
		if (ab.hasVelo()) throw new LocationEnCoursException();
		StationBD st = daoStationBD.find(idStation);
		Velo v = daoVelo.find(idVelo);
		System.out.println("ID velo : " + v.getId() + " / " + idVelo);
		
		// enlever vélo de table posseder
		daoStationBD.removeVelo(st, v, dateLoc);
		// ajouter vélo table louer
		daoAbonne.addVelo(ab, v, dateLoc);
		System.out.println("Station " + idStation + " : Vélo " + idVelo + " retiré à " + dateLoc.toString());
		ihm.notifierLocationVelo(st);
		return true;
	}

	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 2
	 * 
	 * @throws RemoteException
	 * @throws retourVeloException 
	 */
	public boolean retour(int idStation,int idVelo, Timestamp dateRetour) throws RemoteException,VeloPasLoueException {
		
			StationBD st = daoStationBD.find(idStation);
			Velo v = daoVelo.find(idVelo);
			
			if (!v.isInLocation()) {
				throw new VeloPasLoueException();
			}
			
			// ajouter vélo de table posseder
			daoStationBD.addVelo(st, v, dateRetour);
			// retirer vélo table louer
			v = daoVelo.depositVelo(v, dateRetour);
			System.out.println("Vélo rendu");
			ihm.notifierLocationVelo(st);
			return true;
		
	}

	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 4
	 * @throws IdVeloException 
	 * 
	 * @throws RemoteException
	 */
	public void getInfoEtatVelo(int idVelo) {
			Velo v = daoVelo.find(idVelo);
			System.out.println(v.toString());	
	}

	/**
	 * WIP <Stéfan> - 21/03/2015 - Etape 5
	 * 
	 * @throws RemoteException
	 * @throws demandeStationException 
	 */
	public String[] demandeStationProche(int idStation, boolean demandeLocation)throws RemoteException {
		
		// récupération des lattitudes et longi de la station courante
		StationBD station = daoStationBD.find(idStation);
		TreeMap<Double, StationBD> listDistStation = new TreeMap<Double, StationBD>();

		// création variable résultat
		String res[] = new String[3];

		// récupération de la longitude et latitude de la station 1
		double latStation1 = station.getLat();
		double longStation1 = station.getLon();

		// récupération des stations et de la distance avec la station 1
		Iterator<StationBD> it = daoStationBD.getInstances().iterator();
		while (it.hasNext()) {
			StationBD s = it.next();
			double distStation = utils.Distance.distanceInKilometers(
					latStation1, longStation1, s.getLat(), s.getLon());
			listDistStation.put(distStation, s);
		}

		// choix de la station ayant des places
		Iterator<Double> itDist = listDistStation.keySet().iterator();
		while (itDist.hasNext()) {
			double dist = itDist.next();
			StationBD sDist = listDistStation.get(dist);
			/*int placeDispo = sDist.getPlaceDispo(); 
			if(placeDispo> 0 && demandeLocation){ 
				if(placeDispo != sDist.getPlaceMax()){ 
					res[0] = "" + sDist.getId();
					res[1] = "" + sDist.getLat();
					res[2] = "" + sDist.getLon();
					break; 
				}
			}else if(placeDispo>0){
					res[0] = "" + sDist.getId();
					res[1] = "" + sDist.getLat();
					res[2] = "" + sDist.getLon(); 
			break; }
			 */
		}
			return res;	
	}
	
	public ArrayList<StationBD> getInstancesStations() {
		return daoStationBD.getInstances();
	}
		
	public ArrayList<Velo> getInstancesVelos(int s) {
		return daoStationBD.find(s).getVelosStation();
	}

	public ArrayList<Velo> getInstancesAllVelos() {
		return daoVelo.getInstances();
	}
}
