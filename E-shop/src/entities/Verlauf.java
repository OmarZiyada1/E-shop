package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Verlauf {

	
	private final static LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",
			Locale.GERMANY);
	private String formattedDatumZeit;
	private Nutzer nutzer;
	private Artikel artikel;
	private String aktion;

	public Verlauf(String aktion, Nutzer nutzer, Artikel artikel) {

		formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		this.aktion = aktion;
		this.nutzer = nutzer;
		this.artikel = artikel;

	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Änderung: \n Der Nutzer: ");
		builder.append(nutzer.getName());
		builder.append("hat der Bestand folgende Artikel:  ");
		builder.append(artikel);
		builder.append(",am ");
		builder.append(formattedDatumZeit);
		builder.append(", geändert \n");
		builder.append("Der Bestand Des Artikels beträgt jetzt");
		builder.append(artikel.getBestand());

		return builder.toString();
	}

}
