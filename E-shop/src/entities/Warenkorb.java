package entities;

import java.util.HashMap;

public class Warenkorb {

	private HashMap<Artikel, Integer> korbArtikelListe;
	private double gesamtPrise = 0.0;

	/**
	 * @param artikelMap
	 */
	public Warenkorb() {
		this.korbArtikelListe = new HashMap<>();

	}

	/**
	 * @return the artikelMap
	 */
	public HashMap<Artikel, Integer> getKorbArtikelListe() {
		return korbArtikelListe;
	}

	/**
	 * @param artikelMap the artikelMap to set
	 */
	public void setKorbArtikelListe(Artikel art, int anzahl) {
		this.korbArtikelListe.put(art, anzahl);
	}

	/**
	 * @return the gesamtPrise
	 */
	public double getGesamtPrise() {
		return gesamtPrise;
	}

	/**
	 * @param gesamtPrise the gesamtPrise to set
	 */
	public void setGesamtPrise(double gesamtPrise) {
		this.gesamtPrise = gesamtPrise;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Warenkorb: \n-------------- \n");
		int i = 1;
		for (Artikel artikel : korbArtikelListe.keySet()) {

			int anzahlArtikelWK = korbArtikelListe.get(artikel);
			Artikel warenkorpartikel = artikel;
			builder.append(i + ". artikel: " + warenkorpartikel.getName() + ", menge: " + anzahlArtikelWK + "\n");
			i++;
		}

		builder.append("Die Gesamtsumme beträgt: " + gesamtPrise + " € \n");
		return builder.toString();
	}

}
