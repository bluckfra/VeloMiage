package station.ihm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import station.Station;
import station.ihm.panels.PanelAccueil;
import station.ihm.panels.PanelDemandeAbo;
import station.ihm.panels.PanelIdentification;
import station.ihm.panels.PanelRetourVelo;
import station.ihm.popups.PopupErreurRemote;
import station.ihm.popups.PopupLocationVelo;
import station.ihm.popups.PopupRestitutionVelo;
import station.ihm.popups.PopupStationPlacesDispo;
import station.ihm.popups.PopupTicketAbo;
import utils.exceptions.AbonneInexistantException;
import utils.exceptions.AbonnementExpireException;
import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.StationPleineException;
import utils.exceptions.VeloInexistantException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.LocationException;

import java.awt.GridLayout;
import java.net.IDN;
import java.rmi.RemoteException;

public class StationIHM extends JFrame {
	private Station s ;
	private JPanel panelCourant;
	private JPanel contentPane;
	private PanelIdentification panelIdentification;
	private PanelAccueil panelMenu;
	private PanelDemandeAbo panelDemandeAbo;
	private PanelRetourVelo panelRetourVelo;
	private Object[] donneesTicket;
	
	
	public StationIHM(Station st) {
		try {
			// Permet de prendre l'apparence du système hôte
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Station Vélômiage n°" + st.getIdStation()); 
		setSize(470, 220); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 


		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		panelIdentification = new PanelIdentification(this);
		panelMenu = new PanelAccueil(this);
		panelDemandeAbo = new PanelDemandeAbo(this);
		panelRetourVelo = new PanelRetourVelo(this);
		s = st;
		this.setContentPane(contentPane);
		panelCourant = panelMenu;
		changerPanel(Etat.Menu);
	}

	public void actionLouer(int identifiant, int mdp) throws RemoteException {
		boolean identificationReussie = false ;
		try {
			identificationReussie = s.identification(identifiant, mdp);
			if (identificationReussie) {
				actionLocation(identifiant);			
			} else {
				panelIdentification.afficherErreurEssai();
			}
		} catch (RemoteException e) {
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (EssaisEcoulesException e) {
			panelIdentification.afficherErreurPlusDessais();
		} catch (AbonneInexistantException e) {
			// TODO Auto-generated catch block
			panelIdentification.afficherErreurEssai();
		} catch (AbonnementExpireException e) {
			panelIdentification.afficherErreurAbonnementExpire();
			e.printStackTrace();
		}
	}
	
	// PANEL LOCATION
	
	public void actionLocation(int idCli) throws RemoteException {
		try {
			int idVelo = s.locationVelo(idCli);
			this.changerPanel(Etat.Menu);
			new PopupLocationVelo(idVelo).setVisible(true);
		} catch (RemoteException e) {
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (LocationException e) {
			// gestion cas pas de vélo dispo
			actionStationsPlacesDispos(true);
		} catch (LocationEnCoursException e) {
			// gestion cas abonne a deja une location 
			panelIdentification.afficherErreurDejaLoc();
		}
	}
	
	// PANEL RESTITUTION
	public void actionRestitution(int idVelo) throws RemoteException {
		try {
			donneesTicket = s.retourVelo(idVelo);
			this.changerPanel(Etat.Menu);
			new PopupRestitutionVelo(idVelo,this).setVisible(true);
			panelRetourVelo.afficherIdVeloErreur();			
		} catch (RemoteException e) {
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (VeloPasLoueException e) {
			panelRetourVelo.afficherVeloInvalideError();			
		} catch (StationPleineException e) {
			actionStationsPlacesDispos(false);
		} catch (VeloInexistantException e) {
			panelRetourVelo.afficherIdVeloErreur();
		}
	}
	
	public void actionStationsPlacesDispos(boolean loc) {
		try {
			Object[] res = s.demandeStations(loc);
			new PopupStationPlacesDispo(res).setVisible(true);
		} catch (RemoteException e) {
			new PopupErreurRemote().setVisible(true);
		} finally {
			this.changerPanel(Etat.Menu);
		}
	}
	
	public void afficherTicket() {
		new PopupTicketAbo(donneesTicket).setVisible(true);
	}
	
	// PANEL ABONNEMENT
	
	public void actionDemanderAbo(){
		try {
			int reponse[] = s.demanderAbo(false);
			panelDemandeAbo.afficherAboGenere(reponse[0], reponse[1]);
		} catch (RemoteException e) {
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		}
	}
	
	public void changerPanel(Etat e) {
		JPanel p;
		switch (e) {
		case Identification:
			p = panelIdentification;
			break;
		case Menu:
			p = panelMenu;
			panelIdentification.remiseAZero();
			panelRetourVelo.remiseAZero();
			break;
		case DemandeAbonnement:
			p = panelDemandeAbo;
			break;
		case Restitution:
			p = panelRetourVelo;
			break;
		default:
			p = panelMenu;
			break;
		}
		panelCourant.setVisible(false);
		contentPane.remove(panelCourant);
		panelCourant = p;
		contentPane.add(panelCourant);
		panelCourant.setVisible(true);
	}

}
