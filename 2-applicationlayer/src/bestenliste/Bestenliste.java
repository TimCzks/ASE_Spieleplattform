package bestenliste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.code.User;
import koordination.DirektorKonverter;

public class Bestenliste {

	private DirektorKonverter konverter = new DirektorKonverter();
	private List<User> userListe = new ArrayList<>();
	private List<String> bestenliste = new LinkedList<>();
	private List<Integer> bestenlisteInZahlen = new LinkedList<>();
	// TODO: nehme bestenlisteInZahlen und vergleiche sie mit neuem Wert im
	// BestenlisteObserver ("update()")

	public Bestenliste() {
		List<String> usernamen = Arrays.asList(konverter.ermittleAlleUsernamen());
		for (String user : usernamen) {
			userListe.add(konverter.erstelleUser(user.replace(".txt", "")));
		}
		ermittleBestenliste();
	}

	public void printBestenliste() {
		System.out.println("Die Bestenliste für alle Spiele:");
		for (String eintrag : bestenliste) {
			System.out.println("\n" + eintrag);
		}
	}

	private void ermittleBestenliste() {
		ermittleGGMRekord();
		ermittleZRRekord();
		ermittleSSPMeisteSiege();
		ermittleSSPMeisteNiederlagen();
	}

	private void ermittleGGMRekord() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : userListe) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getSiegeGGM());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Galgenmännchen'");
	}

	private void ermittleZRRekord() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : userListe) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getRekordZR());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Zahlenraten'");
	}

	private void ermittleSSPMeisteSiege() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : userListe) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getSiegeSSP());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Schere, Stein, Papier'");
	}

	private void ermittleSSPMeisteNiederlagen() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : userListe) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getNiederlagenSSP());
		}

		List<Map.Entry<String, Integer>> sortedList = siegeVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

		bestenliste.add("Bonus:\nSchlechteste*r Spieler*in in 'Schere, Stein, Papier' ist " + sortedList.get(0).getKey()
				+ " mit " + sortedList.get(0).getValue() + " Niederlagen.");
		bestenlisteInZahlen.add(sortedList.get(0).getValue());
	}

	private void sortiereStatsUndAddeZuListe(Map<String, Integer> siegeVonUserMap, String spielname) {
		List<Map.Entry<String, Integer>> sortedList = siegeVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

		bestenliste.add("Beste*r Spieler*in in " + spielname + " ist " + sortedList.get(0).getKey() + " mit "
				+ sortedList.get(0).getValue() + " Siegen.");
		bestenlisteInZahlen.add(sortedList.get(0).getValue());
	}
}
