package koordination;

import java.util.Scanner;

import domain.code.User;

public class MainMenu {

	private static User user;
	private static KonverterInterface konverter;

	public static void main(String[] args) {
		System.out.println(
				"Willkommen bei der Spieleplattform Schiffsmännchen.\nFalls du bereits einen Account hast, nutze den Befehl \"LOG\"."
						+ "\nAnsonsten nutze den Befehl \"REG\".");
		Scanner sc = new Scanner(System.in);
		if (!anmelden(sc))
			System.out.println("Etwas ist schief gelaufen.");
		System.out.println("Du bist erfolgreich eingeloggt.\n" + user.toString());
		// eingeloggt(); --> Überprüfen auf weitere Befehle, "STATS", "GGM", "SV"
	}

	private static boolean anmelden(Scanner sc) {
		String input = sc.next();
		if (input.equalsIgnoreCase("LOG"))
			return einloggen(sc);
		if (input.equalsIgnoreCase("REG"))
			return registrieren();
		return false;
	}

	private static boolean einloggen(Scanner sc) {
		System.out.println("Bitte gib deinen Benutzernamen an: ");
		String username = sc.next();
		user = konverter.erstelleUser(username);
		return true;
	}

	private static boolean registrieren() {
		// TODO
		return false;
	}

	private void speichern(User user) {
		System.out.println("Willkommen!" + "\nMit dem Befehl \"GGM\" startest du eine Runde Galgenmännchen."
				+ "\nMit dem Befehl \"SV\" startest du eine Runde Schiffe Versenken.");
	}
}
