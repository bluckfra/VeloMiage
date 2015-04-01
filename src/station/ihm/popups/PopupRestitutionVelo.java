package station.ihm.popups;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;

import station.ihm.StationIHM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopupRestitutionVelo extends JDialog{
	private StationIHM ihm;
	
	public PopupRestitutionVelo(int idVelo, StationIHM i) {
		this.ihm = i;
		setTitle("Restitution vélo n°" + idVelo); 
		setSize(450,145); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		
		getContentPane().setLayout(null);
		
		JLabel lbl_titre = new JLabel("F\u00E9licitations ! Vous avez bien rendu le V\u00E9lo n\u00B0" + +idVelo+ " ! \n Merci et à bientôt !");
		lbl_titre.setBounds(65, 11, 336, 33);
		getContentPane().add(lbl_titre);
		
		JButton btnDemanderUnTicket = new JButton("Demander un Ticket");
		btnDemanderUnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ihm.afficherTicket();
				dispose();
			}
		});
		btnDemanderUnTicket.setBounds(91, 66, 149, 23);
		getContentPane().add(btnDemanderUnTicket);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(271, 66, 89, 23);
		getContentPane().add(btnQuitter);

	}

}
