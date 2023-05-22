package entities;

import java.util.List;
import java.util.Vector;

/**
 * Die Klasse "Kunde" repräsentiert einen Kunden und erbt von der Klasse
 * "Nutzer" Sie enthält Informationen über den Kunden wie Kundennummer, Rolle
 * und KundeWarenkorb
 */
public class Kunde extends Nutzer {
	private int kndNr;
	private static final char rolle = 'k';
	private Warenkorb kundeWarenkorb;
	private List<Bestellung> meineBestellungen = new Vector<>();
	private Bestellung aktuelleBestellung ;

	
	/**
	 * Constructor
	 * 
	 * Der Constructor der Klasse initialisiert einen Kunden mit den angegebenen
	 * Werten für Name, Vorname, Nutzername, Passwort und Adresse. Dabei wird der
	 * Konstruktor der Basisklasse "Nutzer" aufgerufen und ein neuer Kundenwarenkorb
	 * für den Kunden erstellt.
	 * 
	 * @param kndNr
	 * @param name
	 * @param vorname
	 * @param nutzerNr
	 * @param passwort
	 * @param adresse
	 */

	public Kunde(String name, String vorname, String nutzerNr, String passwort, Adresse adresse) {
		super(name, vorname, nutzerNr, passwort, adresse);
		this.kundeWarenkorb = new Warenkorb();
	}

	/**
	 * Gibt die Kundennummer zurück
	 * 
	 * @return die Kundennummer
	 */
	public int getKndNr() {
		return kndNr;
	}

	/**
	 * Setzt die Kundennummer
	 * 
	 * @param Kundennummer
	 */
	public void setKndNr(int kndNr) {
		this.kndNr = kndNr;
	}

	/**
	 * Gibt die Rolle des Kunden zurück
	 * 
	 * @return die rolle
	 */
	public static char getRolle() {
		return rolle;
	}

	/**
	 * Gibt den Warenkorb des Kunden zurück
	 * 
	 * @return der kundeWarenkorb
	 */
	public Warenkorb getKundeWarenkorb() {
		return kundeWarenkorb;
	}

	/**
	 * Setzt den Warenkorb des Kunden
	 * 
	 * @param kundeWarenkorb
	 */
	public void setKundeWarenkorb(Artikel artikel, int anzahl) {
		this.kundeWarenkorb.setKorbArtikelListe(artikel, anzahl);
		;
	}

	/**
	 * @return the meineBestellungen
	 */
	public List<Bestellung> getMeineBestellungen() {
		return meineBestellungen;
	}

	/**
	 * @param meineBestellungen the meineBestellungen to set
	 */
	public void setMeineBestellungen(List<Bestellung> meineBestellungen) {
		this.meineBestellungen = meineBestellungen;
	}
	
	
	
	
	/**
	 * @return the aktuelleBestellung
	 */
	public Bestellung getAktuelleBestellung() {
		return aktuelleBestellung;
	}

	/**
	 * @param aktuelleBestellung the aktuelleBestellung to set
	 */
	public void setAktuelleBestellung(Bestellung aktuelleBestellung) {
		this.aktuelleBestellung = aktuelleBestellung;
	}


	/**
	 * Kundeninformationen darstellen.
	 * 
	 * @return Die Kundeninformationen des Kunden
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("________________________");
		builder.append("\n");
		builder.append("Kunde: ");
		builder.append("\n\tkndNr: ");
		builder.append(kndNr);
		builder.append("\n");
		builder.append(super.toString());
		builder.append("\n");
		
		

		return builder.toString();
	}

}
