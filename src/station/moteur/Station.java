package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import bdd.objetsbdd.Velo;
import gestionnaire.GestionnaireProxy;

public class Station {
	
	private GestionnaireProxy proxy;
	private ArrayList<Velo> listeVelos;
	private int taille;
	private int idStation;

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
	 */
	public void demanderAbo(boolean isTech) throws RemoteException {
		int reponse[] = proxy.creerAbonnement(isTech);
		afficherInformationCreationAbonnement(reponse);
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 */
	public void locationVelo(int idClient) throws RemoteException {
		// vérification ID client
		if (proxy.idValidation(idClient)) {
			// vérification qu'il y a au moins un vélo de dispo
			if (!listeVelos.isEmpty()) {
				// récupération d'un vélo
				int idVelo = listeVelos.get(0).getId();
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				// retrait du vélo, et mise à jour du cache
				proxy.location(idStation,idClient, idVelo,now);
				listeVelos.remove(idVelo);
				afficherInformationsDeLocation(idVelo);
				System.out.println("Vous pouvez retirer le vélo : " + idVelo);
			} else {
				// pas de vélo disponible
				// etape 4: indication de la station la plus proche
				String reponse[] = proxy.demandeStationProche(idStation,true);
				System.out.println("Il n'y a pas de vélo de disponible dans cette station");
				System.out.println("Veuillez-vous diriger dans la station: ");
				System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
			}

		} else {
			System.out.println("Attention ID ou mdp incorrect");
		}
	}

	/**
	 * <Stéfan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 */
	public void retourVelo(int idV) throws RemoteException {
		if (listeVelos.size() != this.taille) {
			// mise à jour du cache
			Velo v = new Velo(idV);
			listeVelos.add(v);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// retour du vélo
			proxy.retour(idStation,idV,now);

			afficherInformationsDeRetour(v);
			System.out.println("Retour vélo accepté");
		} else {
			String reponse[] = proxy.demandeStationProche(idStation,false);
			System.out.println("Il n'y a pas de place pour votre vélo de disponible -- Station saturée");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonnées: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
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
