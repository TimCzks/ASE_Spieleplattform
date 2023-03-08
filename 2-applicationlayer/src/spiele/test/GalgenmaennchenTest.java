package spiele.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import domain.code.Stats;
import domain.code.User;
import spiele.Galgenmaennchen;

class GalgenmaennchenTest {
	private User userMock = Mockito.mock(User.class);
	private Galgenmaennchen classUnderTest = Mockito.mock(Galgenmaennchen.class);
	private Stats statsMock = Mockito.mock(Stats.class);

	@BeforeEach
	void setVariables() {
		Mockito.doCallRealMethod().when(classUnderTest).setUser(Mockito.isA(User.class));
		Mockito.doCallRealMethod().when(classUnderTest).setLoesungswort(Mockito.isA(String.class));
		classUnderTest.setUser(userMock);
		classUnderTest.setLoesungswort("Testwort");
	}

	@Test
	void testStartGame() {
		Mockito.when(classUnderTest.spielAblaufBisEnde(Mockito.isA(Boolean.class), Mockito.isA(Integer.class)))
				.thenReturn(5);
		Mockito.doNothing().when(classUnderTest).clearVariablesBeforeGame();
		Mockito.when(classUnderTest.validateEnding(Mockito.isA(Integer.class)))
				.thenReturn("\nGlückwunsch, du hast gewonnen!");
		Mockito.doCallRealMethod().when(classUnderTest).startGame();
		classUnderTest.startGame();
		Mockito.verify(classUnderTest, Mockito.times(1)).startGame();
	}

	@Test
	void testSpielAblaufBisEnde() {
		Mockito.when(classUnderTest.spielAblaufBisEnde(Mockito.isA(Boolean.class), Mockito.isA(Integer.class)))
				.thenReturn(5);
		assertEquals(5, classUnderTest.spielAblaufBisEnde(true, 10));
	}

	@Test
	void testValidateEndingSieg() {
		Mockito.doCallRealMethod().when(classUnderTest).validateEnding(Mockito.isA(Integer.class));
		Mockito.when(userMock.getStats()).thenReturn(statsMock);
		Mockito.doNothing().when(statsMock).setGespielteSpiele(Mockito.isA(Integer.class));
		Mockito.doNothing().when(statsMock).setSiegeGGM(Mockito.isA(Integer.class));
		assertEquals("\nGlückwunsch, du hast gewonnen!", classUnderTest.validateEnding(10));
	}

	@Test
	void testValidateEndingNiederlage() {
		Mockito.doCallRealMethod().when(classUnderTest).validateEnding(Mockito.isA(Integer.class));
		Mockito.when(userMock.getStats()).thenReturn(statsMock);
		Mockito.doNothing().when(statsMock).setGespielteSpiele(Mockito.isA(Integer.class));
		assertEquals("\nDu hast leider verloren. Das gesuchte Wort war Testwort.", classUnderTest.validateEnding(0));
	}

}
