package domain;

import java.util.HashMap;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import entities.Artikel;
import entities.Kunde;
import entities.Warenkorb;

public class WarenkorbVerwaltung {

	ArtikelVerwaltung artVW;
	private Warenkorb warenkorb;

	

	public void fuegeArtikelInKorbEin(Kunde kunde, Artikel art, int anzahl) throws NichtGenugArtikelVorhandenException {
		// nicht mehr wählen als was wir haben
		if (art.getBestand() >= anzahl) {

			if (kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) == null) {
				kunde.setKundeWarenkorb(art, anzahl);
				UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
			} else {
				kunde.setKundeWarenkorb(art, kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) + anzahl);
				UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
			}

		} else {
			throw new NichtGenugArtikelVorhandenException(art);
		}

	}

	public void entferneArtikelKorbListe(Kunde kunde, Artikel art, int anzahl) throws AnzahlIsNichtDefiniertException {
		if (anzahl < kunde.getKundeWarenkorb().getKorbArtikelListe().get(art)) {
			kunde.setKundeWarenkorb(art, kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) - anzahl);
			UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
		} else if (anzahl == kunde.getKundeWarenkorb().getKorbArtikelListe().get(art)) {
			loescheArtikeVomKorb(kunde, art);
			UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
		}

		else {
			throw new AnzahlIsNichtDefiniertException();
		}

	}

	public void loescheArtikeVomKorb(Kunde kunde, Artikel art) {
		kunde.getKundeWarenkorb().getKorbArtikelListe().remove(art);
		UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
	}

	public void leereWarenKorb(Kunde kunde) {
		kunde.getKundeWarenkorb().getKorbArtikelListe().clear();
		UpdadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
	}

	public double UpdadteGesamtprise(HashMap<Artikel, Integer> liste, Kunde kunde) {
		double gesamtPreis = 0;
		for (Artikel artikel : liste.keySet()) {
			int anzahlArtikelWK = liste.get(artikel);
			double preis = artikel.getPreis();
			gesamtPreis += anzahlArtikelWK * preis;
			kunde.getKundeWarenkorb().setGesamtPrise(gesamtPreis);

		}
		return gesamtPreis;
	}

	/**
	 * @return the warenkorb
	 */
	public String getWarenkorb(Kunde kunde) {
		return kunde.getKundeWarenkorb().toString();
	}

}
