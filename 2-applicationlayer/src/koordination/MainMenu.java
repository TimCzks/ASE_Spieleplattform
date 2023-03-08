package koordination;

import java.util.Scanner;

import domain.code.Stats;
import domain.code.User;

public class MainMenu {

	private static User user;
	public static KonverterInterface konverter;
	private static PlattformVerwaltung spieleplattform;

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
		spieleplattform = new PlattformVerwaltung(user, sc);
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
			System.out.println("Der Benutzername '" + username + "' ist bereits vergeben, bitte wähle einen Neuen.");
			username = sc.next();
		}
		user = new User(username, new Stats(0, 100, 0, 0, 0, 0));
		konverter.speichereUserAb(user);
		return true;
	}

}
