package utils.exceptions;

@SuppressWarnings("serial")
public class demandeAboException extends Exception{
	public demandeAboException() {
		super("Un problème est apparu durant la création de l'abonnement. Veuillez réessayer.");
	} 
}
