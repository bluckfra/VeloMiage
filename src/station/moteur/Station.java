package station.moteur;

import java.rmi.Naming;
import java.rmi.RemoteException;

import gestionnaire.GestionnaireProxy;

public class Station {
	
	private GestionnaireProxy proxy;
	public Station() throws Exception{
		proxy = (GestionnaireProxy)Naming.lookup("rmi://localhost:1099/GestionStat");
	
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
