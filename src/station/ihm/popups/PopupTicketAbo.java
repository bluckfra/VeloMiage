package station.ihm.popups;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;

public class PopupTicketAbo extends JDialog{
	
	public PopupTicketAbo(Object[] reponse) {
		Timestamp debut = (Timestamp) reponse[0];
		Timestamp fin = (Timestamp) reponse[1];
		Double prix = (Double) reponse[2];
		
		setTitle("Ticket retour vélo"); 
		setSize(450,171); 
		setLocationRelativeTo(null);
		setResizable(false);
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		
		getContentPane().setLayout(null);
		
		JLabel lblTicket = new JLabel("Ticket location :");
		lblTicket.setBounds(10, 11, 125, 14);
		getContentPane().add(lblTicket);
		
		JLabel lblDateDbutLocation = new JLabel("Date d\u00E9but location :");
		lblDateDbutLocation.setBounds(20, 31, 125, 14);
		getContentPane().add(lblDateDbutLocation);
		
		JLabel lblDateFinLoc = new JLabel("Date fin location :");
		lblDateFinLoc.setBounds(20, 51, 125, 14);
		getContentPane().add(lblDateFinLoc);
		
		JLabel lblPrixDeLa = new JLabel("Prix de la course :");
		lblPrixDeLa.setBounds(20, 71, 125, 14);
		getContentPane().add(lblPrixDeLa);
		
		JLabel lblValFin = new JLabel(fin.toGMTString());
		lblValFin.setBounds(155, 51, 185, 14);
		getContentPane().add(lblValFin);
		
		JLabel lblValDeb = new JLabel(debut.toGMTString());
		lblValDeb.setBounds(155, 31, 185, 14);
		getContentPane().add(lblValDeb);
		
		JLabel lblValPrix = new JLabel(prix+"");
		lblValPrix.setBounds(155, 71, 185, 14);
		getContentPane().add(lblValPrix);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(166, 108, 89, 23);
		getContentPane().add(btnOk);

	}
}
