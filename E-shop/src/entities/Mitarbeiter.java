package entities;

/**
 * Die Klasse "Mitarbeiter" repräsentiert einen Mitarbeiter und erbt von der
 * Klasse "Nutzer" Sie enthält Informationen über den Mitarbeiter wie
 * MitarbeiterID, Rolle
 */

public class Mitarbeiter extends Nutzer {
	private int maId;
	private static final char rolle = 'm';

	/**
	 * Constructor
	 * 
	 * Der Constructor der Klasse initialisiert einen Mitarbeiter mit den angegebenen
	 * Werten für Name, Vorname, Nutzername, Passwort und Adresse. Dabei wird der
	 * Konstruktor der Basisklasse "Nutzer" aufgerufen 
	 * 
	 * @param maId
	 * @param name
	 * @param vorname
	 * @param nutzerNr
	 * @param passwort
	 * @param adresse
	 */

	public Mitarbeiter(String name, String vorname, String nutzerNr, String passwort, Adresse adresse) {
		super(name, vorname, nutzerNr, passwort, adresse);
	}
	
	public Mitarbeiter(String name, String vorname, String nutzerNr, String passwort) {
		super(name, vorname, nutzerNr, passwort);
	}

	/**
	 * @return die maId
	 */
	public int getMaId() {
		return maId;
	}

	/**
	 * @param maId
	 */
	public void setMaId(int maId) {
		this.maId = maId;
	}

	/**
	 * @return die rolle
	 */
	public static char getRolle() {
		return rolle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mitarbeiter ");
		builder.append(super.toString());
		builder.append("[maId=");
		builder.append(maId);
		builder.append("]");
		return builder.toString();
	}

}
