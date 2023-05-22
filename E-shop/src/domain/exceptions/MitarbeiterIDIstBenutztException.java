package domain.exceptions;


import entities.Mitarbeiter;

public class MitarbeiterIDIstBenutztException extends Exception {
	Mitarbeiter mitarbeiter;
	public MitarbeiterIDIstBenutztException(Mitarbeiter mitarbeiter, String zusatzMsg) {
	

				super("Mitarbeiter Id " + mitarbeiter.getMaId()+" oder Mitarbeiter Nutzername " + mitarbeiter.getNutzerName()+ " existiert bereits" + zusatzMsg);
				this.mitarbeiter = mitarbeiter;
				
	}
	
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

}
