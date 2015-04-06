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

import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVelos extends JPanel {
	private JPanel courant = this;
	private JPanel panelBox, panelCenter; 
	private BorderLayout layout ;
	private JTable tableauStations ;
	private ArrayList<Velo> lVelos;
	private final JLabel lblListeDesVelos = new JLabel("Liste des velos :\r\n");
	private JPanel panelNorth;
	private GestionnaireIHM ihm;
	private Velo veloCourant;
	private TableVelo donneesTable;


	public PanelVelos(ArrayList<Velo> lV, GestionnaireIHM g) {
		lVelos = lV ;
		ihm = g;
		panelBox= new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		
		// Création du layout
		this.setLayout(layout);
				
		this.add(panelBox,BorderLayout.NORTH);
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
		panelBox.setLayout(gl_panelBox);
				
		
		panelNorth = new JPanel();
		add(panelNorth, BorderLayout.SOUTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/* cr?ation du tableau  */
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));
		this.add(panelCenter,BorderLayout.CENTER);
		donneesTable = new TableVelo(lV);
		tableauStations = new JTable(donneesTable);
		tableauStations.setAutoCreateRowSorter(true);
		
		tableauStations.setToolTipText("");
		ListSelectionModel listSelectionModel = tableauStations.getSelectionModel();        
		JScrollPane scrollPane = new JScrollPane(tableauStations);
		add(scrollPane, BorderLayout.CENTER);
		listSelectionModel.addListSelectionListener(new ControleurTable());
	}
	
	
	// Modèle pour la JTable
	private class TableVelo extends AbstractTableModel {
		private ArrayList<Velo> velos ;
		private String index[] =  {"Id Vélo","Etat","Station actuelle","Loueur actuel"};
		
		public TableVelo(ArrayList<Velo> lVelos) {
			super();
			velos = lVelos ;
		}
		
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return velos.size();
		}
		
		public Velo getStation(int ligne) {
			return velos.get(ligne);
		}
		
		public Object getValueAt(int ligne, int colonne) {
			Velo a = velos.get(ligne);
			switch (colonne) {
			case 0:
				return a.getId();
			case 1:
				return a.getEnumEtat();
			case 2:
				if (a.getEtat() == 2) {
					return a.getStationCourante();
				} else return "Aucune";
			case 3:
				if (a.getEtat() == 1) {
					return a.getAbonneCourant();
				} else return "Aucun";
				
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
		
		public void majTable(ArrayList<Velo> vs) {
			velos = vs;
			fireTableDataChanged();
		}
	}
	
	// Controleur qui récupère la ligne sélectionnée
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	//veloCourant = lVelos.get(tableauStations.convertRowIndexToModel(tableauStations.getSelectedRow()));
	        }			
		}
	}	
	
	/* test
	private class StationsRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			StationBD stCast = (StationBD) value ;
			if ((StationBD) value.) {
				cell.setBackground(Color.red);
			} else {
				if (tableauStations.getSelectedRow() == row) {
					cell.setBackground(new Color(51,151,255));
				} else cell.setBackground(Color.white);
			}
			
			
			return cell;
		}

	}*/
	
	
	public void rechargerTableau(ArrayList<Velo> nvVelos) {
		donneesTable.majTable(nvVelos);
	}
}
