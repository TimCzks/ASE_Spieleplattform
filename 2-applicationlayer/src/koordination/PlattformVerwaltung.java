package koordination;

import java.util.Scanner;

import domain.code.User;
import spiele.Galgenmaennchen;
import spiele.SchereSteinPapier;
import spiele.ZahlenRaten;

public class PlattformVerwaltung {

	private Galgenmaennchen ggm;
	private SchereSteinPapier ssp;
	private ZahlenRaten zr;

	public PlattformVerwaltung(User user, Scanner sc) {
		super();
		starteSpieleplattform(user, sc);
	}

	public void starteSpieleplattform(User user, Scanner sc) {
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
				ggm = new Galgenmaennchen(sc);
				ggm.starteSpiel();
				user.getStats()
						.setSiegeGGM(user.getStats().getSiegeGGM() + ggm.validiereSpielergebnis(ggm.getLastingTries()));
				user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
				break;
			case "ZR":
				zr = new ZahlenRaten(sc);
				zr.startGame();
				int spielergebnis = zr.validiereSpielergebnis();
				user.getStats().setGespielteSpiele(user.getStats().getGespielteSpiele() + 1);
				if (user.getStats().getRekordZR() > spielergebnis) {
					user.getStats().setRekordZR(spielergebnis);
				}
				break;
			case "SSP":
				ssp = new SchereSteinPapier(sc);
				ssp.startGame();
				user.getStats().setSiegeSSP(user.getStats().getSiegeSSP() + ssp.validiereSpielergebnis()[1]);
				user.getStats()
						.setNiederlagenSSP(user.getStats().getNiederlagenSSP() + ssp.validiereSpielergebnis()[2]);
				user.getStats()
						.setUnentschiedenSSP(user.getStats().getUnentschiedenSSP() + ssp.validiereSpielergebnis()[3]);
				user.getStats()
						.setGespielteSpiele(user.getStats().getGespielteSpiele() + ssp.validiereSpielergebnis()[0]);
				break;
			case "STATS":
				System.out.println(user.toString());
				break;
			case "SAVE":
				MainMenu.konverter.speichereUserAb(user);
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
