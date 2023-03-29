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
		Mockito.doCallRealMethod().when(classUnderTest).setLoesungswort(Mockito.isA(String.class));
		;
		classUnderTest.setLoesungswort("Testwort");
	}

	@Test
	void testStartGame() {
		Mockito.when(classUnderTest.spielAblaufBisEnde(Mockito.isA(Boolean.class))).thenReturn(5);
		Mockito.doNothing().when(classUnderTest).clearVariablesBeforeGame();
		Mockito.when(classUnderTest.validiereSpielergebnis(Mockito.isA(Integer.class))).thenReturn(1);
		Mockito.doCallRealMethod().when(classUnderTest).starteSpiel();
		classUnderTest.starteSpiel();
		Mockito.verify(classUnderTest, Mockito.times(1)).starteSpiel();
	}

	@Test
	void testSpielAblaufBisEnde() {
		Mockito.when(classUnderTest.spielAblaufBisEnde(Mockito.isA(Boolean.class))).thenReturn(5);
		assertEquals(5, classUnderTest.spielAblaufBisEnde(true));
	}

	@Test
	void testValidateEndingSieg() {
		Mockito.doCallRealMethod().when(classUnderTest).validiereSpielergebnis(Mockito.isA(Integer.class));
		assertEquals(1, classUnderTest.validiereSpielergebnis(10));
	}

	@Test
	void testValidateEndingNiederlage() {
		Mockito.doCallRealMethod().when(classUnderTest).validiereSpielergebnis(Mockito.isA(Integer.class));
		assertEquals(0, classUnderTest.validiereSpielergebnis(0));
	}

}
