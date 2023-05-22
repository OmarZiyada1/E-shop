package domain;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import entities.Adresse;
import entities.Bestellung;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Warenkorb;

public class KundeVerwaltung {
	private List<Kunde> list_Kunde = new Vector<Kunde>();

	public KundeVerwaltung() {
	}

	public void kundeRegistieren(Kunde kunde) throws KundeIDistbenutztException {
		
		Iterator<Kunde> iter = list_Kunde.iterator();

		while (iter.hasNext()) {
			Kunde ku = iter.next();
			if (ku.getNutzerName().equals(kunde.getNutzerName()) || ku.getKndNr() == kunde.getKndNr()) {
				throw new KundeIDistbenutztException(kunde, "in Mitarbeiter einfuegen()");
			}
		}

		generateKundenId(kunde);

		list_Kunde.add(kunde);
	}

	private void generateKundenId(Kunde kunde) {
		if (list_Kunde.isEmpty()) {
			kunde.setKndNr(100);
		} else {
			int lastKundenNr = list_Kunde.get(list_Kunde.size() - 1).getKndNr();
			kunde.setKndNr(lastKundenNr + 2);
		}
	}

	// TODO Auto-generated constructor stub

	/**
	 * Registriert einen neuen Nutzer mit den angegebenen Informationen.
	 * 
	 * @param name     Der Nachname des Nutzers.
	 * @param vorName  Der Vorname des Nutzers.
	 * @param nutzerNr Die eindeutige Nutzernummer des Nutzers.
	 * @param passwort Das Passwort des Nutzers.
	 * @param adresse  Die Adresse des Nutzers.
	 * @param rolle    Die Rolle des Nutzers (z.B. 'k' für Kunde, 'm' für
	 *                 Mitarbeiter, 'a' für admin).
	 */

	/**
	 * Loggt den Nutzer mit den angegebenen Anmeldedaten ein.
	 * 
	 * @param nutzerNr Die eindeutige Nutzernummer des Nutzers.
	 * @param passwort Das Passwort des Nutzers.
	 * @return true, wenn die Anmeldung erfolgreich war, andernfalls false.
	 */
	public Kunde kundeEinloggen(String nutzerName, String passwort) throws NutzernameOderPasswortFalschException {
		// TODO: Implementiere die Methode "einloggen" für den Nutzer.
		ListIterator<Kunde> iter = list_Kunde.listIterator();

		Kunde kunde = null;
		boolean treffer = false;
		while (iter.hasNext()) {
			Kunde ku = iter.next();
			if (ku.getNutzerName().equals(nutzerName) && ku.getPasswort().equals(passwort)) {
				kunde = ku;
				treffer = true;
				break;
			}
		}
		if (!treffer || list_Kunde.isEmpty()) {
			throw new NutzernameOderPasswortFalschException();
		}

		return kunde;

	}

	public List<Kunde> getList_Kunde() {
		return list_Kunde;
	}
	
	public List<Bestellung> getMeineBestellungen(Kunde kunde){
		return kunde.getMeineBestellungen();
	}


	
	
	

}
