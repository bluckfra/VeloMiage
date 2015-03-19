package gestionnaire;

public interface GestionnaireProxy extends java.rmi.Remote{
	public String[] creerAbonnement()throws java.rmi.RemoteException;
}
