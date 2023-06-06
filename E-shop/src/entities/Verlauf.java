package entities;

/**
 * 
 * Die Klasse Verlauf repr�sentiert einen Verlauf einer Aktion in Bezug auf
 * einen bestimmten Artikel und Nutzer.
 */
public class Verlauf {

	private String formattedDatumZeit;
	private Nutzer nutzer;
	private Artikel artikel;
	private AKTIONSTYP aktion;
	public enum AKTIONSTYP{
		Neue("Mitarbeiter: (Neue Artikel hinzugef�gt)"), 
		ERHOEHEN("Mitarbeiter: (Artikelbestand erh�ht)"),
		BESTELLEN("Kunde: (Kundenbestllung)"),
		SENKEN("Mitarbeiter: (Bestand gesenkt) "),
		LOESCHEN("Mitarbeiter: (Artikel gel�scht)");
		
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
	public Verlauf(AKTIONSTYP aktion, Nutzer nutzer, Artikel artikel, String formattedDatumZeit) {
		this.aktion=aktion;
		this.formattedDatumZeit = formattedDatumZeit;
		this.nutzer = nutzer;
		this.artikel = artikel;

	}
	
	

	/**
	 * 
	 * Gibt den betroffenen Nutzer zur�ck.*
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
	 * Gibt das formatierte Datum und die formatierte Uhrzeit zur�ck.*
	 * 
	 * @return das formatierte Datum und die formatierte Uhrzeit
	 */
	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}

	/**
	 * 
	 * Gibt den betroffenen Artikel zur�ck.*
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



	/**
	 * 
	 * Gibt Alle gespeicherte �nderungen im Verlauf in einen Zeichenkette zur�ck.*
	 * 
	 * @return gespeicherte �nderungen im Verlauf.
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
		builder.append(", geändert. ");
		builder.append("Der Bestand des Artikels beträgt jetzt ");
		builder.append(artikel.getBestand());
		builder.append(" Stück ");

		return builder.toString();
	}

}
