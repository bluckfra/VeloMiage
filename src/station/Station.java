package station;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import utils.exceptions.AbonneInexistantException;
import utils.exceptions.DemandeStationException;
import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.IdClientException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.StationPleineException;
import utils.exceptions.VeloInexistantException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.LocationException;
import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.Velo;
import bdd.objetsdao.AbonneDAO;
import gestionnaire.GestionnaireProxy;
import gestionnaire.moteur.Gestionnaire;
import gestionnaire.moteur.ihm.TechnicienIHM;

public class Station{
	
	private GestionnaireProxy proxy;
	private ArrayList<Velo> listeVelos;
	private int taille;
	private static int idStation;
	private int codeClient;
	private int nbEssais;
	private String action;
	
	private static final int NBESSAIS_MAX = 3;

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public Station(int id) throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		this.idStation = id;
		Object[] caracteristiques = proxy.caracteristiquesStation(id);
		listeVelos = (ArrayList)caracteristiques[0];
		this.taille = (Integer)caracteristiques[1];
		codeClient = 0;
		nbEssais = 0;
	}

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * @params bool technicien
	 * @throws RemoteException
	 */
	public int[] demanderAbo(boolean isTech) throws RemoteException{
		int reponse[] = proxy.creerAbonnement(isTech);
		return reponse;
	}

	/**
	 * <Stéfan> - 27/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @param int codeCliInsere
	 * @throws RemoteException
	 * @throws AbonneInexistantException 
	 * @throws EssaisEcoulesException 
	 */
	public boolean identification(int idClient, int codeCliInsere) throws RemoteException, EssaisEcoulesException, AbonneInexistantException{
		// récupération du mdp client s'il n'est pas récupéré
		if (codeClient == 0) {
			codeClient = proxy.validationIdClient(idClient);			
		}
		if(codeClient == codeCliInsere){
			// réinitialisation des variables pour le prochain client
			codeClient = 0;
			nbEssais = 0;
			return true ;
		} else {
			nbEssais++;
			// nombre d'essais écoulés
			if (nbEssais == NBESSAIS_MAX) {
				throw new EssaisEcoulesException();
			}
			return false ;
		}
	}
	
		
	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 * @throws LocationException 
	 * @throws LocationEnCoursException 
	 */
	public int locationVelo(int idClient) throws RemoteException, LocationException, LocationEnCoursException {
		int idVelo = -1;
		// vérification qu'il y a au moins un vélo de dispo
		if (listeVelos.isEmpty()) {
			throw new LocationException();
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		idVelo = listeVelos.get(0).getId();
		if (proxy.location(idStation,idClient, idVelo,now)) {	
			listeVelos.remove(0);
		} 
		return idVelo;
	}
	
	public Object[] demandeStations(boolean location) throws RemoteException {
		Object reponse[] = null;
		reponse = proxy.demandeStationProche(idStation,location);
		return reponse;
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 * @throws StationPleineException 
	 * @throws VeloInexistantException 
	 * @throws demandeStationException 
	 * @throws VeloPasLoueException
	 */
	public Object[] retourVelo(int idV) throws RemoteException, VeloPasLoueException, StationPleineException, VeloInexistantException {
		if (listeVelos.size() == this.taille) throw new StationPleineException();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// retour du vélo
		Object[] retour = proxy.retour(idStation,idV,now);
		// mise à jour du cache
		Velo v = new Velo(idV);
		listeVelos.add(v);	
		return retour;
	}
	
	// méthode de demande d'une station proche si manque de place
	public Object[] stationsProches() throws DemandeStationException {
		Object reponse[];
		try {
			reponse = proxy.demandeStationProche(idStation,false);
		} catch (RemoteException e) {
			throw new DemandeStationException();
		}
		return reponse;
	}

	/**
	 * <Stéfan> - 21/03/2015 
	*/
	public int getIdStation() {
		return idStation;
	}
}
