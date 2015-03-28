package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.IdClientException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.StationPleineException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.demandeAboException;
import utils.exceptions.demandeStationException;
import utils.exceptions.locationException;
import utils.exceptions.retourVeloException;
import bdd.objetsbdd.Velo;
import gestionnaire.GestionnaireProxy;

public class Station {
	
	private GestionnaireProxy proxy;
	private ArrayList<Velo> listeVelos;
	private int taille;
	private int idStation;
	private int codeClient;
	private int nbEssais;
	
	private static final int NBESSAIS_MAX = 3;

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public Station(int id) throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		// on doit récupérer la liste via le gestionnaire
		this.idStation = id;
		//this.taille = taille;
		Object[] caracteristiques = proxy.caracteristiquesStation(id);
		//listeVelos = proxy.listeVelo(id);
		listeVelos = (ArrayList)caracteristiques[0];
		this.taille = (Integer)caracteristiques[1];
		
		codeClient = 0;
		nbEssais = 0;
	}

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * @params bool technicien
	 * @throws RemoteException
	 * @throws demandeAboException 
	 * @throws abonnementException 
	 */
	public int[] demanderAbo(boolean isTech) throws RemoteException{
		int reponse[] = proxy.creerAbonnement(isTech);
			afficherInformationCreationAbonnement(reponse);
		return reponse;
	}

	/**
	 * <Stéfan> - 27/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 * @throws IdClientException 
	 */
	public boolean identification(int idClient, int codeCliInsere) throws RemoteException, EssaisEcoulesException{
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
	 * @throws locationException 
	 * @throws LocationEnCoursException 
	 * @throws demandeStationException 
	 */
	public int locationVelo(int idClient) throws RemoteException, locationException, LocationEnCoursException {
		int idVelo = -1;
		// vérification qu'il y a au moins un vélo de dispo
		if (listeVelos.isEmpty()) {
			throw new locationException();
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		idVelo = listeVelos.get(0).getId();
		if (proxy.location(idStation,idClient, idVelo,now)) {	
			listeVelos.remove(0);
			afficherInformationsDeLocation(idVelo);
			System.out.println("Vous pouvez retirer le vélo : " + idVelo);			
		} 
		
		return idVelo;
	}
	
	public String[] demandeStations() throws RemoteException {
		// pas de vélo disponible
		// etape 4: indication de la station la plus proche
		String reponse[] = null;
			reponse = proxy.demandeStationProche(idStation,true);
			System.out.println("Il n'y a pas de vélo de disponible dans cette station");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
		
		return reponse;
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 * @throws StationPleineException 
	 * @throws demandeStationException 
	 * @throws retourVeloException 
	 */
	public boolean retourVelo(int idV) throws RemoteException, VeloPasLoueException, StationPleineException {
		if (listeVelos.size() == this.taille) throw new StationPleineException();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// retour du vélo
			if(proxy.retour(idStation,idV,now)){
				// mise à jour du cache
				Velo v = new Velo(idV);
				listeVelos.add(v);
				afficherInformationsDeRetour(v);
				System.out.println("Retour vélo accepté");
				return true;
			}				
			return false;
	}
	
	// méthode de demande d'une station proche si manque de place
	public void stationsProches() throws demandeStationException {
		String reponse[];
		
		try {
			reponse = proxy.demandeStationProche(idStation,false);
			System.out.println("Il n'y a pas de place pour votre vélo de disponible -- Station saturée");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);

		} catch (RemoteException e) {
			throw new demandeStationException();
		}
	}

	/* Display Management */
	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 
	 * @param string idVelo
	 * @throws RemoteException
	 */
	public void afficherInformationsDeLocation(int idVelo) {
		System.out.println("Vous pouvez retirer le vélo " + idVelo);
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void afficherInformationsDeRetour(Velo velo) {
		System.out.println(velo.toString());
	}

	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1 
	 * @param int reponse[]
	 * @throws RemoteException
	 */
	public void afficherInformationCreationAbonnement(int reponse[]) {
		System.out.println("Votre identifiant est : " + reponse[0]);
		System.out.println("Votre code confidentiel est : "
				+ reponse[1]);
		System.out.println("Veuillez ne pas communiquer vos identifiants");
	}
	
	public int getIdStation() {
		return idStation;
	}
}
