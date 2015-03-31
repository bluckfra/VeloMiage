package station.moteur.ihm.popups;

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
		
		JLabel lbl_titre = new JLabel("Voici la station la plus proche pour " + sta[3] + " un vélo !");
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
		lblStation.setBounds(10, 46, 46, 14);
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
		lbl_IDStation.setBounds(66, 46, 46, 14);
		getContentPane().add(lbl_IDStation);
		
		JLabel lbl_lati = new JLabel("" + sta[1]);
		lbl_lati.setBounds(86, 96, 46, 14);
		getContentPane().add(lbl_lati);
		
		JLabel lbl_longi = new JLabel(""+ sta[2]);
		lbl_longi.setBounds(91, 121, 46, 14);
		getContentPane().add(lbl_longi);
		
		JLabel lblDistancekm = new JLabel("Distance (km):");
		lblDistancekm.setBounds(10, 139, 78, 14);
		getContentPane().add(lblDistancekm);
		
		JLabel lblTempsPied = new JLabel("Temps \u00E0 pied:");
		lblTempsPied.setBounds(10, 167, 78, 14);
		getContentPane().add(lblTempsPied);
		
		JLabel lblTempsEnVlo = new JLabel("Temps en v\u00E9lo:");
		lblTempsEnVlo.setBounds(10, 192, 78, 14);
		getContentPane().add(lblTempsEnVlo);
		
		JLabel lbl_dist_km = new JLabel("");
		lbl_dist_km.setBounds(81, 143, 46, 14);
		getContentPane().add(lbl_dist_km);
		
		JLabel lbl_tps_pied = new JLabel("");
		lbl_tps_pied.setBounds(91, 167, 46, 14);
		getContentPane().add(lbl_tps_pied);
		
		JLabel lbl_tps_velo = new JLabel("");
		lbl_tps_velo.setBounds(98, 191, 46, 14);
		getContentPane().add(lbl_tps_velo);
		
		JLabel lbl_titre_st2 = new JLabel("Voici la 2i\u00E8me station la plus proche pour null un v\u00E9lo !");
		lbl_titre_st2.setBounds(243, 10, 270, 33);
		getContentPane().add(lbl_titre_st2);
		
		JLabel lblTpsVeloST2 = new JLabel("Temps en v\u00E9lo:");
		lblTpsVeloST2.setBounds(243, 192, 78, 14);
		getContentPane().add(lblTpsVeloST2);
		
		JLabel lblTpsPiedST2 = new JLabel("Temps \u00E0 pied:");
		lblTpsPiedST2.setBounds(243, 167, 78, 14);
		getContentPane().add(lblTpsPiedST2);
		
		JLabel lblDistST2 = new JLabel("Distance (km):");
		lblDistST2.setBounds(243, 141, 78, 14);
		getContentPane().add(lblDistST2);
		
		JLabel lblIdStation2 = new JLabel("Station:");
		lblIdStation2.setBounds(243, 45, 46, 14);
		getContentPane().add(lblIdStation2);
		
		JLabel lbl_idSt2 = new JLabel("null");
		lbl_idSt2.setBounds(299, 45, 46, 14);
		getContentPane().add(lbl_idSt2);
		
		JLabel lbl_distST2 = new JLabel("");
		lbl_distST2.setBounds(324, 139, 46, 14);
		getContentPane().add(lbl_distST2);
		
		JLabel lbl_tpsPiedST2 = new JLabel("");
		lbl_tpsPiedST2.setBounds(324, 167, 46, 14);
		getContentPane().add(lbl_tpsPiedST2);
		
		JLabel lbl_tpsVeloST2 = new JLabel("");
		lbl_tpsVeloST2.setBounds(331, 191, 46, 14);
		getContentPane().add(lbl_tpsVeloST2);
		
		JLabel lblCoordSt2 = new JLabel("Coordonn\u00E9es:");
		lblCoordSt2.setBounds(243, 60, 78, 14);
		getContentPane().add(lblCoordSt2);
		
		JLabel lblLatST2 = new JLabel("Lattitude:");
		lblLatST2.setBounds(263, 85, 57, 14);
		getContentPane().add(lblLatST2);
		
		JLabel lbl_latST2 = new JLabel("null");
		lbl_latST2.setBounds(319, 85, 46, 14);
		getContentPane().add(lbl_latST2);
		
		JLabel lbl_lonST2 = new JLabel("null");
		lbl_lonST2.setBounds(324, 110, 46, 14);
		getContentPane().add(lbl_lonST2);
		
		JLabel lblLonST2 = new JLabel("Longitude:");
		lblLonST2.setBounds(263, 110, 64, 14);
		getContentPane().add(lblLonST2);
		
		JLabel lblVoiciLaime = new JLabel("Voici la 3i\u00E8me station la plus proche pour null un v\u00E9lo !");
		lblVoiciLaime.setBounds(523, 11, 270, 33);
		getContentPane().add(lblVoiciLaime);
		
		JLabel lblIDST3 = new JLabel("Station:");
		lblIDST3.setBounds(523, 46, 46, 14);
		getContentPane().add(lblIDST3);
		
		JLabel lbl_idST3 = new JLabel("null");
		lbl_idST3.setBounds(579, 46, 46, 14);
		getContentPane().add(lbl_idST3);
		
		JLabel lblCoordST3 = new JLabel("Coordonn\u00E9es:");
		lblCoordST3.setBounds(523, 61, 78, 14);
		getContentPane().add(lblCoordST3);
		
		JLabel lblLatST3 = new JLabel("Lattitude:");
		lblLatST3.setBounds(543, 86, 57, 14);
		getContentPane().add(lblLatST3);
		
		JLabel lbl_latST3 = new JLabel("null");
		lbl_latST3.setBounds(599, 86, 46, 14);
		getContentPane().add(lbl_latST3);
		
		JLabel lbl_lonST3 = new JLabel("null");
		lbl_lonST3.setBounds(604, 111, 46, 14);
		getContentPane().add(lbl_lonST3);
		
		JLabel lblLonST3 = new JLabel("Longitude:");
		lblLonST3.setBounds(543, 111, 64, 14);
		getContentPane().add(lblLonST3);
		
		JLabel lblDistST3 = new JLabel("Distance (km):");
		lblDistST3.setBounds(523, 142, 78, 14);
		getContentPane().add(lblDistST3);
		
		JLabel lbl_distST3 = new JLabel("");
		lbl_distST3.setBounds(594, 146, 46, 14);
		getContentPane().add(lbl_distST3);
		
		JLabel lblTpsPiedST3 = new JLabel("Temps \u00E0 pied:");
		lblTpsPiedST3.setBounds(523, 168, 78, 14);
		getContentPane().add(lblTpsPiedST3);
		
		JLabel lbl_tpsPiedST3 = new JLabel("");
		lbl_tpsPiedST3.setBounds(604, 168, 46, 14);
		getContentPane().add(lbl_tpsPiedST3);
		
		JLabel lbl_tpsVeloST3 = new JLabel("");
		lbl_tpsVeloST3.setBounds(611, 192, 46, 14);
		getContentPane().add(lbl_tpsVeloST3);
		
		JLabel lblTpsVeloST3 = new JLabel("Temps en v\u00E9lo:");
		lblTpsVeloST3.setBounds(523, 193, 78, 14);
		getContentPane().add(lblTpsVeloST3);
		
		JLabel lblNombreVloDispo = new JLabel("Nombre v\u00E9lo dispo:");
		lblNombreVloDispo.setBounds(10, 217, 102, 14);
		getContentPane().add(lblNombreVloDispo);
		
		JLabel lblNombreDePlace = new JLabel("Nombre de place total:");
		lblNombreDePlace.setBounds(10, 247, 117, 14);
		getContentPane().add(lblNombreDePlace);
		
		JLabel lbl_nbVeloMax = new JLabel("");
		lbl_nbVeloMax.setBounds(130, 247, 46, 14);
		getContentPane().add(lbl_nbVeloMax);
		
		JLabel lbl_nbVeloDispo = new JLabel("");
		lbl_nbVeloDispo.setBounds(108, 217, 46, 14);
		getContentPane().add(lbl_nbVeloDispo);
		
		JLabel lblNBVeloDiST2 = new JLabel("Nombre v\u00E9lo dispo:");
		lblNBVeloDiST2.setBounds(240, 217, 102, 14);
		getContentPane().add(lblNBVeloDiST2);
		
		JLabel lblNBMAXST2 = new JLabel("Nombre de place total:");
		lblNBMAXST2.setBounds(240, 247, 117, 14);
		getContentPane().add(lblNBMAXST2);
		
		JLabel lblVDIST3 = new JLabel("Nombre v\u00E9lo dispo:");
		lblVDIST3.setBounds(523, 217, 102, 14);
		getContentPane().add(lblVDIST3);
		
		JLabel lblNBMAXST3 = new JLabel("Nombre de place total:");
		lblNBMAXST3.setBounds(523, 247, 117, 14);
		getContentPane().add(lblNBMAXST3);
		
		JLabel lbl_nbVeloDiST2 = new JLabel("");
		lbl_nbVeloDiST2.setBounds(341, 216, 46, 14);
		getContentPane().add(lbl_nbVeloDiST2);
		
		JLabel lbl_nbMAxST2 = new JLabel("");
		lbl_nbMAxST2.setBounds(351, 247, 46, 14);
		getContentPane().add(lbl_nbMAxST2);
		
		JLabel lbl_nbDispoST3 = new JLabel("");
		lbl_nbDispoST3.setBounds(621, 217, 46, 14);
		getContentPane().add(lbl_nbDispoST3);
		
		JLabel lbl_nbMAxST3 = new JLabel("");
		lbl_nbMAxST3.setBounds(641, 247, 46, 14);
		getContentPane().add(lbl_nbMAxST3);
	}
}
