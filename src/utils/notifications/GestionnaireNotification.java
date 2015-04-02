package utils.notifications;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestionnaireNotification extends Remote{
	public void notification(int id, String action) throws RemoteException;
}
