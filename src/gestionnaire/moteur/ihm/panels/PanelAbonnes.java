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
import bdd.objetsbdd.StationBD;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAbonnes extends JPanel {
	private JPanel courant = this;
	private JPanel panelBox, panelCenter; 
	private BorderLayout layout ;
	private JTable tableauAbos ;
	private ArrayList<Abonne> lAbonnes;
	private JLabel lblListeDesStations;
	private JPanel panelNorth;
	private JButton btnVoirAbonne;
	private GestionnaireIHM ihm;
	private Abonne abonneCourant;
	private TableAbonnes donneesTable;


	public PanelAbonnes(ArrayList<Abonne> lA, GestionnaireIHM g) {
		lAbonnes = lA ;
		ihm = g;
		panelBox= new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		
		// Création du layout
		this.setLayout(layout);
				
		this.add(panelBox,BorderLayout.NORTH);
		lblListeDesStations = new JLabel("Liste des abonnés :\r\n");
		lblListeDesStations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeDesStations.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesStations)
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesStations)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
				
		
		panelNorth = new JPanel();
		add(panelNorth, BorderLayout.SOUTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnVoirAbonne = new JButton("D\u00E9tails abonné");
		btnVoirAbonne.setEnabled(false);
		panelNorth.add(btnVoirAbonne);
		
		/* cr?ation du tableau  */
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));
		this.add(panelCenter,BorderLayout.CENTER);
		donneesTable = new TableAbonnes(lA);
		tableauAbos = new JTable(donneesTable);
		tableauAbos.setAutoCreateRowSorter(true);
		
		tableauAbos.setToolTipText("");
		ListSelectionModel listSelectionModel = tableauAbos.getSelectionModel();        
		JScrollPane scrollPane = new JScrollPane(tableauAbos);
		add(scrollPane, BorderLayout.CENTER);
		listSelectionModel.addListSelectionListener(new ControleurTable());

		btnVoirAbonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ihm.actionAfficherDetailsAbonne(abonneCourant.getId());
			}
		});
	}
	
	
	// Modèle pour la JTable
	private class TableAbonnes extends AbstractTableModel {
		private ArrayList<Abonne> abos ;
		private String index[] =  {"Id Abonne","Code secret","Velo en location","Début abonnement","Fin abonnement","Technicien"};
		
		public TableAbonnes(ArrayList<Abonne> lStations) {
			super();
			abos = lStations ;
		}
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return abos.size();
		}
		
		public Abonne getAbonne(int ligne) {
			return abos.get(ligne);
		}
		
		public Object getValueAt(int ligne, int colonne) {
			Abonne a = abos.get(ligne);
			switch (colonne) {
			case 0:
				return a.getId();
			case 1:
				return a.getCode();
			case 2:
				return (a.hasVelo() ? a.getVeloCourant() : "Aucun");
			case 3:
				return a.getDateAboDebut();
			case 4:
				return a.getDateAboFin();
			case 5:
				return (a.isTechnicien() ? "Oui" : "Non");
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
		
		public void majTable(ArrayList<Abonne> as) {
			abos = as;
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
	        	abonneCourant = lAbonnes.get(tableauAbos.convertRowIndexToModel(tableauAbos.getSelectedRow()));
	        	btnVoirAbonne.setEnabled(true);
	        }			
		}
	}		
	
	public void rechargerTableau(ArrayList<Abonne> nouveauxAbos) {
		donneesTable.majTable(nouveauxAbos);
	}
}
