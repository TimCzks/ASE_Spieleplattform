package spiele;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.code.User;
import koordination.KonverterFactory;
import koordination.KonverterInterface;

public class Galgenmaennchen {

	String loesungswort;
	List<Character> checkObSieg = new LinkedList<>();
	List<Character> wortUmgewandelt = new LinkedList<>();
	List<Character> wort = new LinkedList<>();
	List<Character> richtigeEingaben = new LinkedList<>();
	List<Character> falscheEingaben = new LinkedList<>();

	public void initGame(Scanner sc, User user) {
		beforeGame();
		System.out.println(
				"Willkommen zu Galgenmännchen!\nZum Starten gib einen Buchstaben von 'A'-'Z' an. (Großschreibung beachten!)"
						+ "\nMöchtest du das Spiel verlassen, gib jederzeit 'EXIT' ein.\n\nJetzt viel Spaß, wie lautet dein erster Buchstabe?");
		boolean gameInProgress = true;
		int lastingTries = 10;
		lastingTries = gameOn(sc, gameInProgress, lastingTries);
		validateEnding(user, lastingTries);
	}

	private void beforeGame() {
		wortUmgewandelt.clear();
		richtigeEingaben.clear();
		falscheEingaben.clear();
		createLoesungswort();
		wort = loesungswort.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}

	private int gameOn(Scanner sc, boolean gameInProgress, int lastingTries) {
		CharSequence guess;
		char guessedChar;
		do {
			guess = sc.next();
			guessedChar = guess.charAt(0);
			if (guessedChar != '?' && wort.contains(guessedChar)) {
				displayCurrentStatus(guessedChar);
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

	private void validateEnding(User user, int lastingTries) {
		if (lastingTries > 0) {
			user.getStats().setSiegeGGM(user.getStats().getSiegeGGM() + 1);
			user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
			System.out.println("\nGlückwunsch, du hast gewonnen!");
		} else {
			user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
			System.out.println("\nDu hast leider verloren. Das gesuchte Wort war " + loesungswort + ".");
		}
	}

	private void createLoesungswort() {
		KonverterInterface konv = new KonverterFactory().getKonverter();
		loesungswort = konv.ermittleLoesungswort();
		for (int i = 0; i < loesungswort.length(); i++) {
			checkObSieg.add('?');
			wortUmgewandelt.add('_');
		}
	}

	private void displayCurrentStatus(char guess) {
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

}
