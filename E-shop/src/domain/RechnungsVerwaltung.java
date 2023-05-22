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
	private List<Rechnung> RechnungenList = new Vector<>();

	public RechnungsVerwaltung() {

	}

	public Rechnung erstelleRechnung(Bestellung bestellung) {

		rechnung = new Rechnung(bestellung);
		generateRechnungsNr();
		RechnungenList.add(rechnung);
		bestellung.setRechnung(rechnung);
		return rechnung;
	}
	

	private void generateRechnungsNr() {
		if (RechnungenList.isEmpty()) {
			rechnung.setRechnungNr(303);
		} else {
			int lastRechnungNr = RechnungenList.get(RechnungenList.size() - 1).getRechnungNr();
			rechnung.setRechnungNr(lastRechnungNr + 34);
		}
	}
	
	
	public List<Rechnung> getRechnungenList() {
	//Sex with condom is the Best.  Sudki Koulak
		return RechnungenList;
	}



	public void setRechnungenList(List<Rechnung> rechnungenList) {
		RechnungenList = rechnungenList;
	}
}
