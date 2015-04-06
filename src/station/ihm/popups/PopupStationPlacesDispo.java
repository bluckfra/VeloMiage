package station.ihm.popups;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PopupStationPlacesDispo extends JDialog{
	public PopupStationPlacesDispo(Object[] sta) {
		setTitle("Veuillez-vous diriger vers l'une des stations suivante"); 
		setSize(788,346); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		
		getContentPane().setLayout(null);
		
		JLabel lbl_titre = new JLabel("Station la plus proche pour " + sta[8] + " un v\u00E9lo !");
		lbl_titre.setBounds(10, 11, 236, 33);
		getContentPane().add(lbl_titre);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(268, 272, 89, 23);
		getContentPane().add(btnQuitter);
		
		JLabel lblStation = new JLabel("Station:");
		lblStation.setBounds(10, 50, 46, 14);
		getContentPane().add(lblStation);
		
		JLabel lblCoordonnes = new JLabel("Coordonn\u00E9es:");
		lblCoordonnes.setBounds(10, 71, 78, 14);
		getContentPane().add(lblCoordonnes);
		
		JLabel lblLattitude = new JLabel("Lattitude:");
		lblLattitude.setBounds(30, 96, 57, 14);
		getContentPane().add(lblLattitude);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(30, 121, 64, 14);
		getContentPane().add(lblLongitude);
		
		JLabel lbl_IDStation = new JLabel("" + sta[0]);
		lbl_IDStation.setBounds(70, 50, 46, 14);
		getContentPane().add(lbl_IDStation);
		
		JLabel lbl_lati = new JLabel("" + sta[1]);
		lbl_lati.setBounds(86, 96, 83, 14);
		getContentPane().add(lbl_lati);
		
		JLabel lbl_longi = new JLabel(""+ sta[2]);
		lbl_longi.setBounds(86, 121, 83, 14);
		getContentPane().add(lbl_longi);
		
		JLabel lblDistancekm = new JLabel("Distance (km):");
		lblDistancekm.setBounds(10, 140, 78, 14);
		getContentPane().add(lblDistancekm);
		
		JLabel lblTempsPied = new JLabel("Temps \u00E0 pied:");
		lblTempsPied.setBounds(10, 160, 78, 14);
		getContentPane().add(lblTempsPied);
		
		JLabel lblTempsEnVlo = new JLabel("Temps en v\u00E9lo:");
		lblTempsEnVlo.setBounds(10, 180, 78, 14);
		getContentPane().add(lblTempsEnVlo);
		
		JLabel lbl_dist_km = new JLabel("" + sta[3]+ " km");
		lbl_dist_km.setBounds(85, 140, 46, 14);
		getContentPane().add(lbl_dist_km);
		
		JLabel lbl_tps_pied = new JLabel("" + sta[4] + " min");
		lbl_tps_pied.setBounds(91, 160, 60, 14);
		getContentPane().add(lbl_tps_pied);
		
		JLabel lbl_tps_velo = new JLabel("" + sta[5] +" min");
		lbl_tps_velo.setBounds(91, 180, 60, 14);
		getContentPane().add(lbl_tps_velo);
		
		JLabel lbl_titre_st2 = new JLabel("2i\u00E8me station la plus proche pour " + sta[8] + " un v\u00E9lo !");
		lbl_titre_st2.setBounds(243, 10, 270, 33);
		getContentPane().add(lbl_titre_st2);
		
		JLabel lblTpsVeloST2 = new JLabel("Temps en v\u00E9lo:");
		lblTpsVeloST2.setBounds(243, 180, 78, 14);
		getContentPane().add(lblTpsVeloST2);
		
		JLabel lblTpsPiedST2 = new JLabel("Temps \u00E0 pied:");
		lblTpsPiedST2.setBounds(243, 160, 78, 14);
		getContentPane().add(lblTpsPiedST2);
		
		JLabel lblDistST2 = new JLabel("Distance :");
		lblDistST2.setBounds(243, 140, 78, 14);
		getContentPane().add(lblDistST2);
		
		JLabel lblIdStation2 = new JLabel("Station:");
		lblIdStation2.setBounds(243, 50, 46, 14);
		getContentPane().add(lblIdStation2);
		
		JLabel lbl_idSt2 = new JLabel("" + sta[9]);
		lbl_idSt2.setBounds(299, 50, 46, 14);
		getContentPane().add(lbl_idSt2);
		
		JLabel lbl_distST2 = new JLabel("" + sta[12] +" km");
		lbl_distST2.setBounds(324, 139, 46, 14);
		getContentPane().add(lbl_distST2);
		
		JLabel lbl_tpsPiedST2 = new JLabel("" + sta[13] + " min");
		lbl_tpsPiedST2.setBounds(324, 160, 60, 14);
		getContentPane().add(lbl_tpsPiedST2);
		
		JLabel lbl_tpsVeloST2 = new JLabel("" + sta[14] + " min");
		lbl_tpsVeloST2.setBounds(331, 180, 60, 14);
		getContentPane().add(lbl_tpsVeloST2);
		
		JLabel lblCoordSt2 = new JLabel("Coordonn\u00E9es:");
		lblCoordSt2.setBounds(243, 70, 78, 14);
		getContentPane().add(lblCoordSt2);
		
		JLabel lblLatST2 = new JLabel("Lattitude:");
		lblLatST2.setBounds(263, 96, 57, 14);
		getContentPane().add(lblLatST2);
		
		JLabel lbl_latST2 = new JLabel("" + sta[10]);
		lbl_latST2.setBounds(319, 96, 83, 14);
		getContentPane().add(lbl_latST2);
		
		JLabel lbl_lonST2 = new JLabel("" + sta[11]);
		lbl_lonST2.setBounds(320, 121, 83, 14);
		getContentPane().add(lbl_lonST2);
		
		JLabel lblLonST2 = new JLabel("Longitude:");
		lblLonST2.setBounds(263, 121, 64, 14);
		getContentPane().add(lblLonST2);
		
		JLabel lblVoiciLaime = new JLabel("3i\u00E8me station la plus proche pour " + sta[8] + " un v\u00E9lo !");
		lblVoiciLaime.setBounds(523, 11, 270, 33);
		getContentPane().add(lblVoiciLaime);
		
		JLabel lblIDST3 = new JLabel("Station:");
		lblIDST3.setBounds(523, 50, 46, 14);
		getContentPane().add(lblIDST3);
		
		JLabel lbl_idST3 = new JLabel("" + sta[18]);
		lbl_idST3.setBounds(579, 50, 46, 14);
		getContentPane().add(lbl_idST3);
		
		JLabel lblCoordST3 = new JLabel("Coordonn\u00E9es:");
		lblCoordST3.setBounds(523, 70, 78, 14);
		getContentPane().add(lblCoordST3);
		
		JLabel lblLatST3 = new JLabel("Lattitude:");
		lblLatST3.setBounds(543, 96, 57, 14);
		getContentPane().add(lblLatST3);
		
		JLabel lbl_latST3 = new JLabel("" + sta[19]);
		lbl_latST3.setBounds(599, 96, 83, 14);
		getContentPane().add(lbl_latST3);
		
		JLabel lbl_lonST3 = new JLabel("" + sta[20]);
		lbl_lonST3.setBounds(604, 121, 83, 14);
		getContentPane().add(lbl_lonST3);
		
		JLabel lblLonST3 = new JLabel("Longitude:");
		lblLonST3.setBounds(543, 121, 64, 14);
		getContentPane().add(lblLonST3);
		
		JLabel lblDistST3 = new JLabel("Distance :");
		lblDistST3.setBounds(523, 140, 78, 14);
		getContentPane().add(lblDistST3);
		
		JLabel lbl_distST3 = new JLabel("" + sta[21] + " km");
		lbl_distST3.setBounds(600, 140, 46, 14);
		getContentPane().add(lbl_distST3);
		
		JLabel lblTpsPiedST3 = new JLabel("Temps \u00E0 pied:");
		lblTpsPiedST3.setBounds(523, 160, 78, 14);
		getContentPane().add(lblTpsPiedST3);
		
		JLabel lbl_tpsPiedST3 = new JLabel("" + sta[22] + " min");
		lbl_tpsPiedST3.setBounds(604, 160, 60, 14);
		getContentPane().add(lbl_tpsPiedST3);
		
		JLabel lbl_tpsVeloST3 = new JLabel("" + sta[23] + " min");
		lbl_tpsVeloST3.setBounds(611, 180, 60, 14);
		getContentPane().add(lbl_tpsVeloST3);
		
		JLabel lblTpsVeloST3 = new JLabel("Temps en v\u00E9lo:");
		lblTpsVeloST3.setBounds(523, 180, 78, 14);
		getContentPane().add(lblTpsVeloST3);
		
		JLabel lblNombreVloDispo = new JLabel("Nombre v\u00E9lo dispo:");
		lblNombreVloDispo.setBounds(10, 200, 102, 14);
		getContentPane().add(lblNombreVloDispo);
		
		JLabel lblNombreDePlace = new JLabel("Nombre de place total:");
		lblNombreDePlace.setBounds(10, 220, 117, 14);
		getContentPane().add(lblNombreDePlace);
		
		JLabel lbl_nbVeloMax = new JLabel("" + sta[7]);
		lbl_nbVeloMax.setBounds(130, 220, 46, 14);
		getContentPane().add(lbl_nbVeloMax);
		
		JLabel lbl_nbVeloDispo = new JLabel("" + sta[6]);
		lbl_nbVeloDispo.setBounds(115, 200, 46, 14);
		getContentPane().add(lbl_nbVeloDispo);
		
		JLabel lblNBVeloDiST2 = new JLabel("Nombre v\u00E9lo dispo:");
		lblNBVeloDiST2.setBounds(240, 200, 102, 14);
		getContentPane().add(lblNBVeloDiST2);
		
		JLabel lblNBMAXST2 = new JLabel("Nombre de place total:");
		lblNBMAXST2.setBounds(240, 220, 117, 14);
		getContentPane().add(lblNBMAXST2);
		
		JLabel lblVDIST3 = new JLabel("Nombre v\u00E9lo dispo:");
		lblVDIST3.setBounds(523, 200, 102, 14);
		getContentPane().add(lblVDIST3);
		
		JLabel lblNBMAXST3 = new JLabel("Nombre de place total:");
		lblNBMAXST3.setBounds(523, 220, 117, 14);
		getContentPane().add(lblNBMAXST3);
		
		JLabel lbl_nbVeloDiST2 = new JLabel("" + sta[15]);
		lbl_nbVeloDiST2.setBounds(345, 200, 46, 14);
		getContentPane().add(lbl_nbVeloDiST2);
		
		JLabel lbl_nbMAxST2 = new JLabel("" + sta[16]);
		lbl_nbMAxST2.setBounds(355, 220, 46, 14);
		getContentPane().add(lbl_nbMAxST2);
		
		JLabel lbl_nbDispoST3 = new JLabel("" + sta[24]);
		lbl_nbDispoST3.setBounds(621, 200, 46, 14);
		getContentPane().add(lbl_nbDispoST3);
		
		JLabel lbl_nbMAxST3 = new JLabel("" + sta[25]);
		lbl_nbMAxST3.setBounds(641, 220, 46, 14);
		getContentPane().add(lbl_nbMAxST3);
	}
}
