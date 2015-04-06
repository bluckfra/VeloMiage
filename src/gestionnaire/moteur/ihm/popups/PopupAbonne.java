package gestionnaire.moteur.ihm.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.AbstractTableModel;

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;
import bdd.objetsdao.Statistiques;


public class PopupAbonne extends JDialog {
	private Abonne abonne;
	private JPanel panelNord,panelCentre,panelSud,contentPane;
	private BorderLayout layout ;
	private FlowLayout layoutSud ;
	private JScrollPane planning ;
	private TableVelos donneesVelos;
	
	public PopupAbonne(Abonne abo) {
		abonne = abo ;
		
		// Récupération du design hôte de la machine
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		// Paramètre de la fenêtre
		setTitle("Abonné n°" + abonne.getId() + " - Statistiques"); 
		setSize(450,400); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);

		// Déclaration des panneaux du BorderLayout
		panelCentre = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		contentPane = new JPanel();
		
		// Modification du JPanel
		layout = new BorderLayout() ;
		contentPane.setLayout(layout);
		this.setContentPane(contentPane);
		
		
		// création du bas de la page
		layoutSud = new FlowLayout();
		panelSud.setLayout(layoutSud);
		panelCentre.setLayout(new GridLayout(0, 1, 0, 0));
		donneesVelos = new TableVelos(Statistiques.getHistoriqueLocationsAbonne(abo.getId()));
		planning = new JScrollPane(new JTable(donneesVelos));
		
		panelCentre.add(planning);	
		contentPane.add(panelNord,BorderLayout.NORTH);
		
		// Code généré automatiquement
		JLabel lblStation = new JLabel("Abonné num. " + abo.getId() + " : ");
		lblStation.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblDebutAbo = new JLabel("Date début abonnement : " + abo.getDateAboDebut().toGMTString());
		
		JLabel lblFinAbo = new JLabel("Date fin abonnement : " + abo.getDateAboFin().toGMTString());
		
		JLabel lblCode = new JLabel("Code secret : " + abo.getCode());
		
		JLabel lblLocations = new JLabel("Locations effectu\u00E9es :");
		
		
		lblLocations.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panelNord = new GroupLayout(panelNord);
		gl_panelNord.setHorizontalGroup(
			gl_panelNord.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStation)
						.addGroup(gl_panelNord.createSequentialGroup()
							.addComponent(lblDebutAbo)
							.addGap(57)
							.addComponent(lblCode))
						.addComponent(lblLocations)
						.addComponent(lblFinAbo))
					.addContainerGap(172, Short.MAX_VALUE))
		);
		gl_panelNord.setVerticalGroup(
			gl_panelNord.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblStation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDebutAbo)
						.addComponent(lblCode))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFinAbo)
					.addGap(35)
					.addComponent(lblLocations)
					.addGap(4))
		);
		panelNord.setLayout(gl_panelNord);
		contentPane.add(panelCentre,BorderLayout.CENTER);
		contentPane.add(panelSud,BorderLayout.SOUTH);
	}
	
	// Classe qui définit le modèle pour la JTable
	class TableVelos extends AbstractTableModel {
		private ArrayList<Object[]> velos ;
		private String[] index = {"Id vélo","Date début location","Date fin location"};

		public TableVelos(ArrayList<Object[]> vs) {
			super();
			velos = vs;
		}
		
		public int getColumnCount() {
			return index.length ;
		}

		public int getRowCount() {
			return velos.size();
		}

		public Object getValueAt(int ligne, int colonne) {
			Object[] v = velos.get(ligne);
			
			switch(colonne) {
				case 0 :
					return (Integer) v[0];
				case 1 :
					return ((Timestamp) v[1]).toGMTString();
				case 2 :
					return ((Timestamp) v[2]).toGMTString();
				default:
					return null;
			}
		}
		
	    public String getColumnName(int columnIndex) {
	        return index[columnIndex];
	    }
	    
		public void majTable(ArrayList<Object[]> vls) {
			velos = vls;
			fireTableDataChanged();
		}

	}
	
	public void rechargerTableau(ArrayList<Object[]> nvxVelos) {
		donneesVelos.majTable(nvxVelos);
	}
	
	public int getIdAbonne() {
		return abonne.getId();
	}

}