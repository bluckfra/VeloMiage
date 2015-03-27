package gestionnaire;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.jar.JarException;

import bdd.objetsbdd.Velo;

public interface GestionnaireProxy extends java.rmi.Remote{
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public int[] creerAbonnement(boolean isTech)throws java.rmi.RemoteException;
	
	/**
	 * <Stéfan> - 21/03/2015 - Step 2
	 * @throws RemoteException
	 */
	public boolean idValidation(int id)throws java.rmi.RemoteException;
	public ArrayList<Velo> listeVelo(int idStation) throws RemoteException;
	public void location(int idStation,int idClient, int idVelo, Timestamp dateLoc) throws java.rmi.RemoteException;
	public double[] retour(int idStation,int idVelo, Timestamp dateRetour) throws java.rmi.RemoteException;
	
	public String[] demandeStationProche(int idStation, boolean demandeLocation) throws java.rmi.RemoteException;
}
