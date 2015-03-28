package station.moteur.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import station.moteur.Station;
import station.moteur.ihm.panels.PanelAccueil;
import station.moteur.ihm.panels.PanelDemandeAbo;
import station.moteur.ihm.panels.PanelIdentification;
import station.moteur.ihm.popups.PopupLocationVelo;
import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.demandeAboException;

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
	public StationIHM(Station st) {
		try {
			// Permet de prendre l'apparence du système hôte
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Station Vélômiage"); 
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
		s = st;
		this.setContentPane(contentPane);
		panelCourant = panelMenu;
		changerPanel(Etat.Menu);
	}
	
	public void actionLouer(int identifiant, int mdp) {
		boolean identificationReussie = false ;
		try {
			 identificationReussie = s.identification(identifiant, mdp);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EssaisEcoulesException e) {
			panelIdentification.afficherErreur();
		}
		
		if (identificationReussie) {
			// location si c'est possible du vélo
			// AFAIRE
			new PopupLocationVelo(4).setVisible(true);
			
		} else {
			// affichage de l'erreur d'un essai
			panelIdentification.afficherErreurEssai();
		}
	}
	
	public void actionDemanderAbo(){
		try {
			int reponse[] = s.demanderAbo(false);
			panelDemandeAbo.afficherAboGenere(reponse[0], reponse[1]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
