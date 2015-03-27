package utils;

public class listeVeloException extends Exception{
	public listeVeloException() {
		super("Un problème est survenu durant la récupération de la liste de vélos. Veuillez réessayer.");
	}
}
