package utils;

public class listeVeloException extends Exception{
	public listeVeloException() {
		super("Un probl�me est survenu durant la r�cup�ration de la liste de v�los. Veuillez r�essayer.");
	}
}
