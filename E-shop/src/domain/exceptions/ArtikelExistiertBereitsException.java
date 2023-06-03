package domain.exceptions;

import entities.Artikel;


	

public class ArtikelExistiertBereitsException extends Exception {

	private Artikel artikel;

	public ArtikelExistiertBereitsException(Artikel artikel , String zusatzMsg) {
		// TODO Auto-generated constructor stub

		super("Artikel mit Titel " + artikel.getName() + " und Nummer " + artikel.getArtikelId()
		+ " existiert bereits" + zusatzMsg);
		this.artikel = artikel;
	}

	public Artikel getArtikel() {
		return artikel;
	}

}
