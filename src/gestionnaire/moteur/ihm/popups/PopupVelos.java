package gestionnaire.moteur.ihm.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;


public class PopupVelos extends JDialog {
	private StationBD station;
	private JPanel panelNord,panelCentre,panelSud,contentPane;
	private BorderLayout layout ;
	private FlowLayout layoutSud ;
	private JScrollPane planning ;
	private TableVelos donneesVelos;
	private JLabel lblPlacesPrises;
	
	public PopupVelos(StationBD s) {
		station = s ;
		
		// Récupération du design hôte de la machine
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		// Paramètre de la fenêtre
		setTitle("Station n°" + station.getId() + " - Vélos"); 
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
		donneesVelos = new TableVelos(s);
		planning = new JScrollPane(new JTable(donneesVelos));
		
		panelCentre.add(planning);	
		contentPane.add(panelNord,BorderLayout.NORTH);
		
		// Code généré automatiquement
		JLabel lblStation = new JLabel("Station num. " + s.getId() + " : ");
		lblStation.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLat = new JLabel("Latitude : " + s.getLat());
		
		JLabel lblLon = new JLabel("Longitude : " + s.getLon());
		
		JLabel lblNbPlaces = new JLabel("Nombre de places : " + s.getNbPlace());
		
		lblPlacesPrises = new JLabel("Places prises : " + s.getVelosStation().size());
		
		JLabel lblVelos = new JLabel("Velos :");
		
		
		lblVelos.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panelNord = new GroupLayout(panelNord);
		gl_panelNord.setHorizontalGroup(
			gl_panelNord.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStation)
						.addGroup(gl_panelNord.createSequentialGroup()
							.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLat)
								.addComponent(lblNbPlaces))
							.addGap(57)
							.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlacesPrises)
								.addComponent(lblLon)))
						.addComponent(lblVelos))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		gl_panelNord.setVerticalGroup(
			gl_panelNord.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblStation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLat)
						.addComponent(lblLon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNbPlaces)
						.addComponent(lblPlacesPrises))
					.addGap(35)
					.addComponent(lblVelos)
					.addGap(4))
		);
		panelNord.setLayout(gl_panelNord);
		contentPane.add(panelCentre,BorderLayout.CENTER);
		contentPane.add(panelSud,BorderLayout.SOUTH);
	}
	
	// Classe qui définit le modèle pour la JTable
	class TableVelos extends AbstractTableModel {
		private ArrayList<Velo> velos ;
		private String[] index = {"Numéro","Etat"};

		public TableVelos(StationBD s) {
			super();
			velos = s.getVelosStation();
		}
		
		public int getColumnCount() {
			return index.length ;
		}

		public int getRowCount() {
			return velos.size();
		}

		public Object getValueAt(int ligne, int colonne) {
			Velo v = velos.get(ligne);
			
			switch(colonne) {
				case 0 :
					return v.getId();
				case 1 :
					return v.getEnumEtat();
				default:
					return null;
			}
		}
		
	    public String getColumnName(int columnIndex) {
	        return index[columnIndex];
	    }
	    
		public void majTable(ArrayList<Velo> vls) {
			velos = vls;
			fireTableDataChanged();
		}

	}
	
	public void rechargerTableau(ArrayList<Velo> nvxVelos) {
		donneesVelos.majTable(nvxVelos);
		lblPlacesPrises.setText("Places prises : " + nvxVelos.size());
	}
	
	public int getIdStation() {
		return station.getId();
	}

}

