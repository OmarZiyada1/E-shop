package domain.exceptions;

import entities.Artikel;

public class NichtGenugArtikelVorhandenException extends Exception {

	public NichtGenugArtikelVorhandenException(Artikel art) {
		super("Die gewählte Menge von den " + art.getName()
				+ " ist leider nicht vorhanden oder die Artikel ist Ausverkauft. Sie können max. " + art.getBestand()
				+ " Stück wählen");

	}

}
