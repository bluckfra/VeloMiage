package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;

import gestionnaire.GestionnaireStationProxy;

public class StationVelos {
	
	private GestionnaireStationProxy proxy;
	public StationVelos() throws Exception{
		proxy = (GestionnaireStationProxy)Naming.lookup("rmi://localhost:1099/GestionStat");
	
	}
	
	public void demanderAbo() throws RemoteException{
		String reponse[] = proxy.creerAbonnement();
		afficherInformationCreationAbonnement(reponse);
	}
	
	public void afficherInformationCreationAbonnement(String reponse[] ){
		System.out.println("Votre identifiant est : " + reponse[0].toString() );
		System.out.println("Votre code confidentiel est : " + reponse[1].toString() );
		System.out.println("Veuillez ne pas communiquer vos identifiants" );
	}
}
