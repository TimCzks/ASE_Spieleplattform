package bestenliste;

import java.util.Map;

import koordination.PlattformVerwaltung;

public class BestenlisteObserverSpieleGesamt implements ObserverInterface {

	Bestenliste aktuelleBestenliste = new Bestenliste();

	@Override
	public void update(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		aktuelleBestenliste.ermittleBestenliste();
		if (spiel.equals("Spiele_Gesamt") && newStat > aktuelleBestenliste.getBestenlisteInZahlen().get(3)) {
			aktuelleBestenliste.getBestenlisteAlsStringausdruck().set(3,
					Map.entry(spiel, "Spieler*in mit den meisten insgesamt gespielten Spielen ist "
							+ spielePlattform.getAktuellerUser().getUsername() + " mit " + newStat + " Spielen."));
			uebergebeNeueBestenlisteAn(spielePlattform);
		}

	}

	@Override
	public void uebergebeNeueBestenlisteAn(PlattformVerwaltung spielePlattform) {
		spielePlattform.speichereUserAb();
		spielePlattform.setBestenliste(aktuelleBestenliste);
	}

}
