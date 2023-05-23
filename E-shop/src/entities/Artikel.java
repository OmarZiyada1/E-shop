package entities;

/**
 * Die Klasse "Artikel" repräsentiert einen Artikel und enthält Informationen
 * wie Artikel-ID, Name, Beschreibung, Bestand, Preis und Verfügbarkeit.
 */
public class Artikel {
	private int artikelId;
	private String name;
	private String beschreibung;
	private int bestand;
	private double preis;
	private boolean verfuegbar;

	/**
	 * Konstruktor1 für die Klasse Artikel. Erzeugt einen Artikel mit den
	 * angegebenen Werten für Name, Beschreibung, Bestand und Preis. Der Artikel
	 * wird standardmäßig als verfügbar markiert.
	 * 
	 * @param name         der Name des Artikels
	 * @param beschreibung die Beschreibung des Artikels
	 * @param bestand      der Bestand des Artikels
	 * @param preis        der Preis des Artikels
	 */
	public Artikel(String name, String beschreibung, int bestand, double preis) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.bestand = bestand;
		this.preis = preis;
		this.verfuegbar = true;
	}

	/**
	 * Konstruktor2 für die Klasse Artikel. Erzeugt einen Artikel mit den
	 * angegebenen Werten für Name, Beschreibung, Bestand, Preis und Verfügbarkeit.
	 * 
	 * @param name         der Name des Artikels
	 * @param beschreibung die Beschreibung des Artikels
	 * @param bestand      der Bestand des Artikels
	 * @param preis        der Preis des Artikels
	 * @param verfuegbar   die Verfügbarkeit des Artikels
	 */
	public Artikel(String name, String beschreibung, int bestand, double preis, boolean verfuegbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.bestand = bestand;
		this.preis = preis;
		this.verfuegbar = verfuegbar;
	}

	/**
	 * Gibt die Artikel-ID zurück.
	 * 
	 * @return die Artikel-ID
	 */
	public int getArtikelId() {
		return artikelId;
	}

	/**
	 * Gibt den Namen des Artikels zurück.
	 * 
	 * @return der Name des Artikels
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt die Beschreibung des Artikels zurück.
	 * 
	 * @return die Beschreibung des Artikels
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Gibt den Bestand des Artikels zurück.
	 * 
	 * @return der Bestand des Artikels
	 */
	public int getBestand() {
		return bestand;
	}

	/**
	 * Gibt die Verfügbarkeit des Artikels zurück.
	 * 
	 * @return true, wenn der Artikel verfügbar ist, andernfalls false
	 */
	public boolean isVerfuegbar() {
		return verfuegbar;
	}

	/**
	 * Setzt den Bestand des Artikels.
	 * 
	 * @param bestand der neue Bestand des Artikels
	 */
	public void setBestand(int bestand) {
		this.bestand = bestand;
	}

	/**
	 * Setzt die Verfügbarkeit des Artikels.
	 * 
	 * @param verfuegbar die neue Verfügbarkeit des Artikels
	 */
	public void setVerfügbar(boolean verfuegbar) {
		this.verfuegbar = verfuegbar;
	}

	/**
	 * Setzt die Artikel-ID.
	 * 
	 * @param artikelId die neue Artikel-ID
	 */
	public void setArtikelId(int artikelId) {
		this.artikelId = artikelId;
	}

	/**
	 * Setzt den Namen des Artikels.
	 * 
	 * @param name der neue Name des Artikels
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setzt die Beschreibung des Artikels.
	 * 
	 * @param beschreibung die neue Beschreibung des Artikels
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Setzt den Preis des Artikels.
	 * 
	 * @param preis der neue Preis des Artikels
	 */
	public void setPreis(double preis) {
		this.preis = preis;
	}

	/**
	 * Gibt den Preis des Artikels zurück.
	 * 
	 * @return der Preis des Artikels
	 */
	public double getPreis() {
		return preis;
	}

	/**
	 * Überprüft, ob der Artikel mit einem anderen Artikelobjekt identisch ist.
	 * 
	 * @param andereArtikel das andere Artikelobjekt, mit dem verglichen wird
	 * @return true, wenn die Artikel identisch sind, andernfalls false
	 */
	public boolean equals(Object andereArtikel) {
		if (andereArtikel instanceof Artikel) {
			return ((this.artikelId == ((Artikel) andereArtikel).artikelId)
					&& (this.beschreibung.equals(((Artikel) andereArtikel).beschreibung)
							&& (this.name.equals(((Artikel) andereArtikel).name))
							&& (this.bestand == ((Artikel) andereArtikel).bestand)
							&& (this.preis == ((Artikel) andereArtikel).preis)));
		} else {
			return false;
		}

	}

	/**
	 * Gibt die Artikelformationen dar.
	 * 
	 * @return Die Artikelformationen des Artikel
	 */

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("(ArtikelId = " + artikelId);
		builder.append(", Name = " + name);
		builder.append(", Beschreibung = " + beschreibung);
		builder.append(", Bestand = " + bestand);
		builder.append(", Preis = " + preis);
		builder.append(", Verfügbarkeit = " + verfuegbar);
		builder.append(")");
		builder.append("\n");

		return builder.toString();

	}

}
