package koordination;

import java.util.Scanner;

import domain.code.Stats;
import domain.code.User;
import spiele.Galgenmaennchen;
import spiele.ZahlenRaten;

public class MainMenu {

	private static User user;
	private static KonverterInterface konverter;
	private static Galgenmaennchen ggm;

	public static void main(String[] args) {
		konverter = new KonverterFactory().getKonverter();
		System.out.println(
				"Willkommen bei der Spieleplattform ASE.\nFalls du bereits einen Account hast, nutze den Befehl 'LOG'."
						+ "\nAnsonsten nutze den Befehl 'REG'.");
		Scanner sc = new Scanner(System.in);
		while (!anmelden(sc)) {
			System.out.println("Eingabe nicht erkannt. Bitte achte auf die Schreibweise und versuche es erneut.");
		}
		System.out.println("Du bist erfolgreich eingeloggt.\n" + user.toString() + "\n");
		starteSpieleplattform(user, sc);
	}

	private static boolean anmelden(Scanner sc) {
		String input = sc.next();
		if (input.equalsIgnoreCase("LOG"))
			return einloggen(sc);
		if (input.equalsIgnoreCase("REG"))
			return registrieren(sc);
		return false;
	}

	private static boolean einloggen(Scanner sc) {
		System.out.println("Bitte gib deinen Benutzernamen an: ");
		String username = sc.next();
		user = konverter.erstelleUser(username);
		return true;
	}

	private static boolean registrieren(Scanner sc) {
		System.out.println("Bitte gib deinen zukünftigen Benutzernamen an:");
		String username = sc.next();
		user = new User(username, new Stats(0, 100, 0));
		konverter.speichereUser(user);
		return true;
	}

	private static void starteSpieleplattform(User user, Scanner sc) {
		System.out.println("Hey, mit dem Befehl 'GGM' startest du eine Runde 'Galgenmännchen'."
				+ "\nMit dem Befehl 'ZR' startest du eine Runde 'Zahlen Raten'.\nMit dem Befehl 'STATS' kannst du dir deine bisherige Spielestatistik anschauen."
				+ "\nÜber 'SAVE' kannst du deinen Account mit der aktuellen Statistik abspeichern."
				+ "\nÜber 'EXIT' kannst du das Spiel verlassen - denke daran, vorher zu speichern.");
		boolean inLoop = true;
		do {
			System.out.println("\nWas möchtest du tun?");
			String eingabe = sc.next();
			switch (eingabe) {
			case "GGM":
				ggm = new Galgenmaennchen();
				ggm.startGame(sc, user);
				break;
			case "ZR":
				ZahlenRaten zr = new ZahlenRaten(sc, user);
				zr.startGame();
				break;
			case "STATS":
				break;
			case "SAVE":
				konverter.speichereUser(user);
				System.out.println("Erfolgreich gespeichert. " + user.toString());
				break;
			case "EXIT":
				inLoop = false;
				System.out.println("Bis zum nächsten mal!");
				break;
			default:
				System.out.println("Eingabe nicht erkannt. Bitte achte auf die Schreibweise und versuche es erneut.");
			}
		} while (inLoop);
	}

}
