package domain;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import entities.Bestellung;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import entities.Kunde;

/**
 * 
 * Diese Klasse verwaltet Kundendaten und bietet Funktionen zur
 * Kundenverwaltung.
 */
public class KundeVerwaltung {
	private List<Kunde> list_Kunde = new Vector<Kunde>(); // list mit alle Kunden Mitarbeiter

	/**
	 * 
	 * Registriert einen neuen Kunden.*
	 * 
	 * @param kunde Der zu registrierende Kunde.
	 * @throws KundeIDistbenutztException Wenn die Kunden-ID bereits verwendet wird.
	 */
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

	/**
	 * 
	 * Generiert eine eindeutige Kunden-ID für den übergebenen Kunden.*
	 * 
	 * @param kunde Der Kunde, für den eine ID generiert werden soll.
	 */
	private void generateKundenId(Kunde kunde) {
		if (list_Kunde.isEmpty()) {
			kunde.setKndNr(100);
		} else {
			int lastKundenNr = list_Kunde.get(list_Kunde.size() - 1).getKndNr();
			kunde.setKndNr(lastKundenNr + 2);
		}
	}

	/**
	 * 
	 * Meldet einen Kunden mit den angegebenen Anmeldeinformationen an.*
	 * 
	 * @param nutzerName Der Nutzername des Kunden.
	 * @param passwort   Das Passwort des Kunden.
	 * @return Der angemeldete Kunde.
	 * @throws NutzernameOderPasswortFalschException Wenn der Nutzername oder das
	 *                                               Passwort falsch sind.
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

	public void kundeAusloggen(Kunde kunde) {
		kunde = null;
	}

	/**
	 * 
	 * Gibt die Liste der Kunden zurück.*
	 * 
	 * @return Die Liste der Kunden.
	 */
	public List<Kunde> getList_Kunde() {
		return list_Kunde;
	}

	/**
	 * 
	 * Gibt die Bestellungen des angegebenen Kunden zurück.*
	 * 
	 * @param kunde Der Kunde, dessen Bestellungen abgerufen werden sollen.
	 * @return Eine Liste der Bestellungen des Kunden.
	 */
	public List<Bestellung> getMeineBestellungen(Kunde kunde) {
		return kunde.getMeineBestellungen();
	}

}
