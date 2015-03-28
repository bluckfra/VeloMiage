package gestionnaire.moteur.ihm;

import gestionnaire.moteur.Gestionnaire;
import gestionnaire.moteur.ihm.panels.PanelStations;
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

import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;
import station.moteur.ihm.Etat;
import station.moteur.ihm.panels.PanelAccueil;
import station.moteur.ihm.popups.PopupLocationVelo;

public class GestionnaireIHM extends JFrame {
	// Déclaration des objets graphiques
	private JMenuBar menu;
	protected JMenu menuFichier,menuStations,menuStats;
	protected JMenuItem quitter,stats,stations; 
	protected JPanel contentPane,panelCourant;
	private PanelStations panelStations;
	private PopupVelos popupDetailsStation;
	private Gestionnaire gestionnaire;
	private boolean isDetails;
	
	public GestionnaireIHM(Gestionnaire g) {
		
		gestionnaire = g ;
		
		try {
			// Permet de prendre l'apparence du système hôte
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Gestion Vélômiage"); 
		setSize(600, 450); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Initialisation du panel principal
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		
		// Création de la JMenuBar
		menu = new JMenuBar();
		this.setJMenuBar(menu);

		// Déclaration des JMenu
		menuFichier = new JMenu("Fichier");
		menuStations = new JMenu("Stations");
		menuStats = new JMenu("Statistiques");
		
		// Ajout au menu principal
		menu.add(menuFichier);
		menu.add(menuStations);
		menu.add(menuStats);

		// Création des items
		stats = new JMenuItem("Statistisques usage");
		stations = new JMenuItem("Stations courantes");
		quitter = new JMenuItem("Quitter");
		
		// Création de l'écoute sur le bouton quitter
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});

		stations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		// Ajout des composants à la JMenuBar
		menuStations.add(stations);
		menuStats.add(stats);
		menuFichier.add(quitter);
		
		// Modification du panel courant
		this.setContentPane(contentPane);
		panelStations = new PanelStations(gestionnaire.getInstancesStations(),this);
		contentPane.add(panelStations);
		panelCourant = panelStations;
		
		this.setVisible(true);
	}
	
	// Méthode qui permet de changer le JPanel du contentpane
	public void changerPanel(EtatGest e) {
		JPanel p;
		switch (e) {
			case Stations:
				p = panelStations;
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
		panelStations.rechargerTableau(stations);
		
		if (popupDetailsStation != null) {
			System.out.println("modif popup");
			if (popupDetailsStation.getIdStation() == s.getId()) {
				popupDetailsStation.rechargerTableau(gestionnaire.getInstancesVelos(s.getId()));
			}
		}
		
	}
	
	public void actionAfficherDetailsStation(StationBD s) {
		System.out.println("init");
		popupDetailsStation = new PopupVelos(s);
		popupDetailsStation.setVisible(true);
		isDetails = true;
	}

}


