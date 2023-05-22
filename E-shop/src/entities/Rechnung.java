package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Rechnung {
	private final static LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
	private String formattedDatumZeit;
	private double gesamtPreis;
	private int rechnungNr;
	private Bestellung bestellung;
	/**
	 * konstruktor
	 * @param bestellung
	 * @param gesamtPreis
	 * @param rechnungNr
	 */
	public Rechnung(Bestellung bestellung) {
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		this.bestellung = bestellung;
		
	}
	/**
	 * @return the bestellung
	 */
	public Bestellung getBestellung() {
		return bestellung;
	}
	/**
	 * @param bestellung the bestellung to set
	 */
	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}
	/**
	 * @return the gesamtPreis
	 */
	public double getGesamtPreis() {
		return gesamtPreis;
	}
	/**
	 * @param gesamtPreis the gesamtPreis to set
	 */
	public void setGesamtPreis(double gesamtPreis) {
		this.gesamtPreis = gesamtPreis;
	}
	/**
	 * @return the rechnungNr
	 */
	public int getRechnungNr() {
		return rechnungNr;
	}
	/**
	 * @param rechnungNr the rechnungNr to set
	 */
	public void setRechnungNr(int rechnungNr) {
		this.rechnungNr = rechnungNr;
	}
	/**
	 * @return the formattedDatumZeit
	 */
	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rechnung: am ");
		builder.append(formattedDatumZeit);
		builder.append(" RechnungNr: ");
		builder.append(rechnungNr);
		builder.append("\n\t");
		builder.append(bestellung.getKunde().getVorname());
		builder.append(" ");
		builder.append(bestellung.getKunde().getName());
		builder.append(bestellung.getKunde().getAdresse());
		builder.append("\n\n\tBestellungsNr.: ");
		builder.append(bestellung.getBestellungsNr());
		builder.append("\n");
		int i = 1;
		for (Artikel best : bestellung.getBestellteArtikeln().keySet()) {
			int anzahlArtikel = bestellung.getBestellteArtikeln().get(best);
			Artikel bestellungArtikel = best;
			builder.append("\t\t"+ i + ". artikel: " + bestellungArtikel.getName() + ", menge: " + anzahlArtikel + "\n");
			i++;
		}
		builder.append("\n\t Summe: ");
		builder.append(bestellung.getGesamtprise());
		builder.append("€ ink. 19% mwst");
		
		
		return builder.toString();
	}
	
	
	

}
