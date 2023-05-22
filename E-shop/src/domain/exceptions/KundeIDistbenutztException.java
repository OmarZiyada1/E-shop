package domain.exceptions;

import entities.Kunde;


public class KundeIDistbenutztException extends Exception{


	// TODO Auto-generated constructor stub
	Kunde kunde;
	public KundeIDistbenutztException(Kunde kunde, String zusatzMsg) {


		super("Kunde Id " + kunde.getKndNr()+"oder Kunde Nutzername " + kunde.getNutzerName()+ " existiert bereits" + zusatzMsg);
		this.kunde = kunde;
	}

	public Kunde getKunde() {
		return kunde;
	}
}


