package bestenliste;

import java.util.Map;

import koordination.PlattformVerwaltung;

public class BestenlisteObserverGGM implements ObserverInterface {

	Bestenliste aktuelleBestenliste = new Bestenliste();

	@Override
	public void update(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		aktuelleBestenliste.ermittleBestenliste();
		if (spiel.equals("'Galgenmännchen'") && newStat > aktuelleBestenliste.getBestenlisteInZahlen().get(0)) {
			aktuelleBestenliste.getBestenlisteAlsStringausdruck().set(0,
					Map.entry(spiel, "Beste*r Spieler*in in " + spiel + " ist "
							+ spielePlattform.getAktuellerUser().getUsername() + " mit " + newStat + " Siegen."));
			uebergebeNeueBestenlisteAn(spielePlattform);
		}

	}

	@Override
	public void uebergebeNeueBestenlisteAn(PlattformVerwaltung spielePlattform) {
		spielePlattform.speichereUserAb();
		spielePlattform.setBestenliste(getAktuelleBestenliste());
	}

	public Bestenliste getAktuelleBestenliste() {
		return aktuelleBestenliste;
	}

	public void setAktuelleBestenliste(Bestenliste bestenliste) {
		this.aktuelleBestenliste = bestenliste;
	}

}
