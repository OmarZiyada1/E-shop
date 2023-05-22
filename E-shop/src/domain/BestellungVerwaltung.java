package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.WarenkorbLeerException;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;

public class BestellungVerwaltung {
	private Bestellung bestellung;
	private List<Bestellung> bestellungList = new Vector<>();
	private WarenkorbVerwaltung korbVW = new WarenkorbVerwaltung();
	private ArtikelVerwaltung artikelVW = new ArtikelVerwaltung();

	public BestellungVerwaltung() {

	}

	public Bestellung bestellen(Kunde kunde) throws AnzahlIsNichtDefiniertException, WarenkorbLeerException {
		HashMap<Artikel, Integer> artikelnInWarenkorbList = kunde.getKundeWarenkorb().getKorbArtikelListe();
		
		if (artikelnInWarenkorbList.isEmpty()) {
			throw new WarenkorbLeerException();
		} else {
			for (Artikel artikel : artikelnInWarenkorbList.keySet()) {
				Artikel warenkorpartikel = artikel;
				int anzahlArtikelWK = artikelnInWarenkorbList.get(artikel);
				artikelVW.bestandSenken(warenkorpartikel, anzahlArtikelWK);
				
			}
			
			
			bestellung = new Bestellung(kunde, artikelnInWarenkorbList, kunde.getKundeWarenkorb().getGesamtPrise());
			
			if (bestellungList.isEmpty()) {
				bestellung.setBestellungsNr(505);
			} else {
				int lastBestellungsNr = bestellungList.get(bestellungList.size() - 1).getBestellungsNr();
				bestellung.setBestellungsNr(lastBestellungsNr + 12);
			}
			bestellungList.add(bestellung);
		
			kunde.setAktuelleBestellung(bestellung);

			kunde.setMeineBestellungen(bestellungList);
			
			kunde.getKundeWarenkorb().setGesamtPrise(0);
			
			
			
			
			return bestellung;
		}
		
	}

}
