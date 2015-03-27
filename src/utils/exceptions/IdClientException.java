package utils.exceptions;

public class IdClientException extends Exception {
	public IdClientException(int id) {
		super("Id client non trouvé : " + id);
	} 
}
