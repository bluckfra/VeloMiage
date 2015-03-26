package station.moteur.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StationJFrame extends JFrame {

	private JPanel contentPane;
	private JFrame frameCourante = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StationJFrame frame = new StationJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StationJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue ! Choisissez une action :");
		lblBienvenue.setBounds(10, 11, 343, 14);
		contentPane.add(lblBienvenue);
		
		JButton btnRendreVelo = new JButton("Rendre un v\u00E9lo");
		btnRendreVelo.setBounds(10, 36, 343, 23);
		contentPane.add(btnRendreVelo);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(10, 231, 89, 23);
		contentPane.add(btnQuitter);
		
		JButton btnDemanderUnAbonnement = new JButton("Demander un abonnement");
		btnDemanderUnAbonnement.setBounds(10, 70, 343, 23);
		contentPane.add(btnDemanderUnAbonnement);
		
		JButton btnLouerVelo = new JButton("Louer un v\u00E9lo");
		btnLouerVelo.setBounds(10, 104, 343, 23);
		contentPane.add(btnLouerVelo);
		
		btnDemanderUnAbonnement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnLouerVelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnRendreVelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(frameCourante, "Voulez réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
					
				if (reponse == JOptionPane.YES_OPTION) 
				{
					System.exit(0);
				}
			}
		});

	}
}
