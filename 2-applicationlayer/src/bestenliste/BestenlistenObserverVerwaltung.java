package bestenliste;

import java.util.ArrayList;
import java.util.List;

public class BestenlistenObserverVerwaltung {

	private int newStat;
	private String spiel;

	private List<BestenlisteObserver> bestenlisten = new ArrayList<>();

	public void addBeobachter(BestenlisteObserver bestenliste) {
		this.bestenlisten.add(bestenliste);
	}

	public void removeBeobachter(BestenlisteObserver bestenliste) {
		this.bestenlisten.remove(bestenliste);
	}

	public void setNewStat(int newStat, String spiel) {
		this.newStat = newStat;
		this.spiel = spiel;
		for (BestenlisteObserver bestenliste : this.bestenlisten) {
			bestenliste.update(this.newStat, spiel);
		}
	}
}
