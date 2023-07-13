package bib.client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.E_ShopInterface;
import bib.common.entities.Buch;

/**
 * Klasse mit Fassade der Bibliothek auf Client-Seite.
 * Die Klasse stellt die von der GUI erwarteten Methoden zur Verfügung
 * und realisiert (transparent für die GUI) die Kommunikation mit dem 
 * Server.
 * Anmerkung: Auf dem Server wird dann die eigentliche, von der lokalen
 * Bibliotheksversion bekannte Funktionalität implementiert (z.B. Bücher 
 * einfügen und suchen)
 * 
 * @author teschke
 */
public class BibliotheksFassade implements E_ShopInterface {

	// Datenstrukturen für die Kommunikation
	private Socket socket = null;
	private BufferedReader sin; // server-input stream
	private PrintStream sout; // server-output stream
	
	
	/**
	 * Konstruktor, der die Verbindung zum Server aufbaut (Socket) und dieser
	 * Grundlage Eingabe- und Ausgabestreams für die Kommunikation mit dem
	 * Server erzeugt.
	 * 
	 * @param host Rechner, auf dem der Server läuft
	 * @param port Port, auf dem der Server auf Verbindungsanfragen warten
	 * @throws IOException
	 */
	public BibliotheksFassade(String host, int port) throws IOException {
		try {
			// Socket-Objekt fuer die Kommunikation mit Host/Port erstellen
			socket = new Socket(host, port);

			// Stream-Objekt fuer Text-I/O ueber Socket erzeugen
			InputStream is = socket.getInputStream();
			sin = new BufferedReader(new InputStreamReader(is));
			sout = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Fehler beim Socket-Stream öffnen: " + e);
			// Wenn im "try"-Block Fehler auftreten, dann Socket schließen:
			if (socket != null)
				socket.close();
			System.err.println("Socket geschlossen");
			System.exit(0);
		}
		
		// Verbindung erfolgreich hergestellt: IP-Adresse und Port ausgeben
		System.err.println("Verbunden: " + socket.getInetAddress() + ":"
				+ socket.getPort());	

		// Begrüßungsmeldung vom Server lesen
		String message = sin.readLine();
		System.out.println(message);
	}

	/**
	 * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
	 * 
	 * @return Liste aller Bücher im Bestand der Bibliothek
	 */
	public List<Buch> gibAlleBuecher() {
		List<Buch> liste = new ArrayList<Buch>();

		// Kennzeichen für gewählte Aktion senden
		sout.println("a");

		// Antwort vom Server lesen und im info-Feld darstellen:
		String antwort = "?";
		try {
			// Anzahl gefundener Bücher einlesen
			antwort = sin.readLine();
			int anzahl = Integer.parseInt(antwort);
			for (int i=0; i<anzahl; i++) {
				// Buch vom Server lesen ...
				Buch buch = liesBuchVonServer();
				// ... und in Liste eintragen
				liste.add(buch);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return liste;
	}

	/**
	 * Methode zum Suchen von Büchern anhand des Titels. Es wird eine Liste von Büchern
	 * zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel enthält.
	 * 
	 * @param titel Titel des gesuchten Buchs
	 * @return Liste der gefundenen Bücher (evtl. leer)
	 */
	public List<Buch> sucheNachTitel(String titel) {
		List<Buch> liste = new ArrayList<Buch>();
		
		// Kennzeichen für gewählte Aktion senden
		sout.println("f");
		// Parameter für Aktion senden
		sout.println(titel);

		// Antwort vom Server lesen und im info-Feld darstellen:
		String antwort = "?";
		try {
			// Anzahl gefundener Bücher einlesen
			antwort = sin.readLine();
			int anzahl = Integer.parseInt(antwort);
			for (int i=0; i<anzahl; i++) {
				// Buch vom Server lesen ...
				Buch buch = liesBuchVonServer();
				// ... und in Liste eintragen
				liste.add(buch);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return liste;
	}

	private Buch liesBuchVonServer() throws IOException {
		String antwort;
		// Nummer von Buch i einlesen
		antwort = sin.readLine();
		int nummer = Integer.parseInt(antwort);
		// Titel von Buch i einlesen
		String buchTitel = sin.readLine();
		// Verfügbarkeit von Buch i einlesen
		antwort = sin.readLine();
		boolean verfuegbar = Boolean.parseBoolean(antwort);
		
		// Neues Buch-Objekt erzeugen...
		Buch buch = new Buch(buchTitel, nummer, verfuegbar);
		return buch;
	}

	/**
	 * Methode zum Einfügen eines neuen Buchs in den Bestand. 
	 * Wenn das Buch bereits im Bestand ist, wird der Bestand nicht geändert.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 * @returns Buch-Objekt, das im Erfolgsfall eingefügt wurde
	 * @throws BuchExistiertBereitsException wenn das Buch bereits existiert
	 */
	public Buch fuegeBuchEin(String titel, int nummer) throws BuchExistiertBereitsException {
		// Kennzeichen für gewählte Aktion senden
		sout.println("e");
		// Parameter für Aktion senden
		sout.println(nummer);
		sout.println(titel);

		// Antwort vom Server lesen:
		String antwort = "Fehler";
		try {
			antwort = sin.readLine();
			if (antwort.equals("Erfolg")) {
				// Eingefügtes Buch vom Server lesen ...
				Buch buch = liesBuchVonServer();
				// ... und zurückgeben
				return buch;
			} else {
				// Fehler: Exception (re-)konstruieren
				String message = sin.readLine();
				throw new BuchExistiertBereitsException(message);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. 
	 * Es wird nur das erste Vorkommen des Buchs gelöscht.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 */
	public void loescheBuch(String titel, int nummer) {
		// Kennzeichen für gewählte Aktion senden
		sout.println("d");
		// Parameter für Aktion senden
		sout.println(nummer);
		sout.println(titel);

		// Antwort vom Server lesen:
		String antwort;
		try {
			antwort = sin.readLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		System.out.println(antwort);
	}

	/**
	 * Methode zum Speichern des Buchbestands in einer Datei.
	 * 
	 * @throws IOException
	 */
	public void schreibeBuecher() throws IOException {
		// Kennzeichen für gewählte Aktion senden
		sout.println("s");
		// (Parameter sind hier nicht zu senden)

		// Antwort vom Server lesen:
		String antwort = "Fehler";
		try {
			antwort = sin.readLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		System.out.println(antwort);
	}

	@Override
	public void disconnect() throws IOException {
		// Kennzeichen für gewählte Aktion senden
		sout.println("q");
		// (Parameter sind hier nicht zu senden)

		// Antwort vom Server lesen:
		String antwort = "Fehler";
		try {
			antwort = sin.readLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		System.out.println(antwort);
	}

	// TODO: Weitere Funktionen der Bibliotheksverwaltung, z.B. ausleihen, zurückgeben etc.
	// ...
}
