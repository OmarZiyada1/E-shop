package entities;

/**
 * 
 * Die Klasse Verlauf reprï¿½sentiert einen Verlauf einer Aktion in Bezug auf
 * einen bestimmten Artikel und Nutzer.
 */
public class Verlauf {

	private String formattedDatumZeit;
	private Nutzer nutzer;
	private Artikel artikel;
	private AKTIONSTYP aktion;
	public enum AKTIONSTYP{
		Neue("Mitarbeiter: (Neue Artikel hinzugefï¿½gt)"), 
		ERHOEHEN("Mitarbeiter: (Artikelbestand erhï¿½ht)"),
		BESTELLEN("Kunde: (Kundenbestllung)"),
		SENKEN("Mitarbeiter: (Bestand gesenkt) "),
		LOESCHEN("Mitarbeiter: (Artikel gelï¿½scht)");
		
		private final String value;
		
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		private AKTIONSTYP(String value){
	         this.value = value;
	        
	     }
		 
	}
	private int aenderungsMenge;
	
	




	/**
	 * 
	 * 
	 * Erstellt einen neuen Verlauf mit der angegebenen Aktion, dem betroffenen
	 * Nutzer, dem betroffenen Artikel und dem formatierten Datum und Uhrzeit.*
	 * 
	 * @param nutzer             der betroffene Nutzer
	 * @param artikel            der betroffene Artikel
	 * @param formattedDatumZeit das formatierte Datum und die formatierte Uhrzeit
	 */
	public Verlauf(AKTIONSTYP aktion, Nutzer nutzer, Artikel artikel, String formattedDatumZeit,int aenderungsMenge ) {
		this.aktion=aktion;
		this.formattedDatumZeit = formattedDatumZeit;
		this.nutzer = nutzer;
		this.artikel = artikel;
		this.aenderungsMenge=aenderungsMenge;

	}
	
	

	/**
	 * 
	 * Gibt den betroffenen Nutzer zurï¿½ck.*
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
	 * Gibt das formatierte Datum und die formatierte Uhrzeit zurï¿½ck.*
	 * 
	 * @return das formatierte Datum und die formatierte Uhrzeit
	 */
	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}

	/**
	 * 
	 * Gibt den betroffenen Artikel zurï¿½ck.*
	 * 
	 * @return der betroffene Artikel
	 */
	public Artikel getArtikel() {
		return artikel;
	}



	/**
	 * @return the aktion
	 */
	public AKTIONSTYP getAktion() {
		return aktion;
	}

	
	

	public int getAenderungsMenge() {
		return aenderungsMenge;
	}



	public void setAenderungsMenge(int aenderungsMenge) {
		this.aenderungsMenge = aenderungsMenge;
	}


	/**
	 * 
	 * Gibt Alle gespeicherte ï¿½nderungen im Verlauf in einen Zeichenkette zurï¿½ck.*
	 * 
	 * @return gespeicherte ï¿½nderungen im Verlauf.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("\nÄnderung:  ");
		builder.append(aktion.value);
		builder.append(" Name: '");
		builder.append(nutzer.getName());
		builder.append(" ");
		builder.append(nutzer.getVorname());
		builder.append("' hat der Bestand folgende Artikel:  ");
		builder.append("'"+ artikel.getName()+"'");
		builder.append(" ,am ");
		builder.append(formattedDatumZeit);
		builder.append(", geÃ¤ndert. ");
		builder.append("Der Bestand des Artikels hat sich um  ");
		builder.append(aenderungsMenge);
		builder.append(" Stück geändert");

		return builder.toString();
	}

}
