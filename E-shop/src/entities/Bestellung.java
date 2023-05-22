package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

public class Bestellung {
	/**
	 * 
	 */
	

	private final static LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",
			Locale.GERMANY);
	private int bestellungsNr;
	private String formattedDatumZeit;
	private Kunde kunde;
	private HashMap<Artikel, Integer> bestellteArtikeln;
	private double Gesamtprise = 0;
	private Rechnung rechnung;




	public Bestellung(Kunde kunde, HashMap<Artikel, Integer> bestellteArtikeln , double gesamtprise) {
		this.bestellteArtikeln = bestellteArtikeln;
		this.kunde = kunde;
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		this.Gesamtprise= gesamtprise;
	}

	/**
	 * @return the bestellteArtikeln
	 */
	public HashMap<Artikel, Integer> getBestellteArtikeln() {
		return bestellteArtikeln;
	}

	/**
	 * @param bestellteArtikeln the bestellteArtikeln to set
	 */
	public void setBestellteArtikeln(HashMap<Artikel, Integer> bestellteArtikeln) {
		this.bestellteArtikeln = bestellteArtikeln;
	}

	/**
	 * @return the kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * @param kunde the kunde to set
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * @return the formattedDatumZeit
	 */
	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}
	
	/**
	 * @return the gesamtprise
	 */
	public double getGesamtprise() {
		return Gesamtprise;
	}

	/**
	 * @param gesamtprise the gesamtprise to set
	 */
	public void setGesamtprise(double gesamtprise) {
		Gesamtprise = gesamtprise;
	}

	
	/**
	 * @return the bestellungsNr
	 */
	public int getBestellungsNr() {
		return bestellungsNr;
	}

	/**
	 * @param bestellungsNr the bestellungsNr to set
	 */
	public void setBestellungsNr(int bestellungsNr) {
		this.bestellungsNr = bestellungsNr;
	}
	
	

	public Rechnung getRechnung() {
		return rechnung;
	}

	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bestellung:  am \n\t");
		builder.append(formattedDatumZeit);
		builder.append(", kundeNr.= ");
		builder.append(kunde.getKndNr());
		builder.append("\nBstellteArtikeln: \n");
		
		
		int i = 1;
		for (Artikel best : bestellteArtikeln.keySet()) {

			int anzahlArtikel = bestellteArtikeln.get(best);
			Artikel bestellungArtikel = best;
			builder.append("\t"+ i + ". artikel: " + bestellungArtikel.getName() + ", menge: " + anzahlArtikel + "\n");
			i++;
		}
		
		
		return builder.toString();
	}

}
