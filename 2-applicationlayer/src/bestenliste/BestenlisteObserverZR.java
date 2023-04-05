package bestenliste;

import java.util.Map;

import koordination.PlattformVerwaltung;

public class BestenlisteObserverZR implements ObserverInterface {

	Bestenliste aktuelleBestenliste = new Bestenliste();

	@Override
	public void update(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		aktuelleBestenliste.ermittleBestenliste();
		if (spiel.equals("'Zahlenraten'") && newStat < aktuelleBestenliste.getBestenlisteInZahlen().get(1)) {
			aktuelleBestenliste.getBestenlisteAlsStringausdruck().set(1,
					Map.entry(spiel, "Beste*r Spieler*in in " + spiel + " ist "
							+ spielePlattform.getAktuellerUser().getUsername() + " mit " + newStat + " Versuchen."));
			uebergebeNeueBestenlisteAn(spielePlattform);
		}
	}

	@Override
	public void uebergebeNeueBestenlisteAn(PlattformVerwaltung spielePlattform) {
		spielePlattform.speichereUserAb();
		spielePlattform.setBestenliste(aktuelleBestenliste);
	}

}
