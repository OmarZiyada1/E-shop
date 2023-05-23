
package entities;

/**
 * Eine Klasse, die eine Adresse repräsentiert.
 */
public class Adresse {
	private String strasse;
	private String hNr;
	private String plz;
	private String ort;
	private String land;

	/**
	 * Konstruktor für die Adresse.
	 * 
	 * @param strasse Die Straße der Adresse.
	 * @param hNr     Die Hausnummer der Adresse.
	 * @param plz     Die Postleitzahl der Adresse.
	 * @param ort     Der Ort der Adresse.
	 * @param land    Das Land der Adresse.
	 */
	public Adresse(String strasse, String hNr, String plz, String ort, String land) {
		this.strasse = strasse;
		this.hNr = hNr;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}

	/**
	 * Gibt die Straße der Adresse zurück.
	 * 
	 * @return Die Straße der Adresse.
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * Setzt die Straße der Adresse.
	 * 
	 * @param strasse Die Straße der Adresse.
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * Gibt die Hausnummer der Adresse zurück.
	 * 
	 * @return Die Hausnummer der Adresse.
	 */
	public String gethNr() {
		return hNr;
	}

	/**
	 * Setzt die Hausnummer der Adresse.
	 * 
	 * @param hNr Die Hausnummer der Adresse.
	 */
	public void sethNr(String hNr) {
		this.hNr = hNr;
	}

	/**
	 * Gibt die Postleitzahl der Adresse zurück.
	 * 
	 * @return Die Postleitzahl der Adresse.
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * Gibt die Postleitzahl der Adresse zurück.
	 * 
	 * @return Die Postleitzahl der Adresse.
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * Gibt den Ort der Adresse zurück.
	 * 
	 * @return Der Ort der Adresse.
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Setzt den Ort der Adresse.
	 * 
	 * @param ort Der Ort der Adresse.
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * Gibt das Land der Adresse zurück.
	 * 
	 * @return Das Land der Adresse.
	 */
	public String getLand() {
		return land;
	}

	/**
	 * Setzt das Land der Adresse.
	 * 
	 * @param land Das Land der Adresse.
	 */
	public void setLand(String land) {
		this.land = land;
	}

	/**
	 * Gibt eine formatierte Zeichenkette mit allen Adressinformationen zurück.
	 * 
	 * @return Eine formatierte Zeichenkette mit allen Adressinformationen.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tAdresse: ");
		builder.append(strasse);

		builder.append(" " + hNr);
		builder.append(", ");
		builder.append(plz);
		builder.append(" ");
		builder.append(ort);
		builder.append(" ");
		builder.append(land);

		return builder.toString();
	}

}
