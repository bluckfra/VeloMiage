package station.moteur.ihm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import station.moteur.Station;
import station.moteur.ihm.panels.PanelAccueil;
import station.moteur.ihm.panels.PanelDemandeAbo;
import station.moteur.ihm.panels.PanelIdentification;
import station.moteur.ihm.panels.PanelRetourVelo;
import station.moteur.ihm.popups.PopupErreurRemote;
import station.moteur.ihm.popups.PopupLocationVelo;
import station.moteur.ihm.popups.PopupRestitutionVelo;
import station.moteur.ihm.popups.PopupStationPlacesDispo;
import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.StationPleineException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.demandeAboException;
import utils.exceptions.demandeStationException;
import utils.exceptions.locationException;

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
		} catch (RemoteException e) {
			panelIdentification.remiseAZero();
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (EssaisEcoulesException e) {
			panelIdentification.remiseAZero();
			panelIdentification.afficherErreur();
		}
		if (identificationReussie) {
			actionLocation(identifiant);			
		} else {
			panelIdentification.remiseAZero();
			panelIdentification.afficherErreurEssai();
		}
	}
	
	// PANEL LOCATION
	
	public void actionLocation(int idCli) throws RemoteException {
		try {
			int idVelo = s.locationVelo(idCli);
			this.changerPanel(Etat.Menu);
			new PopupLocationVelo(idVelo).setVisible(true);
		} catch (RemoteException e) {
			panelIdentification.remiseAZero();
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (locationException e) {
			// gestion cas pas de vélo dispo
			actionStationsPlacesDispos(true);
		} catch (LocationEnCoursException e) {
			// gestion cas abonne a deja une location 
			panelIdentification.afficherErreurDejaLoc();
		}
	}
	
	// PANEL RESTITUTION
	public void actionRestitution(int idVelo) throws RemoteException {
		boolean restitutionOK = false ;
		try {
			restitutionOK = s.retourVelo(idVelo);
		} catch (RemoteException e) {
			panelRetourVelo.remiseAZero();
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		} catch (VeloPasLoueException e) {
			panelRetourVelo.remiseAZero();
			panelRetourVelo.afficherVeloInvalideError();			
		} catch (StationPleineException e) {
			panelRetourVelo.remiseAZero();
			actionStationsPlacesDispos(false);
		}
		if (restitutionOK) {
			// affichage popup retour validé
			panelRetourVelo.remiseAZero();
			this.changerPanel(Etat.Menu);
			new PopupRestitutionVelo(idVelo).setVisible(true);
		} else {
			// affichage message erreur
			panelRetourVelo.remiseAZero();
			panelRetourVelo.afficherError();			
		}

	}
	
	public void actionStationsPlacesDispos(boolean loc) {
		try {
			Object[] res = s.demandeStations(loc);
			this.changerPanel(Etat.Menu);
			new PopupStationPlacesDispo(res).setVisible(true);
			if(loc){
				panelIdentification.remiseAZero();
			}else{
				panelRetourVelo.remiseAZero();				
			}
		} catch (RemoteException e) {
			if(loc){
				panelIdentification.remiseAZero();
			}else{
				panelRetourVelo.remiseAZero();				
			}
			this.changerPanel(Etat.Menu);
			new PopupErreurRemote().setVisible(true);
		}
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
