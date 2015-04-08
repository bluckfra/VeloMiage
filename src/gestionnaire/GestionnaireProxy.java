package gestionnaire;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

import utils.exceptions.AbonneInexistantException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.VeloInexistantException;
import utils.exceptions.VeloPasLoueException;
import bdd.objetsbdd.Velo;

public interface GestionnaireProxy extends java.rmi.Remote{
	/**
	 * <Stéfan> - 21/03/2015
	 * @throws RemoteException
	 * @throws AbonneInexistantException 
	 */
	public int[] creerAbonnement(boolean isTech)throws java.rmi.RemoteException;
	public int validationIdClient(int id)throws java.rmi.RemoteException, AbonneInexistantException;
	public ArrayList<Velo> listeVelo(int idStation) throws RemoteException;
	public Object[] caracteristiquesStation(int idStation) throws RemoteException;
	public boolean location(int idStation,int idClient, int idVelo, Timestamp dateLoc) throws java.rmi.RemoteException, LocationEnCoursException;
	public Object[] retour(int idStation,int idVelo, Timestamp dateRetour) throws java.rmi.RemoteException, VeloPasLoueException, VeloInexistantException;
	public Object[] demandeStationProche(int idStation, boolean demandeLocation) throws java.rmi.RemoteException;

}
