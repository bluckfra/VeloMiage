package station.moteur.ihm.popups;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PopupStationPlacesDispo extends JDialog{
	public PopupStationPlacesDispo(Object[] sta) {
		setTitle("Veuillez-vous diriger vers la station " + sta[0]); 
		setSize(450,245); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		
		getContentPane().setLayout(null);
		
		JLabel lbl_titre = new JLabel("Voici la station la plus proche pour " + sta[3] + " un vélo !");
		lbl_titre.setBounds(99, 11, 270, 33);
		getContentPane().add(lbl_titre);
		
		JButton btnDemanderUnTicket = new JButton("Demander plus d'informations sur la station");
		btnDemanderUnTicket.setBounds(35, 127, 248, 23);
		getContentPane().add(btnDemanderUnTicket);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(305, 127, 89, 23);
		getContentPane().add(btnQuitter);
		
		JLabel lblStation = new JLabel("Station:");
		lblStation.setBounds(99, 46, 46, 14);
		getContentPane().add(lblStation);
		
		JLabel lblCoordonnes = new JLabel("Coordonn\u00E9es:");
		lblCoordonnes.setBounds(99, 71, 78, 14);
		getContentPane().add(lblCoordonnes);
		
		JLabel lblLattitude = new JLabel("Lattitude:");
		lblLattitude.setBounds(99, 96, 57, 14);
		getContentPane().add(lblLattitude);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(269, 96, 64, 14);
		getContentPane().add(lblLongitude);
		
		JLabel lbl_IDStation = new JLabel("" + sta[0]);
		lbl_IDStation.setBounds(155, 46, 46, 14);
		getContentPane().add(lbl_IDStation);
		
		JLabel lbl_lati = new JLabel("" + sta[1]);
		lbl_lati.setBounds(155, 96, 46, 14);
		getContentPane().add(lbl_lati);
		
		JLabel lbl_longi = new JLabel(""+ sta[2]);
		lbl_longi.setBounds(330, 96, 46, 14);
		getContentPane().add(lbl_longi);
	}
}
