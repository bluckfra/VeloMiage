package gestionnaire.moteur.ihm.panels;

import gestionnaire.moteur.ihm.GestionnaireIHM;

import java.awt.BorderLayout;
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

import bdd.objetsbdd.StationBD;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelStations extends JPanel {
	private JPanel courant = this;
	private JPanel panelBox, panelCenter; 
	private BorderLayout layout ;
	private JTable tableauStations ;
	private ArrayList<StationBD> lStations;
	private final JLabel lblListeDesAvions = new JLabel("Liste des stations :\r\n");
	private JPanel panelNorth;
	private JButton btnVoirStation;
	private GestionnaireIHM ihm;
	private StationBD stationCourante;


	public PanelStations(ArrayList<StationBD> lA, GestionnaireIHM g) {
		lStations = lA ;
		ihm = g;
		panelBox= new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		
		// Cr�ation du layout
		this.setLayout(layout);
				
		this.add(panelBox,BorderLayout.NORTH);
		lblListeDesAvions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeDesAvions.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAvions)
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAvions)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
				
		
		panelNorth = new JPanel();
		add(panelNorth, BorderLayout.SOUTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnVoirStation = new JButton("D\u00E9tails station");
		btnVoirStation.setEnabled(false);
		panelNorth.add(btnVoirStation);
		
		/* cr?ation du tableau  */
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));
		this.add(panelCenter,BorderLayout.CENTER);
		tableauStations = new JTable(new TableStations(lA));
		tableauStations.setAutoCreateRowSorter(true);
		
		tableauStations.setToolTipText("");
		ListSelectionModel listSelectionModel = tableauStations.getSelectionModel();        
		JScrollPane scrollPane = new JScrollPane(tableauStations);
		add(scrollPane, BorderLayout.CENTER);
		listSelectionModel.addListSelectionListener(new ControleurTable());

		btnVoirStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ihm.actionAfficherDetaisStation(stationCourante);
			}
		});
	}
	
	
	// Mod�le pour la JTable
	private class TableStations extends AbstractTableModel {
		private ArrayList<StationBD> stations ;
		private String index[] =  {"Id Station","Nombre de places","Places occup�es"};
		
		public TableStations(ArrayList<StationBD> lStations) {
			super();
			stations = lStations ;
		}
		
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return stations.size();
		}
		
		public StationBD getStation(int ligne) {
			return stations.get(ligne);
		}
		
		public Object getValueAt(int ligne, int colonne) {
			StationBD a = stations.get(ligne);
			switch (colonne) {
			case 0:
				return a.getId();
			case 1:
				return a.getNbPlace();
			case 2:
				return a.getVelosStation().size();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	// Controleur qui r�cup�re la ligne s�lectionn�e
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	stationCourante = lStations.get(tableauStations.convertRowIndexToModel(tableauStations.getSelectedRow()));
	        	btnVoirStation.setEnabled(true);
	        }			
		}
	}	
	
}
