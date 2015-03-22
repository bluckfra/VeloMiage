package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import gestionnaire.GestionnaireProxy;

public class Station {
	
	private GestionnaireProxy proxy;
	private ArrayList<String> veloList;
	private int size;

	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Step 1
	 * 
	 * @throws Exception
	 */
	public Station() throws Exception {
		proxy = (GestionnaireProxy) Naming
				.lookup("rmi://localhost:1099/GestionStat");
		veloList = new ArrayList<String>();
		veloList.add("test");
		veloList.add("test2");
		size = 15;
	}

	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Step 1
	 * @params bool technicien
	 * @throws RemoteException
	 */
	public void demanderAbo(boolean isTech) throws RemoteException {
		int reponse[] = proxy.creerAbonnement(isTech);
		afficherInformationCreationAbonnement(reponse);
	}

	/**
	 * <St�fan> - 21/03/2015 - Step 2 & 3 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void bikeLocation(int idClient) throws RemoteException {
		if (proxy.idValidation(idClient)) {
			if (!veloList.isEmpty()) {
				String idVelo = veloList.get(0);
				veloList.remove(idVelo);
				proxy.location(idVelo);
				DisplayLocationinformation(idVelo);
				System.out.println("v�lo retir�");
			} else {
				System.out.println("Il n'y a pas de v�lo de disponible");
			}

		} else {
			System.out.println("Attention ID ou mdp incorrect");
		}
	}

	/**
	 * <St�fan> - 21/03/2015 - Step 2 & 3 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void bikePayBack(String idVelo) throws RemoteException {
		if (veloList.size() != this.size) {
			veloList.add(idVelo);
			proxy.payBack(idVelo);

			DisplayPayBackinformation(idVelo);
			System.out.println("Retour v�lo accept�");
		} else {
			System.out
					.println("Il n'y a pas de place pour votre v�lo de disponible -- Station satur�e");
		}
	}

	/* Display Management */
	/**
	 * <St�fan> - 21/03/2015 - Step 2 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void DisplayLocationinformation(String idVelo) {
		System.out.println("Vous pouvez retirer le v�lo " + idVelo);
	}

	/**
	 * <St�fan> - 21/03/2015 - Step 2 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void DisplayPayBackinformation(String idVelo) {
		System.out.println("V�lo " + idVelo);
	}

	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Step 1 Arg String
	 * 
	 * @throws RemoteException
	 */
	public void afficherInformationCreationAbonnement(int reponse[]) {
		System.out.println("Votre identifiant est : " + reponse[0]);
		System.out.println("Votre code confidentiel est : "
				+ reponse[1]);
		System.out.println("Veuillez ne pas communiquer vos identifiants");
	}
}
