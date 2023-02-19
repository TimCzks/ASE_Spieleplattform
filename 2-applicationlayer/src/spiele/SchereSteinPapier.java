package spiele;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domain.code.User;

public class SchereSteinPapier {

	private final List<String> auswahlListe = Arrays.asList(new String[] { "SCHERE", "STEIN", "PAPIER" });
	private final String UNENTSCHIEDEN = ". Unentschieden! Neuer Versuch?";
	private final String NIEDERLAGE = ". Niederlage! Neuer Versuch?";
	private final String SIEG = ". Sieg! Neuer Versuch?";
	private Scanner sc;
	private User user;
	private int siege, niederlagen, unentschieden;
	private String auswahl;

	public SchereSteinPapier(Scanner sc, User user) {
		super();
		this.sc = sc;
		this.user = user;
	}

	private void startGame() {
		beforeGame();
		System.out.println(
				"Willkommen bei Schere, Stein, Papier!\nZum Starten gib entweder 'SCHERE', 'STEIN' oder 'PAPIER' ein, "
						+ "sobald du nicht mehr weiterspielen willst, gib 'EXIT' ein.");
		boolean inGame = true;
		Random r = new Random();
		do {
			auswahl = auswahlListe.get(r.nextInt(3));
			switch (sc.next()) {
			case "SCHERE":
				gameRule("SCHERE", "STEIN");
				break;
			case "STEIN":
				gameRule("STEIN", "PAPIER");
				break;
			case "PAPIER":
				gameRule("PAPIER", "SCHERE");
				break;
			case "EXIT":
				inGame = false;
				System.out.println("Du hast folgendes erzielt: " + siege + " Siege, " + niederlagen + " Niederlagen, "
						+ unentschieden + " Unentschieden");
				break;
			default:
				System.out.println("Eingabe nicht erkannt. Bitte achte auf die Schreibweise und versuche es erneut.");
			}
		} while (inGame);
	}

	private void gameRule(String equal, String lose) {
		if (auswahl.equals(equal)) {
			unentschieden++;
			System.out.println("COM-Gegner hat " + auswahl + UNENTSCHIEDEN);
		} else if (auswahl.equals(lose)) {
			niederlagen++;
			System.out.println("COM-Gegner hat " + auswahl + NIEDERLAGE);
		}
		siege++;
		System.out.println("COM-Gegner hat " + auswahl + SIEG);
	}

	private void beforeGame() {
		niederlagen = 0;
		siege = 0;
		unentschieden = 0;
	}

}
