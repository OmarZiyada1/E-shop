package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;


import entities.Bestellung;
import entities.Rechnung;

public class RechnungsVerwaltung {
	private Rechnung rechnung;
	private List<Rechnung> RechnungenList = new Vector<>();
	private LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",
			Locale.GERMANY);
	private String formattedDatumZeit;

	public RechnungsVerwaltung() {

	}

	public Rechnung erstelleRechnung(Bestellung bestellung) {

		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		
		rechnung = new Rechnung(bestellung, formattedDatumZeit);
		generateRechnungsNr();
		RechnungenList.add(rechnung);
		bestellung.setRechnung(rechnung);
		return rechnung;
	}
	
	public void updateTime(){
		aktuelleDatumZeit = LocalDateTime.now();
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
