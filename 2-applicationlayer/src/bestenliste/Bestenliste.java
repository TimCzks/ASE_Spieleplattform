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

	public Bestenliste() {

	}

	public void printBestenliste() {
		System.out.println("Die Bestenliste für alle Spiele:");
		for (Entry<String, String> eintrag : getBestenlisteAlsStringausdruck()) {
			System.out.println("\n" + eintrag.getValue());
		}
	}

	public void erstelleUserListe() {
		List<String> usernamen = Arrays.asList(konverter.ermittleAlleUsernamen());
		for (String user : usernamen) {
			getUserListe().add(konverter.erstelleUser(user.replace(".txt", "")));
		}
	}

	public void ermittleBestenliste() {
		leereListenVorErmittlung();
		erstelleUserListe();
		ermittleGGMRekord();
		ermittleZRRekord();
		ermittleSSPMeisteSiege();
		ermittleMeisteGesamteSpiele();
		ermittleSSPMeisteNiederlagen();
	}

	public void leereListenVorErmittlung() {
		bestenlisteAlsStringausdruck.clear();
		userListe.clear();
	}

	public void ermittleGGMRekord() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : getUserListe()) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getSiegeGGM());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Galgenmännchen'");
	}

	public void ermittleZRRekord() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : getUserListe()) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getRekordZR());
		}

		List<Map.Entry<String, Integer>> sortedList = siegeVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).collect(Collectors.toList());

		getBestenlisteAlsStringausdruck().add(Map.entry("'Zahlenraten'", "Beste*r Spieler*in in 'Zahlenraten' ist "
				+ sortedList.get(0).getKey() + " mit " + sortedList.get(0).getValue() + " Versuchen."));
		getBestenlisteInZahlen().add(sortedList.get(0).getValue());
	}

	public void ermittleSSPMeisteSiege() {
		Map<String, Integer> siegeVonUserMap = new HashMap<>();
		for (User user : getUserListe()) {
			siegeVonUserMap.put(user.getUsername(), user.getStats().getSiegeSSP());
		}

		sortiereStatsUndAddeZuListe(siegeVonUserMap, "'Schere, Stein, Papier'");
	}

	public void ermittleMeisteGesamteSpiele() {
		Map<String, Integer> GesamtSpieleVonUserMap = new HashMap<>();
		for (User user : getUserListe()) {
			GesamtSpieleVonUserMap.put(user.getUsername(), user.getStats().getGespielteSpiele());
		}

		List<Map.Entry<String, Integer>> sortedList = GesamtSpieleVonUserMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

		getBestenlisteAlsStringausdruck()
				.add(Map.entry("Spiele_Gesamt", "Spieler*in mit den meisten insgesamt gespielten Spielen ist "
						+ sortedList.get(0).getKey() + " mit " + sortedList.get(0).getValue() + " Spielen."));
		getBestenlisteInZahlen().add(sortedList.get(0).getValue());
	}

	public void ermittleSSPMeisteNiederlagen() {
		Map<String, Integer> niederlagenVonUserMap = new HashMap<>();
		for (User user : getUserListe()) {
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

	public void setBestenlisteAlsStringausdruck(List<Map.Entry<String, String>> bestenlisteAlsStringausdruck) {
		this.bestenlisteAlsStringausdruck = bestenlisteAlsStringausdruck;
	}

	public DirektorKonverter getKonverter() {
		return konverter;
	}

	public void setKonverter(DirektorKonverter konverter) {
		this.konverter = konverter;
	}

	public List<User> getUserListe() {
		return userListe;
	}

	public void setUserListe(List<User> userListe) {
		this.userListe = userListe;
	}
}
