package gestionnaire;

import java.rmi.RemoteException;
import java.util.jar.JarException;

public interface GestionnaireProxy extends java.rmi.Remote{
	/**
	 * <Mélanie&Stéfan> - 19/03/2015 - Step 1
	 * @throws RemoteException
	 */
	public String[] creerAbonnement()throws java.rmi.RemoteException;
	
	/**
	 * <Stéfan> - 21/03/2015 - Step 2
	 * @throws RemoteException
	 */
	public boolean idValidation(String id)throws java.rmi.RemoteException;

	public void location(String idVelo) throws java.rmi.RemoteException;
	public void payBack(String idVelo) throws java.rmi.RemoteException;
}
