package utils.exceptions;

@SuppressWarnings("serial")
public class demandeAboException extends Exception{
	public demandeAboException() {
		super("Un probl�me est apparu durant la cr�ation de l'abonnement. Veuillez r�essayer.");
	} 
}
