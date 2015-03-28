package station.moteur.ihm.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import station.moteur.ihm.Etat;
import station.moteur.ihm.StationIHM;

public class PanelIdentification extends JPanel {
	private JTextField textFieldId;
	private JTextField textFieldMdp;
	private JLabel lblIdent,lblMotDePasse,lblIdentifiant,labelErreur;
	protected StationIHM modele;

	/**
	 * Create the panel.
	 */
	public PanelIdentification(StationIHM m) {	
		modele = m;
		setLayout(null);
		 lblIdent = new JLabel("S'identifier :");
		lblIdent.setBounds(10, 11, 169, 14);
		add(lblIdent);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(105, 156, 80, 23);
		add(btnRetour);
		
		 lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(30, 65, 131, 14);
		add(lblIdentifiant);
		
		 lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(30, 90, 131, 14);
		add(lblMotDePasse);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(194, 62, 235, 20);
		add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldMdp = new JTextField();
		textFieldMdp.setBounds(194, 87, 235, 20);
		add(textFieldMdp);
		textFieldMdp.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(10, 156, 85, 23);
		add(btnValider);
		
		 labelErreur = new JLabel("");
		labelErreur.setBounds(30, 36, 399, 14);
		add(labelErreur);
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.changerPanel(Etat.Menu);
			}
		});

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.actionLouer(Integer.parseInt(textFieldId.getText()), Integer.parseInt(textFieldMdp.getText()));
			}
		});

		
	}
	
	public void afficherErreur() {
		labelErreur.setText("Erreur : nombre d'essais écoulés");
		textFieldId.setEnabled(false);
		textFieldMdp.setEnabled(false);
	}

	public void afficherErreurEssai() {
		labelErreur.setText("Erreur : mot de passe incorrect");
	}

}
