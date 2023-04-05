package bestenliste;

import java.util.ArrayList;
import java.util.List;

import koordination.PlattformVerwaltung;

public class BestenlistenObserverVerwaltung {

	private int newStat;
	private String spiel;

	private List<ObserverInterface> observerListe = new ArrayList<>();

	public void addBeobachter(ObserverInterface observer) {
		this.observerListe.add(observer);
	}

	public void removeBeobachter(ObserverInterface observer) {
		this.observerListe.remove(observer);
	}

	public void setNewStat(int newStat, String spiel, PlattformVerwaltung spielePlattform) {
		this.newStat = newStat;
		this.spiel = spiel;
		for (ObserverInterface observer : this.observerListe) {
			observer.update(this.newStat, this.spiel, spielePlattform);
		}
	}
}
