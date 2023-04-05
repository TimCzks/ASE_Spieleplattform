package bestenliste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import domain.code.User;
import koordination.DirektorKonverter;

public class Bestenliste {

	private DirektorKonverter konverter = new DirektorKonverter();
	private List<User> userListe = new ArrayList<>();
	private List<Map.Entry<String, String>> bestenlisteAlsStringausdruck = new LinkedList<>();
	private List<Integer> bestenlisteInZahlen = new LinkedList<>();
	// TODO: nehme bestenlisteInZahlen und vergleiche sie mit neuem Wert im
	// BestenlisteObserver ("update()")

	public Bestenliste() {

	}

	public void printBestenliste() {
		System.out.println("Die Bestenliste für alle Spiele:");
		for (Entry<String, String> eintrag : getBestenlisteAlsStringausdruck()) {
			System.out.println("\n" + eintrag.getValue());
		}
	}

	private void erstelleUserListe() {
		List<String> usernamen = Arrays.asList(getKonverter().ermittleAlleUsernamen());
		for (String user : usernamen) {
			userListe.add(getKonverter().erstelleUser(user.replace(".txt", "")));
		}
	}

	public void ermittleBestenliste() {
		userListe.clear();
		erstelleUserListe();
		bestenlisteAlsStringausdruck.clear();
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

		List<Map.Entry<String, Integer>> sortedList = siegeVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).collect(Collectors.toList());

		getBestenlisteAlsStringausdruck().add(Map.entry("'Zahlenraten'", "Beste*r Spieler*in in 'Zahlenraten' ist "
				+ sortedList.get(0).getKey() + " mit " + sortedList.get(0).getValue() + " Versuchen."));
		getBestenlisteInZahlen().add(sortedList.get(0).getValue());
	}

	private void ermittleSSPMeisteSiege() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : userListe) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getSiegeSSP());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Schere, Stein, Papier'");
	}

	private void ermittleSSPMeisteNiederlagen() {
		Map<String, Integer> niederlagenVonUserMap = new HashMap<>();
		for (User user : userListe) {
			niederlagenVonUserMap.put(user.getUsername(), user.getStats().getNiederlagenSSP());
		}

		List<Map.Entry<String, Integer>> sortedList = niederlagenVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

		getBestenlisteAlsStringausdruck().add(Map.entry("'Schere, Stein, Papier'N",
				"Bonus:\nSchlechteste*r Spieler*in in 'Schere, Stein, Papier' ist " + sortedList.get(0).getKey()
						+ " mit " + sortedList.get(0).getValue() + " Niederlagen."));
		getBestenlisteInZahlen().add(sortedList.get(0).getValue());
	}

	private void sortiereStatsUndAddeZuListe(Map<String, Integer> siegeVonUserMap, String spielname) {
		List<Map.Entry<String, Integer>> sortedList = siegeVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

		getBestenlisteAlsStringausdruck().add(Map.entry(spielname, "Beste*r Spieler*in in " + spielname + " ist "
				+ sortedList.get(0).getKey() + " mit " + sortedList.get(0).getValue() + " Siegen."));
		getBestenlisteInZahlen().add(sortedList.get(0).getValue());
	}

	public List<Integer> getBestenlisteInZahlen() {
		return bestenlisteInZahlen;
	}

	public List<Map.Entry<String, String>> getBestenlisteAlsStringausdruck() {
		return bestenlisteAlsStringausdruck;
	}

	public DirektorKonverter getKonverter() {
		return konverter;
	}
}
