package domain.code;

import java.util.Scanner;

import konvertierung.Konverter;

public class MainMenu {

	private static User user;

	public static void main(String[] args) {
		System.out.println(
				"Willkommen bei der Spieleplattform Schiffsm�nnchen.\nFalls du bereits einen Account hast, nutze den Befehl \"LOG\"."
						+ "\nAnsonsten nutze den Befehl \"REG\".");
		Scanner sc = new Scanner(System.in);
		if (!anmelden(sc))
			System.out.println("Etwas ist schief gelaufen.");
		System.out.println("Du bist erfolgreich eingeloggt.\n" + user.toString());
		// eingeloggt(); --> �berpr�fen auf weitere Befehle, "STATS", "GGM", "SV"
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
		Konverter konv = new Konverter();
		System.out.println("Bitte gib deinen Benutzernamen an: ");
		String username = sc.next();
		user = konv.erstelleUser(username);
		return true;
	}

	private static boolean registrieren() {
		// TODO
		return false;
	}

	private void speichern(User user) {
		System.out.println("Willkommen!" + "\nMit dem Befehl \"GGM\" startest du eine Runde Galgenm�nnchen."
				+ "\nMit dem Befehl \"SV\" startest du eine Runde Schiffe Versenken.");
	}
}
