package spiele;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import koordination.KonverterFactory;
import koordination.KonverterInterface;

public class Galgenmaennchen {

	String loesungswort;
	List<Character> eingaben = new LinkedList<>();

	public void initGame(Scanner sc) {
		loesungswort = "";
		eingaben.clear();
		createLoesungswort();
		System.out.println("Willkommen zu Galgenmännchen!\nZum Starten gib einen Buchstaben von 'A'-'Z' an."
				+ "\nMöchtest du das Spiel verlassen, gib jederzeit 'EXIT' ein.\nJetzt viel Spaß, wie lautet dein erster Buchstabe?");
		boolean gameInProgress = true;
		do {
			CharSequence guess = sc.next();
			if (loesungswort.contains(guess)) {
				System.out.println("Richtig geraten.");
				loesungswort.replace(guess.charAt(0), '?');
			} else {
				eingaben.add(guess.charAt(0));
			}

		} while (gameInProgress);

	}

	private void createLoesungswort() {
		KonverterInterface konv = new KonverterFactory().getKonverter();
		loesungswort = konv.ermittleLoesungswort();
	}

}
