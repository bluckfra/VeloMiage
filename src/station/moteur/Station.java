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
	 * <M�lanie&St�fan> - 19/03/2015 - Etape 1
	 * 
	 * @throws Exception
	 */
	public Station(int id, int taille) throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		// on doit r�cup�rer la liste via le gestionnaire
		this.idStation = id;
		this.taille = taille;
		listeVelos = proxy.listeVelo(id);
	}

	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Etape 1
	 * @params bool technicien
	 * @throws RemoteException
	 */
	public void demanderAbo(boolean isTech) throws RemoteException {
		int reponse[] = proxy.creerAbonnement(isTech);
		afficherInformationCreationAbonnement(reponse);
	}

	/**
	 * <St�fan> - 21/03/2015 - Etape 2 & 3
	 * @param int idClient
	 * @throws RemoteException
	 */
	public void locationVelo(int idClient) throws RemoteException {
		// v�rification ID client
		if (proxy.idValidation(idClient)) {
			// v�rification qu'il y a au moins un v�lo de dispo
			if (!listeVelos.isEmpty()) {
				// r�cup�ration d'un v�lo
				int idVelo = listeVelos.get(0).getId();
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				// retrait du v�lo, et mise � jour du cache
				proxy.location(idStation,idClient, idVelo,now);
				listeVelos.remove(idVelo);
				afficherInformationsDeLocation(idVelo);
				System.out.println("Vous pouvez retirer le v�lo : " + idVelo);
			} else {
				// pas de v�lo disponible
				// etape 4: indication de la station la plus proche
				String reponse[] = proxy.demandeStationProche(idStation,true);
				System.out.println("Il n'y a pas de v�lo de disponible dans cette station");
				System.out.println("Veuillez-vous diriger dans la station: ");
				System.out.println("Coordonn�es: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
			}

		} else {
			System.out.println("Attention ID ou mdp incorrect");
		}
	}

	/**
	 * <St�fan> - 21/03/2015 - Etape 2 & 3 
	 * @param string idVelo
	 * @throws RemoteException
	 */
	public void retourVelo(int idV) throws RemoteException {
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
			System.out.println("Il n'y a pas de place pour votre v�lo de disponible -- Station satur�e");
			System.out.println("Veuillez-vous diriger dans la station: ");
			System.out.println("Coordonn�es: lattitude = " + reponse[1] + " Longitudes = " + reponse[2]);
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
