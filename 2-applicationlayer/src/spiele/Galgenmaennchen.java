package spiele;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import koordination.KonverterFactory;
import koordination.KonverterInterface;

public class Galgenmaennchen {

	private String loesungswort;
	private int lastingTries = 10;
	private Scanner sc;
	private List<Character> checkObSieg = new LinkedList<>();
	private List<Character> wortUmgewandelt = new LinkedList<>();
	private List<Character> wort = new LinkedList<>();
	private List<Character> richtigeEingaben = new LinkedList<>();
	private List<Character> falscheEingaben = new LinkedList<>();

	public Galgenmaennchen(Scanner sc) {
		super();
		this.sc = sc;
	}

	public void starteSpiel() {
		clearVariablesBeforeGame();
		System.out.println(
				"Willkommen zu Galgenmännchen!\nZum Starten gib einen Buchstaben von 'A'-'Z' an. (Großschreibung beachten!)"
						+ "\nMöchtest du das Spiel verlassen, gib jederzeit 'EXIT' ein.\n\nJetzt viel Spaß, wie lautet dein erster Buchstabe?");
		boolean gameInProgress = true;
		lastingTries = spielAblaufBisEnde(gameInProgress);
	}

	public void clearVariablesBeforeGame() {
		wortUmgewandelt.clear();
		richtigeEingaben.clear();
		falscheEingaben.clear();
		checkObSieg.clear();
		lastingTries = 10;
		createLoesungswort();
		wort = loesungswort.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}

	public int spielAblaufBisEnde(boolean gameInProgress) {
		CharSequence guess;
		char guessedChar;
		do {
			guess = sc.next();
			guessedChar = guess.charAt(0);
			if (guessedChar != '?' && wort.contains(guessedChar)) {
				zeigeAktuellenFortschrittAn(guessedChar);
				if (wort.equals(checkObSieg)) {
					gameInProgress = false;
				}
			} else {
				falscheEingaben.add(guessedChar);
				lastingTries -= 1;
				System.out.println("Falsch geraten. Noch " + lastingTries + " Versuche!" + " Bisherige Versuche:\n"
						+ falscheEingaben.toString() + " Aktueller Fortschritt: " + wortUmgewandelt.toString());
			}
		} while (gameInProgress && lastingTries > 0);
		return lastingTries;
	}

	private void createLoesungswort() {
		KonverterInterface konv = new KonverterFactory().getKonverter();
		setLoesungswort(konv.ermittleLoesungswort());
		for (int i = 0; i < loesungswort.length(); i++) {
			checkObSieg.add('?');
			wortUmgewandelt.add('_');
		}
	}

	private void zeigeAktuellenFortschrittAn(char guess) {
		int index;
		while (wort.indexOf(guess) >= 0) {
			index = wort.indexOf(guess);
			wort.remove(index);
			wort.add(index, '?');
			wortUmgewandelt.remove(index);
			wortUmgewandelt.add(index, guess);
		}
		System.out.println("Richtig geraten, aktueller Fortschitt: " + wortUmgewandelt.toString());
	}

	public int validiereSpielergebnis(int lastingTries) {
		if (lastingTries > 0) {
			System.out.println("\nGlückwunsch, du hast gewonnen!");
			return 1;
		} else {
			System.out.println("\nDu hast leider verloren. Das gesuchte Wort war " + loesungswort + ".");
			return 0;
		}
	}

	public void setLoesungswort(String loesungswort) {
		this.loesungswort = loesungswort;
	}

	public int getLastingTries() {
		return lastingTries;
	}

}
