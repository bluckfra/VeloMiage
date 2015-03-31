package station.moteur.ihm.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;

import station.moteur.ihm.Etat;
import station.moteur.ihm.StationIHM;
import station.moteur.ihm.popups.PopupErreurRemote;

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
		
		JLabel lbl_titre_restitution = new JLabel("Restitution d'un v\u00E9lo");
		lbl_titre_restitution.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titre_restitution.setBounds(10, 11, 159, 14);
		add(lbl_titre_restitution);
		
		JLabel lbl_restituerVelo = new JLabel("Veuillez entrer le num\u00E9ro de v\u00E9lo \u00E0 restituer :");
		lbl_restituerVelo.setBounds(73, 48, 237, 14);
		add(lbl_restituerVelo);
		
		txt_idVelo = new JTextField();
		txt_idVelo.setBounds(299, 45, 86, 20);
		add(txt_idVelo);
		txt_idVelo.setColumns(10);
		
		JButton btn_valider = new JButton("Valider");
		btn_valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modele.actionRestitution(Integer.parseInt(txt_idVelo.getText()));
					remiseAZero();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					afficherError();
				} catch (RemoteException e1) {
					remiseAZero();
					modele.changerPanel(Etat.Menu);
					new PopupErreurRemote().setVisible(true);
				}
			}
		});
		btn_valider.setBounds(73, 106, 89, 23);
		add(btn_valider);
		
		JButton btn_annuler = new JButton("Annuler");
		btn_annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remiseAZero();
				modele.changerPanel(Etat.Menu);
			}
		});
		btn_annuler.setBounds(207, 106, 89, 23);
		add(btn_annuler);
		
		lbl_error = new JLabel("");
		lbl_error.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_error.setForeground(Color.RED);
		lbl_error.setBounds(67, 73, 263, 22);
		add(lbl_error);
	}
	
	public void afficherError(){
		lbl_error.setText("Erreur : ID du vélo à restituer incorrect");
		lbl_error.setEnabled(true);
	}
	
	public void afficherVeloInvalideError(){
		lbl_error.setText("Erreur : Le vélo que vous voulez rendre n'est pas en location");
		lbl_error.setEnabled(true);
	}
	
	public void remiseAZero(){
		for (Component C : this.getComponents()){
			if(C instanceof JTextField){
				((JTextComponent) C ).setText("");
			}
		}
	}
}
