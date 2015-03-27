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
	 * <Mélanie&Stéfan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public Station(int id, int taille) throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		// on doit récupérer la liste via le gestionnaire
		this.idStation = id;
		this.taille = taille;
		listeVelos = proxy.listeVelo(id);
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
	public void demanderAbo(boolean isTech) throws RemoteException, demandeAboException{
		int reponse[] = proxy.creerAbonnement(isTech);
		if(reponse.length == 2){
			afficherInformationCreationAbonnement(reponse);
		}else{
			throw new demandeAboException();
		}
		
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
	
	
	
	
	public boolean validationCodeClient (int code){
		return (codeClient == code) ? true : false;
	}
	
	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 * @throws locationException 
	 * @throws demandeStationException 
	 */
	public int locationVelo(int idClient) throws RemoteException, locationException, demandeStationException {
		int idVelo = -1;
		// vérification qu'il y a au moins un vélo de dispo
		if (!listeVelos.isEmpty()) {
			// récupération d'un vélo
			idVelo = listeVelos.get(0).getId();

			Timestamp now = new Timestamp(System.currentTimeMillis());
			// retrait du vélo, et mise à jour du cache
			if(!proxy.location(idStation,idClient, idVelo,now)){
				
				listeVelos.remove(idVelo);
				afficherInformationsDeLocation(idVelo);
				System.out.println("Vous pouvez retirer le vélo : " + idVelo);
			} 
		} 
		return idVelo;
	}
	
	public String[] demandeStations() {
		// pas de vélo disponible
		// etape 4: indication de la station la plus proche
		String reponse[] = null;
		try {
			reponse = proxy.demandeStationProche(idStation,true);
			System.out.println("Il n'y a pas de vélo de disponible dans cette station");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (demandeStationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reponse;
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 * @throws demandeStationException 
	 * @throws retourVeloException 
	 */
	public void retourVelo(int idV) throws RemoteException, demandeStationException, retourVeloException {
		if (listeVelos.size() != this.taille) {
			// mise à jour du cache
			Velo v = new Velo(idV);
			listeVelos.add(v);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// retour du vélo
			if(proxy.retour(idStation,idV,now)){
				afficherInformationsDeRetour(v);
				System.out.println("Retour vélo accepté");
			}
			else{
				throw new retourVeloException();
			}
			
		} else {
			String reponse[] = proxy.demandeStationProche(idStation,false);
			if(reponse.length == 3){
			System.out.println("Il n'y a pas de place pour votre vélo de disponible -- Station saturée");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
			}else{
				throw new demandeStationException();
			}
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
}
