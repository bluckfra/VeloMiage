package station.ihm.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import station.ihm.Etat;
import station.ihm.StationIHM;

public class PanelAccueil extends JPanel {

	protected StationIHM modele;
	private PanelAccueil myself = this;
	/**
	 * Create the panel.
	 */
	public PanelAccueil(StationIHM m) {		
		modele = m;
		
		setLayout(null);
		JLabel lblBienvenue = new JLabel("Bienvenue ! Choisissez une action :");
		lblBienvenue.setBounds(10, 11, 169, 14);
		add(lblBienvenue);
		
		JButton btnRendreVelo = new JButton("Rendre un v\u00E9lo");
		btnRendreVelo.setBounds(20, 36, 420, 23);
		add(btnRendreVelo);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(10, 156, 67, 23);
		add(btnQuitter);
		
		JButton btnDemanderUnAbonnement = new JButton("Demander un abonnement");
		btnDemanderUnAbonnement.setBounds(20, 70, 420, 23);
		add(btnDemanderUnAbonnement);
		
		JButton btnLouerVelo = new JButton("Louer un v\u00E9lo");
		btnLouerVelo.setBounds(20, 104, 420, 23);
		add(btnLouerVelo);
		
		btnDemanderUnAbonnement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.changerPanel(Etat.DemandeAbonnement);
				modele.actionDemanderAbo();
				
			}
		});
		
		btnLouerVelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.changerPanel(Etat.Identification);
			}
		});

		btnRendreVelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.changerPanel(Etat.Restitution);
			}
		});
		
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(myself, "Voulez réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
					
				if (reponse == JOptionPane.YES_OPTION) 
				{
					System.exit(0);
				}
			}
		});

	}

}
