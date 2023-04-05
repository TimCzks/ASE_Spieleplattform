package bestenliste;

import java.util.Map;

import koordination.PlattformVerwaltung;

public class BestenlisteObserverSSP implements ObserverInterface {

	Bestenliste aktuelleBestenliste = new Bestenliste();

	@Override
	public void update(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		aktuelleBestenliste.ermittleBestenliste();
		if (spiel.equals("'Schere, Stein, Papier'") && newStat > aktuelleBestenliste.getBestenlisteInZahlen().get(2)) {
			aktuelleBestenliste.getBestenlisteAlsStringausdruck().set(2,
					Map.entry(spiel, "Beste*r Spieler*in in " + spiel + " ist "
							+ spielePlattform.getAktuellerUser().getUsername() + " mit " + newStat + " Siegen."));
			uebergebeNeueBestenlisteAn(spielePlattform);
		}
		if (spiel.equals("'Schere, Stein, Papier'N") && newStat > aktuelleBestenliste.getBestenlisteInZahlen().get(3)) {
			aktuelleBestenliste.getBestenlisteAlsStringausdruck().set(3,
					Map.entry(spiel + "N", "Schlechteste*r Spieler*in in " + spiel + " ist "
							+ spielePlattform.getAktuellerUser().getUsername() + " mit " + newStat + " Niederlagen."));
			uebergebeNeueBestenlisteAn(spielePlattform);
		}
	}

	@Override
	public void uebergebeNeueBestenlisteAn(PlattformVerwaltung spielePlattform) {
		spielePlattform.speichereUserAb();
		spielePlattform.setBestenliste(aktuelleBestenliste);
	}

}
