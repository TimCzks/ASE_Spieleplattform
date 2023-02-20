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
		System.out.println("Bitte gib deinen zuk�nftigen Benutzernamen an:");
		String username = sc.next();
		user = new User(username, new Stats(0, 100, 0, 0, 0, 0));
		konverter.speichereUser(user);
		return true;
	}

	private static void starteSpieleplattform(User user, Scanner sc) {
		System.out.println("Hey, mit 'GGM' startest du eine Runde 'Galgenm�nnchen'."
				+ "\nMit 'SSP' startest du eine Runde 'Schere, Stein, Papier'."
				+ "\nMit 'ZR' startest du eine Runde 'Zahlen Raten'.\nMit dem Befehl 'STATS' kannst du dir deine bisherige Spielestatistik anschauen."
				+ "\n�ber 'SAVE' kannst du deinen Account mit der aktuellen Statistik abspeichern."
				+ "\n�ber 'EXIT' kannst du das Spiel verlassen - denke daran, vorher zu speichern.");
		boolean inLoop = true;
		do {
			System.out.println("\nWas m�chtest du tun?");
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
				konverter.speichereUser(user);
				System.out.println("Aktuelle Stats erfolgreich gespeichert.");
				break;
			case "EXIT":
				inLoop = false;
				System.out.println("Bis zum n�chsten mal!");
				break;
			default:
				System.out.println("Eingabe nicht erkannt. Bitte achte auf die Schreibweise und versuche es erneut.");
			}
		} while (inLoop);
	}

}
