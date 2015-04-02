package utils.notifications;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GestionnaireNotificationImpl extends UnicastRemoteObject implements GestionnaireNotification{

	private int idStation;
	protected GestionnaireNotificationImpl(int idStation) throws RemoteException {
		super();
		this.idStation = idStation;
	}
	
	public void notification(int id, String action) throws RemoteException {	
		System.out.println("test notif : " + action + " id station = " + id );
	}
}
