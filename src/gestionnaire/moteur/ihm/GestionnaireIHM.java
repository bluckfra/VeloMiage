package gestionnaire.moteur.ihm;

import gestionnaire.moteur.Gestionnaire;
import gestionnaire.moteur.ihm.panels.PanelAbonnes;
import gestionnaire.moteur.ihm.panels.PanelStations;
import gestionnaire.moteur.ihm.panels.PanelVelos;
import gestionnaire.moteur.ihm.popups.PopupAbonne;
import gestionnaire.moteur.ihm.popups.PopupVelos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;
import station.ihm.Etat;
import station.ihm.panels.PanelAccueil;
import station.ihm.popups.PopupLocationVelo;

public class GestionnaireIHM extends JFrame {
	// D�claration des objets graphiques
	private JMenuBar menu;
	protected JMenu menuFichier,menuStations;
	protected JMenuItem quitter,stations,velos,abonnes; 
	protected JPanel contentPane,panelCourant;
	private PanelStations panelStations;
	private PanelVelos panelVelos;
	private PanelAbonnes panelAbonnes;
	private PopupVelos popupDetailsStation;
	private PopupAbonne popupAbonne;
	private Gestionnaire gestionnaire;
	private boolean isDetails;
	
	public GestionnaireIHM(Gestionnaire g) {
		gestionnaire = g ;
		
		try {
			// Permet de prendre l'apparence du syst�me h�te
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Gestion V�l�miage"); 
		setSize(600, 450); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Initialisation du panel principal
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		
		// Cr�ation de la JMenuBar
		menu = new JMenuBar();
		this.setJMenuBar(menu);

		// D�claration des JMenu
		menuFichier = new JMenu("Fichier");
		menuStations = new JMenu("Information �quipements");
		
		// Ajout au menu principal
		menu.add(menuFichier);
		menu.add(menuStations);
		stations = new JMenuItem("Stations courantes");
		velos = new JMenuItem("V�los en circulation");
		abonnes = new JMenuItem("Abonn�s du service");
		quitter = new JMenuItem("Quitter");
		
		// Cr�ation de l'�coute sur le bouton quitter
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});

		stations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(EtatGest.Stations);
			}
		});
		
		velos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(EtatGest.Velos);
			}
		});

		abonnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(EtatGest.Abonnes);
			}
		});
		
		// Ajout des composants � la JMenuBar
		menuStations.add(stations);
		menuStations.add(velos);
		menuStations.add(abonnes);
		menuFichier.add(quitter);
		
		// Modification du panel courant
		this.setContentPane(contentPane);
		panelStations = new PanelStations(gestionnaire.getInstancesStations(),this);
		panelVelos = new PanelVelos(gestionnaire.getInstancesAllVelos(), this);
		panelAbonnes = new PanelAbonnes(gestionnaire.getInstancesAbonnes(), this);
		contentPane.add(panelStations);
		panelCourant = panelStations;
		
		this.setVisible(true);
	}
	
	// M�thode qui permet de changer le JPanel du contentpane
	public void changerPanel(EtatGest e) {
		JPanel p;
		switch (e) {
			case Stations:
				p = panelStations;
				break;
			case Velos:
				p = panelVelos;
				break;
			case Abonnes:
				p = panelAbonnes;
				break;
			default:
				p = panelStations;
				break;
		}
		panelCourant.setVisible(false);
		contentPane.remove(panelCourant);
		panelCourant = p;
		contentPane.add(panelCourant);
		panelCourant.setVisible(true);
	}
	
	public void notifierLocationVelo(StationBD s) {
		ArrayList<StationBD> stations = gestionnaire.getInstancesStations();
		ArrayList<Velo> velos = gestionnaire.getInstancesAllVelos();
		ArrayList<Abonne> abos = gestionnaire.getInstancesAbonnes();
		panelStations.rechargerTableau(stations);
		panelVelos.rechargerTableau(velos);
		panelAbonnes.rechargerTableau(abos);
		
		if (isDetails) {
			System.out.println("modif popup");
			if (popupDetailsStation.getIdStation() == s.getId()) {
				popupDetailsStation.rechargerTableau(gestionnaire.getInstancesVelos(s.getId()));
			}
		}
		
	}
	
	public void actionAfficherDetailsStation(StationBD s) {
		isDetails = true;
		popupDetailsStation = new PopupVelos(s);
		popupDetailsStation.setVisible(true);
	}
	
	public void actionAfficherDetailsAbonne(Abonne abonneCourant) {
		isDetails = true;
		popupAbonne = new PopupAbonne(abonneCourant);
		popupAbonne.setVisible(true);
	}

}


