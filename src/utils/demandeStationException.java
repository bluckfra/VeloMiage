package utils;

public class demandeStationException extends Exception{
	public demandeStationException(){
		super("Une erreur est survenue lors de la recherche de la station la plus proche.");
	}
}
