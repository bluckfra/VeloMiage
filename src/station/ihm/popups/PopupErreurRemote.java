package station.ihm.popups;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class PopupErreurRemote extends JDialog {

	
	public PopupErreurRemote() {
		
		setTitle("Erreur Connexion"); 
		setSize(450,145); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);

		getContentPane().setLayout(null);
		
		JLabel lblErreur = new JLabel("Erreur de connexion avec le serveur. Veuillez recommencer");
		lblErreur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblErreur.setForeground(Color.RED);
		lblErreur.setBounds(10, 11, 423, 33);
		getContentPane().add(lblErreur);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(165, 55, 89, 23);
		getContentPane().add(btnOk);

	}

}
