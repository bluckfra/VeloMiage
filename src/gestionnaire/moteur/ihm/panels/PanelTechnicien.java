package gestionnaire.moteur.ihm.panels;

import gestionnaire.moteur.ihm.GestionnaireIHM;
import gestionnaire.moteur.ihm.TechnicienIHM;

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
import bdd.objetsbdd.StationBD;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelTechnicien extends JPanel {
	private JPanel courant = this;
	private JPanel panelBox, panelCenter; 
	private BorderLayout layout ;
	private JTable tableauStations ;
	private HashMap<Integer, String> lStations;
	private JLabel lblListeDesStations ;
	private JPanel panelNorth;
	private TechnicienIHM ihm;
	private StationBD stationCourante;
	private TableStations donneesTable;


	public PanelTechnicien(Abonne a,HashMap<Integer,String> lA, TechnicienIHM g) {
		lStations = lA ;
		ihm = g;
		panelBox= new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		
		// Création du layout
		this.setLayout(layout);
				
		this.add(panelBox,BorderLayout.NORTH);
		lblListeDesStations = new JLabel("Liste des stations à traiter :\r\n");
		lblListeDesStations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeDesStations.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblIdentifiant = new JLabel("Identifiant : " + a.getId());
		
		JLabel lblCodeSecret = new JLabel("Code secret : " + a.getCode());
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBox.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblListeDesStations)
						.addGroup(gl_panelBox.createSequentialGroup()
							.addComponent(lblIdentifiant)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblCodeSecret)))
					.addContainerGap(285, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelBox.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelBox.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentifiant)
						.addComponent(lblCodeSecret))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListeDesStations)
					.addContainerGap())
		);
		panelBox.setLayout(gl_panelBox);
				
		
		panelNorth = new JPanel();
		add(panelNorth, BorderLayout.SOUTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/* cr?ation du tableau  */
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));
		this.add(panelCenter,BorderLayout.CENTER);
		donneesTable = new TableStations(lA);
		tableauStations = new JTable(donneesTable);
		tableauStations.setAutoCreateRowSorter(true);
		
		tableauStations.setToolTipText("");
		ListSelectionModel listSelectionModel = tableauStations.getSelectionModel();        
		JScrollPane scrollPane = new JScrollPane(tableauStations);
		add(scrollPane, BorderLayout.CENTER);
		//listSelectionModel.addListSelectionListener(new ControleurTable());
	}
	
	
	private class TableStations extends AbstractTableModel {
		private HashMap<Integer,String> stations ;
		private ArrayList<Integer> idStations;
		private String index[] =  {"Id Station","Message"};
		
		public TableStations(HashMap<Integer,String> lStations) {
			super();
			stations = lStations ;
			idStations = new ArrayList<Integer>(stations.keySet());
		}
		
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return stations.size();
		}
		
		
		public Object getValueAt(int ligne, int colonne) {
			int s = idStations.get(ligne);
			switch (colonne) {
			case 0:
				return s;
			case 1:
				return stations.get(s);
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
		
		public void majTable(HashMap<Integer,String> st) {
			stations = st;
			idStations = new ArrayList<Integer>(stations.keySet());
			fireTableDataChanged();
		}
	}
	
	public void rechargerTableau(HashMap<Integer,String> nouvellesStations) {
		donneesTable.majTable(nouvellesStations);
	}
	
	// Controleur qui récupère la ligne sélectionnée
	/*private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	stationCourante = lStations.get(tableauStations.convertRowIndexToModel(tableauStations.getSelectedRow()));
	        }			
		}
	}	*/	
}

	

