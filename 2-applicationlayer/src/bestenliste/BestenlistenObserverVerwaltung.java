package bestenliste;

import java.util.ArrayList;
import java.util.List;

import koordination.PlattformVerwaltung;

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

	public void setNewStat(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		this.newStat = newStat;
		this.spiel = spiel;
		for (BestenlisteObserver observer : this.bestenlisten) {
			observer.update(this.newStat, this.spiel, spielePlattform);
		}
	}
}
