package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.IdClientException;
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
	 * <M�lanie&St�fan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public Station(int id) throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		// on doit r�cup�rer la liste via le gestionnaire
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
	 * <M�lanie&St�fan> - 19/03/2015 - Etape 1
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
	 * <St�fan> - 27/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 * @throws IdClientException 
	 */
	public boolean identification(int idClient, int codeCliInsere) throws RemoteException, EssaisEcoulesException{
		// r�cup�ration du mdp client s'il n'est pas r�cup�r�
		if (codeClient == 0) {
			codeClient = proxy.validationIdClient(idClient);			
		}
		if(codeClient == codeCliInsere){
			// r�initialisation des variables pour le prochain client
			codeClient = 0;
			nbEssais = 0;
			return true ;
		} else {
			nbEssais++;
			// nombre d'essais �coul�s
			if (nbEssais == NBESSAIS_MAX) {
				throw new EssaisEcoulesException();
			}
			return false ;
		}
	}
	
		
	/**
	 * <St�fan> - 21/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 * @throws locationException 
	 * @throws demandeStationException 
	 */
	public int locationVelo(int idClient) throws RemoteException, locationException {
		int idVelo = -1;
		// v�rification qu'il y a au moins un v�lo de dispo
		if (listeVelos.isEmpty()) {
			throw new locationException();
		}
		// r�cup�ration d'un v�lo
		for(int i = 0; i < listeVelos.size(); i++){
			System.out.println("velo avant loca " + listeVelos.get(i).toString());
		}
		idVelo = listeVelos.get(0).getId();
		listeVelos.remove(0);
		
		// test retour 
		for(int i = 0; i < listeVelos.size(); i++){
			System.out.println("velo apres loca " + listeVelos.get(i).toString());
		}
		afficherInformationsDeLocation(idVelo);
		System.out.println("Vous pouvez retirer le v�lo : " + idVelo);
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// retrait du v�lo, et mise � jour du cache
		while(!proxy.location(idStation,idClient, idVelo,now)){	
		} 
		
		return idVelo;
	}
	
	public String[] demandeStations() throws RemoteException {
		// pas de v�lo disponible
		// etape 4: indication de la station la plus proche
		String reponse[] = null;
			reponse = proxy.demandeStationProche(idStation,true);
			System.out.println("Il n'y a pas de v�lo de disponible dans cette station");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonn�es: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
		
		return reponse;
	}

	/**
	 * <St�fan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 * @throws demandeStationException 
	 * @throws retourVeloException 
	 */
	public void retourVelo(int idV) throws RemoteException, demandeStationException {
		if (listeVelos.size() != this.taille) {
			// mise � jour du cache
			Velo v = new Velo(idV);
			listeVelos.add(v);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// retour du v�lo
			proxy.retour(idStation,idV,now);
				afficherInformationsDeRetour(v);
				System.out.println("Retour v�lo accept�");

			
		} else {
			String reponse[] = proxy.demandeStationProche(idStation,false);
			if(reponse.length == 3){
			System.out.println("Il n'y a pas de place pour votre v�lo de disponible -- Station satur�e");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonn�es: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
			}else{
				throw new demandeStationException();
			}
		}
	}

	/* Display Management */
	/**
	 * <St�fan> - 21/03/2015 - Etape 2 
	 * @param string idVelo
	 * @throws RemoteException
	 */
	public void afficherInformationsDeLocation(int idVelo) {
		System.out.println("Vous pouvez retirer le v�lo " + idVelo);
	}

	/**
	 * <St�fan> - 21/03/2015 - Etape 2 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void afficherInformationsDeRetour(Velo velo) {
		System.out.println(velo.toString());
	}

	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Etape 1 
	 * @param int reponse[]
	 * @throws RemoteException
	 */
	public void afficherInformationCreationAbonnement(int reponse[]) {
		System.out.println("Votre identifiant est : " + reponse[0]);
		System.out.println("Votre code confidentiel est : "
				+ reponse[1]);
		System.out.println("Veuillez ne pas communiquer vos identifiants");
	}
}
