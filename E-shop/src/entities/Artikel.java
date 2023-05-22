package entities;

public class Artikel {
	private int artikelId;
	private String name;
	private String beschreibung;
	private int bestand;
	private double preis;
	private boolean verfuegbar;

	/**
	 * @param artikelId
	 * @param name
	 * @param beschreibung
	 * @param bestand
	 * @param preis
	 */
	public Artikel( String name, String beschreibung, int bestand, double preis) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.bestand = bestand;
		this.preis = preis;
		this.verfuegbar = true;
	}

	/**
	 * @param artikelId
	 * @param name
	 * @param beschreibung
	 * @param bestand
	 * @param preis
	 * @param verfügbar
	 */
	public Artikel( String name, String beschreibung, int bestand, double preis, boolean verfuegbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.bestand = bestand;
		this.preis = preis;
		this.verfuegbar = verfuegbar;
	}

	/**
	 * @return the artikelId
	 */
	public int getArtikelId() {
		return artikelId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @return the bestand
	 */
	public int getBestand() {
		return bestand;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}

	/**
	 * @param bestand the bestand to set
	 */
	public void setBestand(int bestand) {
		this.bestand = bestand;
	}

	public void setVerfügbar(boolean verfuegbar) {
		this.verfuegbar = verfuegbar;
	}

	/**
	 * @param artikelId the artikelId to set
	 */
	public void setArtikelId(int artikelId) {
		this.artikelId = artikelId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @param preis the preis to set
	 */
	public void setPreis(double preis) {
		this.preis = preis;
	}

	/**
	 * @return the preis
	 */
	public double getPreis() {
		return preis;
	}

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

	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("(ArtikelId = ");
		builder.append(artikelId);
		builder.append(", Name = ");
		builder.append(name);
		builder.append(", Beschreibung = ");
		builder.append(beschreibung);
		builder.append(", Bestand = ");
		builder.append(bestand);
		builder.append(", Preis = ");
		builder.append(preis);
		builder.append(", Verfügbarkeit = ");
		builder.append(verfuegbar);
		builder.append(")");
		builder.append("\n");
		
		return builder.toString();
		
	}

}
