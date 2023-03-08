package koordination;

import java.util.Scanner;

import domain.code.Stats;
import domain.code.User;
import spiele.Galgenmaennchen;
import spiele.SchereSteinPapier;
import spiele.ZahlenRaten;

public class MainMenu {

	private static User user;
	private static KonverterInterface konverter;
	private static Galgenmaennchen ggm;
	private static SchereSteinPapier ssp;
	private static ZahlenRaten zr;

	public static void main(String[] args) {
		konverter = new KonverterFactory().getKonverter();
		System.out.println(
				"Willkommen bei der Spieleplattform ASE.\nFalls du bereits einen Account hast, nutze den Befehl 'LOG'."
						+ "\nAnsonsten nutze den Befehl 'REG'.");
		Scanner sc = new Scanner(System.in);
		while (!anmelden(sc)) {
			System.out.println("Eingabe nicht erkannt. Bitte achte auf die Schreibweise und versuche es erneut.");
		}
		System.out.println("Du bist erfolgreich eingeloggt als " + user.getUsername() + ".\n");
		starteSpieleplattform(user, sc);
	}

	private static boolean anmelden(Scanner sc) {
		String input = sc.next();
		System.out.println("Bitte gib den Benutzernamen deines Accounts an:");
		String username = sc.next();
		if (input.equalsIgnoreCase("LOG"))
			return einloggen(username);
		if (input.equalsIgnoreCase("REG"))
			return registrieren(sc, username);
		return false;
	}

	private static boolean einloggen(String username) {
		user = konverter.erstelleUser(username);
		return true;
	}

	private static boolean registrieren(Scanner sc, String username) {
		while (konverter.pruefeObUserBereitsExistiert(username)) {
			System.out.println("Der Benutzername '" + username + "' ist bereits vergeben, bitte w�hle einen Neuen.");
			username = sc.next();
		}
		user = new User(username, new Stats(0, 100, 0, 0, 0, 0));
		konverter.speichereUserAb(user);
		return true;
	}

	private static void starteSpieleplattform(User user, Scanner sc) {
		System.out.println("Hey, mit 'GGM' startest du eine Runde 'Galgenmännchen'."
				+ "\nMit 'SSP' startest du eine Runde 'Schere, Stein, Papier'."
				+ "\nMit 'ZR' startest du eine Runde 'Zahlen Raten'.\nMit dem Befehl 'STATS' kannst du dir deine bisherige Spielestatistik anschauen."
				+ "\n�ber 'SAVE' kannst du deinen Account mit der aktuellen Statistik abspeichern."
				+ "\n�ber 'EXIT' kannst du das Spiel verlassen - denke daran, vorher zu speichern.");
		boolean inLoop = true;
		do {
			System.out.println("\nWas möchtest du tun?");
			String eingabe = sc.next();
			switch (eingabe) {
			case "GGM":
				ggm = new Galgenmaennchen(sc, user);
				ggm.startGame();
				break;
			case "ZR":
				zr = new ZahlenRaten(sc, user);
				zr.startGame();
				break;
			case "SSP":
				ssp = new SchereSteinPapier(sc, user);
				ssp.startGame();
				break;
			case "STATS":
				System.out.println(user.toString());
				break;
			case "SAVE":
				konverter.speichereUserAb(user);
				System.out.println("Aktuelle Stats erfolgreich gespeichert.");
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
