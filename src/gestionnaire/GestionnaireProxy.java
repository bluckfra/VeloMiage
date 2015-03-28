package gestionnaire;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.jar.JarException;

import utils.exceptions.demandeAboException;
import utils.exceptions.demandeStationException;
import utils.exceptions.listeVeloException;
import utils.exceptions.locationException;
import utils.exceptions.retourVeloException;
import bdd.objetsbdd.Velo;

public interface GestionnaireProxy extends java.rmi.Remote{

	public int[] creerAbonnement(boolean isTech)throws java.rmi.RemoteException;
	
	/**
	 * <Stéfan> - 21/03/2015 - Step 2 - 3 
	 * @throws RemoteException
	 */
	public int validationIdClient(int id)throws java.rmi.RemoteException;
	public ArrayList<Velo> listeVelo(int idStation) throws RemoteException;
	public Object[] caracteristiquesStation(int idStation) throws RemoteException;
	public boolean location(int idStation,int idClient, int idVelo, Timestamp dateLoc) throws java.rmi.RemoteException;
	public boolean retour(int idStation,int idVelo, Timestamp dateRetour) throws java.rmi.RemoteException;
	
	/**
	 * <Stéfan> - 21/03/2015 - Step 5
	 * @throws RemoteException
	 */
	public String[] demandeStationProche(int idStation, boolean demandeLocation) throws java.rmi.RemoteException;
}
