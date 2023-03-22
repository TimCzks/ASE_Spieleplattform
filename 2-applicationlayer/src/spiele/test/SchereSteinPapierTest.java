package spiele.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import spiele.SchereSteinPapier;

class SchereSteinPapierTest {

	private SchereSteinPapier classUnderTest = Mockito.mock(SchereSteinPapier.class);

	@Test
	void testStartGame() {
		Mockito.doCallRealMethod().when(classUnderTest).starteSpiel();
		Mockito.doNothing().when(classUnderTest).clearVariablesBeforeGame();
		Mockito.doNothing().when(classUnderTest).starteSpielDurchlaeufeBisExit();
		classUnderTest.starteSpiel();
		Mockito.verify(classUnderTest, Mockito.times(1)).starteSpiel();
	}

	@Test
	void testValidiereSpielergebnis() {
		Mockito.doCallRealMethod().when(classUnderTest).clearVariablesBeforeGame();
		Mockito.doCallRealMethod().when(classUnderTest).validiereSpielergebnis();
		classUnderTest.clearVariablesBeforeGame();
		assertEquals(0, classUnderTest.validiereSpielergebnis()[0]);
	}

	@Test
	void testGameruleUnentschieden() {
		Mockito.doCallRealMethod().when(classUnderTest).gameRule(Mockito.isA(String.class), Mockito.isA(String.class));
		Mockito.when(classUnderTest.getAuswahl()).thenReturn("SCHERE");
		assertEquals("COM-Gegner hat SCHERE. Unentschieden! Neuer Versuch?",
				classUnderTest.gameRule("SCHERE", "STEIN"));
	}

	@Test
	void testGameruleSieg() {
		Mockito.doCallRealMethod().when(classUnderTest).gameRule(Mockito.isA(String.class), Mockito.isA(String.class));
		Mockito.when(classUnderTest.getAuswahl()).thenReturn("SCHERE");
		assertEquals("COM-Gegner hat SCHERE. Sieg! Neuer Versuch?", classUnderTest.gameRule("STEIN", "PAPIER"));
	}

	@Test
	void testGameruleNiederlage() {
		Mockito.doCallRealMethod().when(classUnderTest).gameRule(Mockito.isA(String.class), Mockito.isA(String.class));
		Mockito.when(classUnderTest.getAuswahl()).thenReturn("SCHERE");
		assertEquals("COM-Gegner hat SCHERE. Niederlage! Neuer Versuch?", classUnderTest.gameRule("PAPIER", "SCHERE"));
	}

}
