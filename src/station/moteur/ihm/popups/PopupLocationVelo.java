package station.moteur.ihm.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class PopupLocationVelo extends JDialog {

	
	public PopupLocationVelo(int idVelo) {
		getContentPane().setLayout(null);
		
		JLabel lblRetirer = new JLabel("Vous avez bien retir\u00E9 le v\u00E9lo n\u00B0 "+idVelo+" ! Vous allez \u00EAtre d\u00E9connect\u00E9. ");
		lblRetirer.setBounds(65, 11, 324, 33);
		getContentPane().add(lblRetirer);
		
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
