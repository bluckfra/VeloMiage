package gestionnaire;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.jar.JarException;

import utils.demandeAboException;
import utils.demandeStationException;
import utils.listeVeloException;
import utils.locationException;
import utils.retourVeloException;
import bdd.objetsbdd.Velo;

public interface GestionnaireProxy extends java.rmi.Remote{
	/**
	 * <M�lanie&St�fan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public int[] creerAbonnement(boolean isTech)throws java.rmi.RemoteException, demandeAboException;
	
	/**
	 * <St�fan> - 21/03/2015 - Step 2 - 3 
	 * @throws RemoteException
	 */
	public int idValidation(int id)throws java.rmi.RemoteException;
	public ArrayList<Velo> listeVelo(int idStation) throws RemoteException, listeVeloException;
	public boolean location(int idStation,int idClient, int idVelo, Timestamp dateLoc) throws java.rmi.RemoteException , locationException;
	public boolean retour(int idStation,int idVelo, Timestamp dateRetour) throws java.rmi.RemoteException , retourVeloException;
	
	/**
	 * <St�fan> - 21/03/2015 - Step 5
	 * @throws RemoteException
	 */
	public String[] demandeStationProche(int idStation, boolean demandeLocation) throws java.rmi.RemoteException, demandeStationException;
}
