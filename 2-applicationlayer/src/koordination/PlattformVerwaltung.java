package koordination;

import java.util.Scanner;

import bestenliste.Bestenliste;
import domain.code.User;
import spiele.Galgenmaennchen;
import spiele.SchereSteinPapier;
import spiele.ZahlenRaten;

public class PlattformVerwaltung {

	private Galgenmaennchen ggm;
	private SchereSteinPapier ssp;
	private ZahlenRaten zr;
	private Bestenliste bestenliste;

	public PlattformVerwaltung(User user, Scanner sc) {
		super();
		bestenliste = new Bestenliste();
		starteSpieleplattform(user, sc);
	}

	public void starteSpieleplattform(User user, Scanner sc) {
		printEinleitungZuASE();
		boolean inLoop = true;
		do {

			System.out.println("\nWas möchtest du tun?");
			String eingabe = sc.next();
			switch (eingabe) {
			case "GGM":
				spieleGalgenmaennchen(user, sc);
				break;
			case "ZR":
				spieleZahlenRaten(user, sc);
				break;
			case "SSP":
				spieleSchereSteinPapier(user, sc);
				break;
			case "STATS":
				System.out.println(user.toString());
				break;
			case "SAVE":
				MainMenu.konverter.speichereUserAb(user);
				System.out.println("Aktuelle Stats erfolgreich gespeichert.");
				break;
			case "BESTENLISTE":
				bestenliste.printBestenliste();
				break;
			case "HELP":
				getHelp();
				System.out.println("\nWas möchtest du tun?");
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

	private void printEinleitungZuASE() {
		System.out.println("Hey, mit 'GGM' startest du eine Runde 'Galgenmännchen'."
				+ "\nMit 'SSP' startest du eine Runde 'Schere, Stein, Papier'."
				+ "\nMit 'ZR' startest du eine Runde 'Zahlen Raten'.\nMit dem Befehl 'STATS' kannst du dir deine bisherige Spielestatistik anschauen."
				+ "\nMit 'BESTENLISTE' kannst du dir anschauen, wer in welchem Spiel die besten Stats erzielt hat."
				+ "\nüber 'HELP' bekommst du eine Übersicht über alle nutzbaren Befehle."
				+ "\nüber 'SAVE' kannst du deinen Account mit der aktuellen Statistik abspeichern."
				+ "\nüber 'EXIT' kannst du das Spiel verlassen - denke daran, vorher zu speichern.");
	}

	private void spieleGalgenmaennchen(User user, Scanner sc) {
		ggm = new Galgenmaennchen(sc);
		ggm.starteSpiel();
		user.getStats().setSiegeGGM(user.getStats().getSiegeGGM() + ggm.validiereSpielergebnis(ggm.getLastingTries()));
		user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
	}

	private void spieleZahlenRaten(User user, Scanner sc) {
		zr = new ZahlenRaten(sc);
		zr.starteSpiel();
		int spielergebnis = zr.validiereSpielergebnis();
		user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
		if (user.getStats().getRekordZR() > spielergebnis) {
			user.getStats().setRekordZR(spielergebnis);
		}
	}

	private void spieleSchereSteinPapier(User user, Scanner sc) {
		ssp = new SchereSteinPapier(sc);
		ssp.starteSpiel();
		user.getStats().setSiegeSSP(user.getStats().getSiegeSSP() + ssp.validiereSpielergebnis()[1]);
		user.getStats().setNiederlagenSSP(user.getStats().getNiederlagenSSP() + ssp.validiereSpielergebnis()[2]);
		user.getStats().setUnentschiedenSSP(user.getStats().getUnentschiedenSSP() + ssp.validiereSpielergebnis()[3]);
		user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + ssp.validiereSpielergebnis()[0]);
	}

	private void getHelp() {
		System.out.println("Hier findest du alle nutzbaren Befehle:" + "\n'GGM': Startet eine Runde 'Galgenmännchen'."
				+ "\n'SSP': Startet eine Runde 'Schere, Stein, Papier'." + "\n'ZR': Startet eine Runde 'Zahlen Raten'."
				+ "\n'STATS': Lasse dir deine bisherige Spielestatistik anzeigen."
				+ "\n'SAVE': Speichere deinen Account mit der aktuellen Statistik ab."
				+ "\n'BESTENLISTE': Schaue dir an, wer in welchem Spiel die besten Stats erzielt hat."
				+ "\n'HELP': Lasse dir alle nutzbaren Befehle anzeigen."
				+ "\n'EXIT': Verlassen der Spieleplattform - denke daran, vorher zu speichern.");
	}

}
