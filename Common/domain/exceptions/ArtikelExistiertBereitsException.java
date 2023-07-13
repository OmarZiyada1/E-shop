package domain.exceptions;

import entities.Artikel;


	

public class ArtikelExistiertBereitsException extends Exception {

	private Artikel artikel;

	public ArtikelExistiertBereitsException(Artikel artikel , String zusatzMsg) {
		// TODO Auto-generated constructor stub

		super("Artikel mit Name '" + artikel.getName() + "'" + " existiert bereits" + zusatzMsg);
		this.artikel = artikel;
	}

	public Artikel getArtikel() {
		return artikel;
	}

}
