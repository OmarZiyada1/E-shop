package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.WarenkorbLeerException;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;

public class BestellungVerwaltung {
	
	private  LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",
			Locale.GERMANY);
	private String formattedDatumZeit;
	private Bestellung bestellung;
	private List<Bestellung> bestellungList = new Vector<>();
	private ArtikelVerwaltung artikelVW = new ArtikelVerwaltung();
	


	public BestellungVerwaltung() {

	}
	
	public void updateTime(){
		aktuelleDatumZeit = LocalDateTime.now();
	}

	public Bestellung bestellen(Kunde kunde) throws  WarenkorbLeerException {
		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		HashMap<Artikel, Integer> artikelnInWarenkorbList = kunde.getKundeWarenkorb().getKorbArtikelListe();
		
		if (artikelnInWarenkorbList.isEmpty()) {
			throw new WarenkorbLeerException();
		} else {
			for (Artikel artikel : artikelnInWarenkorbList.keySet()) {
				Artikel warenkorpartikel = artikel;
				int anzahlArtikelWK = artikelnInWarenkorbList.get(artikel);
				artikelVW.bestandSenken(warenkorpartikel, anzahlArtikelWK);
			}

			bestellung = new Bestellung(kunde, artikelnInWarenkorbList, kunde.getKundeWarenkorb().getGesamtPrise(), formattedDatumZeit);
			generateBestellungsNr();
			bestellungList.add(bestellung);
			kunde.setAktuelleBestellung(bestellung);
			kunde.setMeineBestellungen(bestellung);
			kunde.getKundeWarenkorb().setGesamtPrise(0);

			return bestellung;
		}

	}

	private void generateBestellungsNr() {
		if (bestellungList.isEmpty()) {
			bestellung.setBestellungsNr(505);
		} else {
			int lastBestellungsNr = bestellungList.get(bestellungList.size() - 1).getBestellungsNr();
			bestellung.setBestellungsNr(lastBestellungsNr + 12);
		}
	}
	
	public List<Bestellung> getBestellungList() {
		return bestellungList;
	}

	public void setBestellungList(List<Bestellung> bestellungList) {
		this.bestellungList = bestellungList;
	}


}
