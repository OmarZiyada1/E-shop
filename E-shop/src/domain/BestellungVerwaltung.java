package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import domain.exceptions.WarenkorbLeerException;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;

/**
 * 
 * Die Klasse BestellungVerwaltung verwaltet Bestellungen und den
 * Bestellungsprozess.
 */
public class BestellungVerwaltung {

	private LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
	private String formattedDatumZeit;
	private Bestellung bestellung;
	private List<Bestellung> bestellungList = new Vector<>();
	private ArtikelVerwaltung artikelVW = new ArtikelVerwaltung();

	/*
	 * Akualisiert die aktuelleDatumZeit Variable
	 */
	public void updateTime() {
		aktuelleDatumZeit = LocalDateTime.now();
	}

	/**
	 * 
	 * Erstellt eine neue Bestellung für den angegebenen Kunden.
	 * 
	 * @param kunde Der Kunde, der die Bestellung aufgibt.
	 * @return Die erstellte Bestellung.
	 * @throws WarenkorbLeerException Wenn der Warenkorb des Kunden leer ist.
	 */
	public Bestellung bestellen(Kunde kunde) throws WarenkorbLeerException {
		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		HashMap<Artikel, Integer> artikelnInWarenkorbList = kunde.getKundeWarenkorb().getKorbArtikelListe();

		if (artikelnInWarenkorbList.isEmpty()) {
			throw new WarenkorbLeerException();
		} else {
			// loop Reduziert den Bestand der Artikel in Unserem Bestand
			for (Artikel artikel : artikelnInWarenkorbList.keySet()) {
				Artikel warenkorpartikel = artikel;
				int anzahlArtikelWK = artikelnInWarenkorbList.get(artikel);
				artikelVW.bestandSenken(warenkorpartikel, anzahlArtikelWK);
			}
			// Erstelle eine neue Bestellung
			bestellung = new Bestellung(kunde, artikelnInWarenkorbList, kunde.getKundeWarenkorb().getGesamtPrise(),
					formattedDatumZeit);
			generateBestellungsNr();
			bestellungList.add(bestellung);
			kunde.setAktuelleBestellung(bestellung);
			kunde.setMeineBestellungen(bestellung);
			kunde.getKundeWarenkorb().setGesamtPrise(0);

			return bestellung;
		}

	}

	/**
	 * 
	 * Generiert eine Bestellungsnummer für die aktuelle Bestellung.
	 */
	private void generateBestellungsNr() {
		if (bestellungList.isEmpty()) {
			bestellung.setBestellungsNr(505);
		} else {
			int lastBestellungsNr = bestellungList.get(bestellungList.size() - 1).getBestellungsNr();
			bestellung.setBestellungsNr(lastBestellungsNr + 12);
		}
	}

	/**
	 * 
	 * Gibt die Liste aller Bestellungen zurück.
	 * 
	 * @return Die Liste der Bestellungen.
	 */
	public List<Bestellung> getBestellungList() {
		return bestellungList;
	}

	
	/**
	 * 
	 * Setzt die Liste der Bestellungen.
	 * 
	 * @param bestellungList Die Liste der Bestellungen.
	 */
	public void setBestellungList(List<Bestellung> bestellungList) {
		this.bestellungList = bestellungList;
	}

}
