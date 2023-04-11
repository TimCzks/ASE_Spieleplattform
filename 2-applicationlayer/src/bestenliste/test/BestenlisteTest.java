package bestenliste.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bestenliste.Bestenliste;
import domain.code.Stats;
import domain.code.User;
import koordination.DirektorKonverter;

class BestenlisteTest {

	private DirektorKonverter konverter = Mockito.mock(DirektorKonverter.class);
	private Bestenliste bestenliste = Mockito.mock(Bestenliste.class);

	@BeforeEach
	void setKonverter() {
		Mockito.doCallRealMethod().when(bestenliste).setKonverter(konverter);
		bestenliste.setKonverter(konverter);
	}

	void methodenVerhaltenFuerErmittelnDerUserListeMitListe(List<User> bestenListeUserliste) {
		Mockito.doCallRealMethod().when(bestenliste).ermittleBestenliste();
		Mockito.doCallRealMethod().when(bestenliste).erstelleUserListe();
		Mockito.doCallRealMethod().when(bestenliste).getUserListe();

		Mockito.doNothing().when(bestenliste).ermittleGGMRekord();
		Mockito.doNothing().when(bestenliste).ermittleZRRekord();
		Mockito.doNothing().when(bestenliste).leereListenVorErmittlung();
		Mockito.doNothing().when(bestenliste).ermittleSSPMeisteSiege();
		Mockito.doNothing().when(bestenliste).ermittleMeisteGesamteSpiele();
		Mockito.doNothing().when(bestenliste).ermittleSSPMeisteNiederlagen();
		Mockito.doCallRealMethod().when(bestenliste).setUserListe(bestenListeUserliste);
	}

	@Test
	void testPrintBestenliste() throws IOException {
		List<Map.Entry<String, String>> bestenlisteAlsStringausdruck = new ArrayList<>();
		bestenlisteAlsStringausdruck.add(Map.entry("Spiel1", "Eintrag1"));
		Mockito.doCallRealMethod().when(bestenliste).setBestenlisteAlsStringausdruck(bestenlisteAlsStringausdruck);
		Mockito.doCallRealMethod().when(bestenliste).printBestenliste();
		bestenliste.setBestenlisteAlsStringausdruck(bestenlisteAlsStringausdruck);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(byteArrayOutputStream));
		bestenliste.printBestenliste();
		byteArrayOutputStream.flush();

		String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
		assertTrue(allWrittenLines.contains("Die Bestenliste für alle Spiele:"));
	}

	@Test
	void testErmittleUserDerBestenliste() {
		List<User> bestenListeUserliste = new ArrayList<>();
		methodenVerhaltenFuerErmittelnDerUserListeMitListe(bestenListeUserliste);

		User testUser1 = new User();
		testUser1.setUsername("user1");
		User testUser2 = new User();
		testUser2.setUsername("user2");
		bestenliste.setUserListe(bestenListeUserliste);

		List<User> testUserListe = new ArrayList<>();
		testUserListe.add(testUser1);
		testUserListe.add(testUser2);

		Mockito.when(konverter.ermittleAlleUsernamen()).thenReturn(new String[] { "user1.txt", "user2.txt" });
		Mockito.when(konverter.erstelleUser("user1")).thenReturn(testUser1);
		Mockito.when(konverter.erstelleUser("user2")).thenReturn(testUser2);

		bestenliste.ermittleBestenliste();

		Mockito.verify(konverter, Mockito.times(1)).ermittleAlleUsernamen();
		Mockito.verify(konverter).erstelleUser("user1");
		Mockito.verify(konverter).erstelleUser("user2");
		assertEquals(2, bestenliste.getUserListe().size());
		assertEquals(testUserListe, bestenliste.getUserListe());
	}

	@Test
	void testErmittleGGMRekord() {
		List<User> bestenListeUserliste = new ArrayList<>();
		List<Map.Entry<String, String>> bestenlisteAlsStringausdruck = new ArrayList<>();
		methodenVerhaltenFuerErmittelnGGMRekordMit(bestenListeUserliste, bestenlisteAlsStringausdruck);

		User Testuser1 = new User("user1", new Stats(0, 100, 0, 0, 0, 3));
		Testuser1.getStats().setSiegeGGM(3);
		User testUser2 = new User("user2", new Stats(0, 100, 0, 0, 0, 5));
		testUser2.getStats().setSiegeGGM(5);

		bestenliste.setBestenlisteAlsStringausdruck(bestenlisteAlsStringausdruck);
		bestenliste.setUserListe(bestenListeUserliste);
		bestenliste.getUserListe().add(Testuser1);
		bestenliste.getUserListe().add(testUser2);
		bestenliste.ermittleGGMRekord();

		List<Map.Entry<String, String>> ermittelteBestenlisteMitGGMRekord = bestenliste
				.getBestenlisteAlsStringausdruck();

		assertEquals(1, ermittelteBestenlisteMitGGMRekord.size());
		assertEquals("'Galgenmännchen'", ermittelteBestenlisteMitGGMRekord.get(0).getKey());
		assertEquals("Beste*r Spieler*in in 'Galgenmännchen' ist user2 mit 5 Siegen.",
				ermittelteBestenlisteMitGGMRekord.get(0).getValue());
	}

	private void methodenVerhaltenFuerErmittelnGGMRekordMit(List<User> bestenListeUserliste,
			List<Map.Entry<String, String>> bestenlisteAlsStringausdruck) {
		Mockito.doCallRealMethod().when(bestenliste).setUserListe(bestenListeUserliste);
		Mockito.doCallRealMethod().when(bestenliste).ermittleGGMRekord();
		Mockito.doCallRealMethod().when(bestenliste).getUserListe();
		Mockito.doCallRealMethod().when(bestenliste).getBestenlisteAlsStringausdruck();
		Mockito.doCallRealMethod().when(bestenliste).setBestenlisteAlsStringausdruck(bestenlisteAlsStringausdruck);
	}

}
