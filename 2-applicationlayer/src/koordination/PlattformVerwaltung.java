package koordination;

import java.util.Scanner;

import bestenliste.Bestenliste;
import bestenliste.BestenlisteObserverGGM;
import bestenliste.BestenlisteObserverSSP;
import bestenliste.BestenlisteObserverSpieleGesamt;
import bestenliste.BestenlisteObserverZR;
import bestenliste.BestenlistenObserverVerwaltung;
import domain.code.User;
import spiele.Galgenmaennchen;
import spiele.SchereSteinPapier;
import spiele.ZahlenRaten;

public class PlattformVerwaltung {

	private Galgenmaennchen ggm;
	private SchereSteinPapier ssp;
	private ZahlenRaten zr;
	private Bestenliste bestenliste;
	private User aktuellerUser;
	private BestenlistenObserverVerwaltung observable;
	private BestenlisteObserverGGM observerGGM;
	private BestenlisteObserverSSP observerSSP;
	private BestenlisteObserverZR observerZR;
	private BestenlisteObserverSpieleGesamt observerSpieleGesamt;

	public PlattformVerwaltung(User aktuellerUser) {
		super();
		this.aktuellerUser = aktuellerUser;
		setBestenliste(new Bestenliste());
		setUpObservers();
	}

	public void starteSpieleplattform(Scanner sc) {
		printEinleitungZuASE();
		boolean inLoop = true;
		do {
			bestenliste.ermittleBestenliste();
			System.out.println("\nWas möchtest du tun?");
			String eingabe = sc.next();
			switch (eingabe) {
			case "GGM":
				spieleGalgenmaennchen(sc);
				break;
			case "ZR":
				spieleZahlenRaten(sc);
				break;
			case "SSP":
				spieleSchereSteinPapier(sc);
				break;
			case "STATS":
				System.out.println(aktuellerUser.toString());
				break;
			case "SAVE":
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
			observable.setNewStat(aktuellerUser.getStats().getGespielteSpiele(), "Spiele_Gesamt", this);
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

	private void spieleGalgenmaennchen(Scanner sc) {
		ggm = new Galgenmaennchen(sc);
		ggm.starteSpiel();
		aktuellerUser.getStats().setSiegeGGM(
				aktuellerUser.getStats().getSiegeGGM() + ggm.validiereSpielergebnis(ggm.getLastingTries()));
		aktuellerUser.getStats().setGespielteSpiele(aktuellerUser.getStats().getGespielteSpiele() + 1);
		observable.setNewStat(aktuellerUser.getStats().getSiegeGGM(), "'Galgenmännchen'", this);
	}

	private void spieleZahlenRaten(Scanner sc) {
		zr = new ZahlenRaten(sc);
		zr.starteSpiel();
		int spielergebnis = zr.validiereSpielergebnis();
		aktuellerUser.getStats().setGespielteSpiele(aktuellerUser.getStats().getGespielteSpiele() + 1);
		if (aktuellerUser.getStats().getRekordZR() > spielergebnis) {
			aktuellerUser.getStats().setRekordZR(spielergebnis);
			observable.setNewStat(aktuellerUser.getStats().getRekordZR(), "'Zahlenraten'", this);
		}
	}

	private void spieleSchereSteinPapier(Scanner sc) {
		ssp = new SchereSteinPapier(sc);
		ssp.starteSpiel();
		aktuellerUser.getStats().setSiegeSSP(aktuellerUser.getStats().getSiegeSSP() + ssp.validiereSpielergebnis()[1]);
		aktuellerUser.getStats()
				.setNiederlagenSSP(aktuellerUser.getStats().getNiederlagenSSP() + ssp.validiereSpielergebnis()[2]);
		aktuellerUser.getStats()
				.setUnentschiedenSSP(aktuellerUser.getStats().getUnentschiedenSSP() + ssp.validiereSpielergebnis()[3]);
		aktuellerUser.getStats()
				.setGespielteSpiele(aktuellerUser.getStats().getGespielteSpiele() + ssp.validiereSpielergebnis()[0]);
		observable.setNewStat(aktuellerUser.getStats().getSiegeSSP(), "'Schere, Stein, Papier'", this);
		observable.setNewStat(aktuellerUser.getStats().getNiederlagenSSP(), "'Schere, Stein, Papier'N", this);
	}

	public void speichereUserAb() {
		MainMenu.konverter.speichereUserAb(aktuellerUser);
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

	private void setUpObservers() {
		observable = new BestenlistenObserverVerwaltung();
		observerGGM = new BestenlisteObserverGGM();
		observerSSP = new BestenlisteObserverSSP();
		observerZR = new BestenlisteObserverZR();
		observerSpieleGesamt = new BestenlisteObserverSpieleGesamt();
		observable.addBeobachter(observerSpieleGesamt);
		observable.addBeobachter(observerGGM);
		observable.addBeobachter(observerSSP);
		observable.addBeobachter(observerZR);

	}

	public User getAktuellerUser() {
		return aktuellerUser;
	}

	public void setBestenliste(Bestenliste bestenliste) {
		this.bestenliste = bestenliste;
	}

}
