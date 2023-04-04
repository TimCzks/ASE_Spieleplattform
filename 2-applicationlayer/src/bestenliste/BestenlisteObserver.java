package bestenliste;

public class BestenlisteObserver implements ObserverInterface {

	@Override
	public void update(int newStat, String spiel) {
		// Wenn neuer Stat höher, als der entsprechende Wert der bestenlisteInZahlen
		// (des entsprechenden Spiels),
		// dann:
		// -> Speichere User ab und baue neues Objekt einer Bestenliste, das an die
		// PlattformVerwaltung übergeben wird
		// -> Dadurch wird die bestenliste neu ermittelt
		// if(spiel.equals("ggm") && newStat > bestenlisteInZahlen.get(0)) ->
		// speichereUserAb etc
	}

}
