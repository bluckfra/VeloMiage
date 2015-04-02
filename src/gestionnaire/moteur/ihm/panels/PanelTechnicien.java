package gestionnaire.moteur.ihm.panels;

import gestionnaire.moteur.ihm.GestionnaireIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.EtatVelo;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

import javax.swing.JButton;

import gestionnaire.moteur.ihm.TechnicienIHM;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelTechnicien extends JPanel {
	private JPanel courant = this;
	private JPanel panelBox, panelCenter; 
	private final JLabel lblListeDesVelos = new JLabel("Vos informations");
	private TechnicienIHM modele;

	public PanelTechnicien (TechnicienIHM tech) {
		modele = tech;
		panelBox= new JPanel();
		panelBox.setBounds(0, 0, 450, 24);
		panelCenter = new JPanel();
		setLayout(null);
				
		this.add(panelBox);
		lblListeDesVelos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeDesVelos.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesVelos)
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesVelos)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(25, 97, 74, 14);
		add(lblIdentifiant);
		
		JLabel lblNewLabel = new JLabel("Nombre de v\u00E9los dont vous devez vous occuper :");
		lblNewLabel.setBounds(25, 122, 244, 36);
		add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(180, 229, 89, 23);
		add(btnQuitter);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(95, 97, 46, 14);
		add(lblNewLabel_1);

	}
}

