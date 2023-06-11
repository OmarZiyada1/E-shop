package domain.exceptions;

import entities.Artikel;

public class ArtikelExistiertNichtException extends Exception {
	private Artikel artikel;
		public ArtikelExistiertNichtException(Artikel artikel , String zusatzMsg) {
			super("Artikel mit Name '" + artikel.getName() + "'" + " existiert nicht" + zusatzMsg);
			this.artikel = artikel;
		}

		
	
	public Artikel getArtikel() {
		return artikel;
	}

}
