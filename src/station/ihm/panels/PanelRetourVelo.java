package station.ihm.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;

import station.ihm.Etat;
import station.ihm.StationIHM;
import station.ihm.popups.PopupErreurRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.rmi.RemoteException;

public class PanelRetourVelo extends JPanel{
	private JTextField txt_idVelo;
	private JLabel lbl_error;
	protected StationIHM modele;
	
	public PanelRetourVelo(StationIHM m) {
		modele = m;
		setLayout(null);
		
		JLabel lbl_titre_restitution = new JLabel("Restitution d'un v\u00E9lo :");
		lbl_titre_restitution.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_titre_restitution.setBounds(10, 11, 131, 14);
		add(lbl_titre_restitution);
		
		JLabel lbl_restituerVelo = new JLabel("Veuillez entrer le num\u00E9ro de v\u00E9lo \u00E0 restituer :");
		lbl_restituerVelo.setBounds(28, 69, 237, 14);
		add(lbl_restituerVelo);
		
		txt_idVelo = new JTextField();
		txt_idVelo.setBounds(275, 66, 86, 20);
		add(txt_idVelo);
		txt_idVelo.setColumns(10);
		
		JButton btn_valider = new JButton("Valider");
		btn_valider.setBounds(10, 156, 89, 23);
		add(btn_valider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(109, 156, 89, 23);
		add(btnRetour);
		
		lbl_error = new JLabel("");
		lbl_error.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_error.setForeground(Color.RED);
		lbl_error.setBounds(28, 36, 412, 22);
		add(lbl_error);
		
		btn_valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modele.actionRestitution(Integer.parseInt(txt_idVelo.getText()));
					remiseAZero();
				} catch (NumberFormatException e1) {
					afficherIdVeloErreur();
				} catch (RemoteException e1) {
					remiseAZero();
					modele.changerPanel(Etat.Menu);
					new PopupErreurRemote().setVisible(true);
				}
			}
		});

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remiseAZero();
				modele.changerPanel(Etat.Menu);
			}
		});
	}
	
	public void afficherIdVeloErreur(){
		lbl_error.setText("Erreur : ID du vélo à restituer incorrect");
		lbl_error.setVisible(true);
		remiseAZero();
	}
	
	public void afficherVeloInvalideError(){
		lbl_error.setText("Erreur : Le vélo que vous voulez rendre n'est pas en location");
		lbl_error.setVisible(true);
		remiseAZero();
	}
	
	public void remiseAZero(){
		for (Component C : this.getComponents()){
			if(C instanceof JTextField){
				((JTextComponent) C ).setText("");
			}
		}
	}
}
