package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import utils.IdClientException;
import utils.demandeAboException;
import utils.demandeStationException;
import utils.locationException;
import utils.retourVeloException;
import bdd.objetsbdd.Velo;
import gestionnaire.GestionnaireProxy;

public class Station {
	
	private GestionnaireProxy proxy;
	private ArrayList<Velo> listeVelos;
	private int taille;
	private int idStation;
	private int codeClient=0;

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
	public void identification(int idClient, int codeCli) throws RemoteException, IdClientException{
		int i = 0;
		boolean valid = false;
		codeClient = proxy.idValidation(idClient);
		if( codeClient != 0){
			if(codeClient == codeCli){
				
			}else{
				while(i < 3 || !valid ){
					validationCodeClient(codeCli);
				}
			}
		}else{
			throw new IdClientException(idClient);
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
	public void locationVelo(int idClient) throws RemoteException, locationException, demandeStationException {
		// vérification ID client
			// vérification qu'il y a au moins un vélo de dispo
			if (!listeVelos.isEmpty()) {
				// récupération d'un vélo
				int idVelo = listeVelos.get(0).getId();
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				// retrait du vélo, et mise à jour du cache
				if(proxy.location(idStation,idClient, idVelo,now)){
					listeVelos.remove(idVelo);
					afficherInformationsDeLocation(idVelo);
					System.out.println("Vous pouvez retirer le vélo : " + idVelo);
				}
				else{
					throw new locationException();
				}
			} else {
				// pas de vélo disponible
				// etape 4: indication de la station la plus proche
				String reponse[] = proxy.demandeStationProche(idStation,true);
				if(reponse.length == 3){
					System.out.println("Il n'y a pas de vélo de disponible dans cette station");
					System.out.println("Veuillez-vous diriger dans la station: ");
					System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
				}else{
					throw new demandeStationException();
				}
				
			}
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
