package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;
import entities.Rechnung;

public class RechnungsVerwaltung {
	private Rechnung rechnung;
	private List<Bestellung> RechnungenList = new Vector<>();


	public RechnungsVerwaltung() {

	}



	public Rechnung erstelleRechnung(Bestellung bestellung) {
		
		rechnung = new Rechnung(bestellung);
		if (RechnungenList.isEmpty()) {
			rechnung.setRechnungNr(303);
		} else {
			int lastRechnungNr = RechnungenList.get(RechnungenList.size() - 1).getBestellungsNr();
			rechnung.setRechnungNr(lastRechnungNr + 34);
		}
		return rechnung;
	}
}
