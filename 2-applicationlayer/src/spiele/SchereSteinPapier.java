package spiele;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domain.code.Stats;
import domain.code.User;

public class SchereSteinPapier {

	private final List<String> auswahlListe = Arrays.asList(new String[] { "SCHERE", "STEIN", "PAPIER" });
	private final String UNENTSCHIEDEN = ". Unentschieden! Neuer Versuch?";
	private final String NIEDERLAGE = ". Niederlage! Neuer Versuch?";
	private final String SIEG = ". Sieg! Neuer Versuch?";
	private Scanner sc;
	private User user;
	private int siege, niederlagen, unentschieden, anzahlSpiele;
	private String auswahl;

	public SchereSteinPapier(Scanner sc, User user) {
		super();
		this.sc = sc;
		this.user = user;
	}

	public void startGame() {
		clearVariablesBeforeGame();
		System.out.println(
				"Willkommen bei Schere, Stein, Papier!\nZum Starten gib entweder 'SCHERE', 'STEIN' oder 'PAPIER' ein, "
						+ "sobald du nicht mehr weiterspielen willst, gib 'EXIT' ein.");
		starteSpielDurchlaeufeBisExit();
		setStatsAfterGame();
	}

	private void starteSpielDurchlaeufeBisExit() {
		boolean inGame = true;
		Random r = new Random();
		do {
			auswahl = auswahlListe.get(r.nextInt(2));
			switch (sc.next()) {
			case "SCHERE":
				System.out.println(gameRule("SCHERE", "STEIN"));
				break;
			case "STEIN":
				System.out.println(gameRule("STEIN", "PAPIER"));
				break;
			case "PAPIER":
				System.out.println(gameRule("PAPIER", "SCHERE"));
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

	private String gameRule(String equal, String lose) {
		anzahlSpiele++;
		String comAuswahl = "COM-Gegner hat " + auswahl;
		if (auswahl.equals(equal)) {
			unentschieden++;
			return comAuswahl + UNENTSCHIEDEN;
		} else if (auswahl.equals(lose)) {
			niederlagen++;
			return comAuswahl + NIEDERLAGE;
		} else {
			siege++;
			return comAuswahl + SIEG;
		}
	}

	private void clearVariablesBeforeGame() {
		niederlagen = 0;
		siege = 0;
		unentschieden = 0;
		anzahlSpiele = 0;
	}

	private void setStatsAfterGame() {
		Stats userStats = user.getStats();
		userStats.setSiegeSSP(userStats.getSiegeSSP() + siege);
		userStats.setNiederlagenSSP(userStats.getNiederlagenSSP() + niederlagen);
		userStats.setUnentschiedenSSP(userStats.getUnentschiedenSSP() + unentschieden);
		userStats.setGespielteSpiele(userStats.getGespielteSpiele() + anzahlSpiele);
	}

}
