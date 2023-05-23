package entities;

/**
 * 
 * Die Klasse Verlauf repräsentiert einen Verlauf einer Aktion in Bezug auf
 * einen bestimmten Artikel und Nutzer.
 */
public class Verlauf {

	private String formattedDatumZeit;
	private Nutzer nutzer;
	private Artikel artikel;
	private String aktion;

	/**
	 * 
	 * 
	 * Erstellt einen neuen Verlauf mit der angegebenen Aktion, dem betroffenen
	 * Nutzer, dem betroffenen Artikel und dem formatierten Datum und Uhrzeit.*
	 * 
	 * @param aktion             die durchgeführte Aktion
	 * @param nutzer             der betroffene Nutzer
	 * @param artikel            der betroffene Artikel
	 * @param formattedDatumZeit das formatierte Datum und die formatierte Uhrzeit
	 */
	public Verlauf(String aktion, Nutzer nutzer, Artikel artikel, String formattedDatumZeit) {

		this.formattedDatumZeit = formattedDatumZeit;
		this.aktion = aktion;
		this.nutzer = nutzer;
		this.artikel = artikel;

	}

	/**
	 * 
	 * Gibt den betroffenen Nutzer zurück.*
	 * 
	 * @return der betroffene Nutzer
	 */

	public Nutzer getNutzer() {
		return nutzer;
	}

	/**
	 * 
	 * Setzt den betroffenen Nutzer.*
	 * 
	 * @param nutzer der betroffene Nutzer
	 */
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	/**
	 * 
	 * Gibt das formatierte Datum und die formatierte Uhrzeit zurück.*
	 * 
	 * @return das formatierte Datum und die formatierte Uhrzeit
	 */
	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}

	/**
	 * 
	 * Gibt den betroffenen Artikel zurück.*
	 * 
	 * @return der betroffene Artikel
	 */
	public Artikel getArtikel() {
		return artikel;
	}

	/**
	 * 
	 * Gibt die durchgeführte Aktion zurück.*
	 * 
	 * @return die durchgeführte Aktion
	 */
	public String getAktion() {
		return aktion;
	}

	/**
	 * 
	 * Setzt die durchgeführte Aktion.*
	 * 
	 * @param aktion die durchgeführte Aktion
	 */

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}

	/**
	 * 
	 * Gibt Alle gespeicherte Änderungen im Verlauf in einen Zeichenkette zurück.*
	 * 
	 * @return gespeicherte Änderungen im Verlauf.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("\nÄnderung:\n ");
		builder.append(aktion);
		builder.append("Name: ");
		builder.append(nutzer.getName());
		builder.append(" hat der Bestand folgende Artikel:  ");
		builder.append(artikel.getName());
		builder.append(",am ");
		builder.append(formattedDatumZeit);
		builder.append(", geändert \n");
		builder.append("Der Bestand Des Artikels beträgt jetzt ");
		builder.append(artikel.getBestand());
		builder.append(" Stück ");

		return builder.toString();
	}

}
