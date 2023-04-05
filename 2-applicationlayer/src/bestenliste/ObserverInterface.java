package bestenliste;

import koordination.PlattformVerwaltung;

public interface ObserverInterface {

	public void update(int newStat, String spiel, PlattformVerwaltung spielePlattform);
}
