package gestionnaire.moteur.ihm.panels;

import gestionnaire.moteur.ihm.GestionnaireIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

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
	private TechnicienIHM modele;
	private JTable table;
	private TableStations donneesTable;
	private JTable table_1;

	public PanelTechnicien (HashMap<Integer,String> hmStation, TechnicienIHM tech) {
		modele = tech;
		panelBox= new JPanel();
		panelBox.setBounds(0, 0, 450, 24);
		panelCenter = new JPanel();
		setLayout(null);
				
		this.add(panelBox);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGap(0, 24, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel = new JLabel("Stations dont vous devez vous occuper :");
		lblNewLabel.setBounds(24, 35, 244, 36);
		add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(174, 235, 89, 23);
		add(btnQuitter);
		
		JLabel lbl_idTech = new JLabel("" );
		lbl_idTech.setBounds(95, 35, 46, 14);
		add(lbl_idTech);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 219, 369, -135);
		add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setBounds(34, 82, 1, 1);
		add(table_1);
		
		
		table.setToolTipText("");
		ListSelectionModel listSelectionModel = table.getSelectionModel();   
		JScrollPane scrollPane = new JScrollPane(table);


	}
	
	// Modèle pour la JTable
		private class TableStations extends AbstractTableModel {
			private HashMap<StationBD,String> stations ;
			private String index[] =  {"Id Station","Nombre de places","Places occupées"};
			
			public TableStations(HashMap<StationBD,String> lStations) {
				super();
				stations = lStations ;
			}
			
			
			public int getColumnCount() {
				return index.length;
			}

			public int getRowCount() {
				return stations.size();
			}
			
			
			public Object getValueAt(int ligne, int colonne) {
				//StationBD a = stations.get(key);
				switch (colonne) {
				/*case 0:
					return a.getId();
				case 1:
					return a.getNbPlace();
				case 2:
					return a.getVelosStation().size();*/
				default:
					return null;
				}
			}
			
			public String getColumnName(int colonne) {
		        return index[colonne];
		    }
			
			public void majTable(HashMap<StationBD,String> st) {
				stations = st;
				fireTableDataChanged();
			}
		}
		
		public void rechargerTableau(HashMap<StationBD,String> nouvellesStations) {
			donneesTable.majTable(nouvellesStations);
		}
}

