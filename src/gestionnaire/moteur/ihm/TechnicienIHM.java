package gestionnaire.moteur.ihm;

import gestionnaire.moteur.ihm.panels.PanelTechnicien;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bdd.objetsbdd.Abonne;

import utils.exceptions.EssaisEcoulesException;
import utils.exceptions.LocationEnCoursException;
import utils.exceptions.StationPleineException;
import utils.exceptions.VeloPasLoueException;
import utils.exceptions.demandeAboException;
import utils.exceptions.demandeStationException;


import java.awt.GridLayout;
import java.net.IDN;
import java.rmi.RemoteException;

public class TechnicienIHM extends JFrame {
	private Abonne a ;
	private JPanel panelCourant;
	private JPanel contentPane;
	private PanelTechnicien panelTech;
	
	public TechnicienIHM(Abonne ab) {
		try {
			// Permet de prendre l'apparence du système hôte
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Compte technicien" + ab.getId()); 
		setSize(470, 220); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 


		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		panelTech = new PanelTechnicien(this);
		
		
		a = ab;
		this.setContentPane(contentPane); // on récupère l'abonné courant
		//panelCourant = panelMenu;
		//changerPanel(Etat.Menu);
	
		//panelCourant.setVisible(false);
		//contentPane.remove(panelCourant);
		//panelCourant = p;
		contentPane.add(panelCourant);
		panelCourant.setVisible(true);
	}

}
