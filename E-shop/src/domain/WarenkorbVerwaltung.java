package domain;

import java.util.HashMap;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import entities.Artikel;
import entities.Kunde;
import entities.Warenkorb;

/**
 * 
 * Die Klasse WarenkorbVerwaltung verwaltet den Warenkorb eines Kunden.
 */
public class WarenkorbVerwaltung {

	/**
	 * 
	 * F�gt einen Artikel mit der angegebenen Anzahl zum Warenkorb des Kunden hinzu.
	 * 
	 * @param kunde   Der Kunde, dem der Artikel hinzugef�gt wird.
	 * @param artikel Der Artikel, der hinzugef�gt wird.
	 * @param anzahl  Die Anzahl des Artikels.
	 * @throws NichtGenugArtikelVorhandenException Wenn nicht gen�gend Artikel
	 *                                             vorhanden sind.
	 */
	public void fuegeArtikelInKorbEin(Kunde kunde, Artikel art, int anzahl) throws NichtGenugArtikelVorhandenException {
		int aktuelleMengeInWarenKorb = 0;
		if (kunde.getKundeWarenkorb().getKorbArtikelListe().containsKey(art)) {
			aktuelleMengeInWarenKorb = kunde.getKundeWarenkorb().getKorbArtikelListe().get(art);

		}

		if (art.getBestand() - aktuelleMengeInWarenKorb >= anzahl) {

			if (kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) == null) {
				kunde.setKundeWarenkorb(art, anzahl);
				updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
			} else {
				kunde.setKundeWarenkorb(art, kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) + anzahl);
				updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
			}

		} else {
			throw new NichtGenugArtikelVorhandenException(art);
		}

	}

	/**
	 * 
	 * Entfernt eine bestimmte Anzahl des angegebenen Artikels aus dem Warenkorb des
	 * Kunden.
	 * 
	 * @param kunde  Der Kunde, dem der Artikel entfernt wird.
	 * @param art    Der Artikel, der entfernt wird.
	 * @param anzahl Die Anzahl des Artikels, die entfernt wird.
	 * @throws AnzahlIsNichtDefiniertException Wenn die Anzahl nicht definiert ist.
	 */
	public void entferneArtikelKorbListe(Kunde kunde, Artikel art, int anzahl) throws AnzahlIsNichtDefiniertException {
		if (anzahl < kunde.getKundeWarenkorb().getKorbArtikelListe().get(art)) {
			kunde.setKundeWarenkorb(art, kunde.getKundeWarenkorb().getKorbArtikelListe().get(art) - anzahl);
			updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
		} else if (anzahl == kunde.getKundeWarenkorb().getKorbArtikelListe().get(art)) {
			loescheArtikeVomKorb(kunde, art);
			updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
		}

		else {
			throw new AnzahlIsNichtDefiniertException();
		}

	}

	/**
	 * 
	 * Entfernt einen Artikel vollst�ndig aus dem Warenkorb des Kunden.
	 * 
	 * @param kunde Der Kunde, dem der Artikel entfernt wird.
	 * @param art   Der Artikel, der entfernt wird.
	 */

	public void loescheArtikeVomKorb(Kunde kunde, Artikel art) {
		kunde.getKundeWarenkorb().getKorbArtikelListe().remove(art);
		updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
	}

	/**
	 * 
	 * Leert den Warenkorb des Kunden.
	 * 
	 * @param kunde Der Kunde, dessen Warenkorb geleert wird.
	 */
	public void leereWarenKorb(Kunde kunde) {
		kunde.getKundeWarenkorb().getKorbArtikelListe().clear();
		updadteGesamtprise(kunde.getKundeWarenkorb().getKorbArtikelListe(), kunde);
	}

	/**
	 * 
	 * Aktualisiert den Gesamtpreis des Warenkorbs anhand der enthaltenen Artikel.
	 * 
	 * @param liste Die Liste der Artikel im Warenkorb.
	 * @param kunde Der Kunde, dessen Warenkorb aktualisiert wird.
	 * @return Der neue Gesamtpreis des Warenkorbs.
	 */
	public double updadteGesamtprise(HashMap<Artikel, Integer> liste, Kunde kunde) {
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
	 * 
	 * Gibt den Warenkorb des Kunden zur�ck.
	 * 
	 * @param kunde Der Kunde, dessen Warenkorb zur�ckgegeben wird.
	 * @return Der Warenkorb des Kunden.
	 */
	public Warenkorb getWarenkorb(Kunde kunde) {
		return kunde.getKundeWarenkorb();
	}

}
