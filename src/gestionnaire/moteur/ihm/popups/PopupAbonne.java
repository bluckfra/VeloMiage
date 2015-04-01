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

import bdd.objetsbdd.Abonne;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;


public class PopupAbonne extends JDialog {
/*	private Abonne abonne;
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
		donneesVelos = null;//new TableVelos(abo);
		planning = new JScrollPane(new JTable(donneesVelos));
		
		panelCentre.add(planning);	
		contentPane.add(panelNord,BorderLayout.NORTH);
		
		// Code généré automatiquement
		JLabel lblStation = new JLabel("Abonné num. " + abo.getId() + " : ");
		lblStation.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblDebutAbo = new JLabel("Date début abonnement : " + abo.getDateAboDebut().toGMTString());
		JButton boutonAbsence = new JButton("Indiquer vélo défectueux");
		boutonAbsence.setEnabled(false);
		
		boutonAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblFinAbo = new JLabel("Date début abonnement : " + abo.getDateAboFin().toGMTString());
		
		JLabel lblCode = new JLabel("Code secret : " + abo.getCode());
		
		JLabel lblLocations = new JLabel("Locations effectu\u00E9es :");
		
		
		lblLocations.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnDemanderRotationVlo = new JButton("Demander rotation v\u00E9lo");
		GroupLayout gl_panelNord = new GroupLayout(panelNord);
		gl_panelNord.setHorizontalGroup(
			gl_panelNord.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStation)
						.addGroup(gl_panelNord.createSequentialGroup()
							.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDebutAbo)
								.addComponent(lblCode))
							.addGap(57)
							.addComponent(lblFinAbo))
						.addComponent(lblLocations)
						.addGroup(gl_panelNord.createSequentialGroup()
							.addComponent(boutonAbsence)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDemanderRotationVlo)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panelNord.setVerticalGroup(
			gl_panelNord.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblStation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDebutAbo)
						.addComponent(lblFinAbo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(boutonAbsence)
						.addComponent(btnDemanderRotationVlo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLocations)
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
					return v.getEtat();
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
	
	public int getIdAbonne() {
		return abonne.getId();
	}
*/
}

