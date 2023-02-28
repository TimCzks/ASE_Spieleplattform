package spiele.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import spiele.ZahlenRaten;

class ZahlenRatenTest {

	private ZahlenRaten classUnderTest = new ZahlenRaten(67);
	private ZahlenRaten zrMockedClass = Mockito.mock(ZahlenRaten.class);

	@Test
	void testZahlenRaten() {
		int outcome = classUnderTest.checkNumber(67);
		assertThat(outcome, is(0));
	}

	@Test
	void testZahlenRatenGross() {
		int outcome = classUnderTest.checkNumber(68);
		assertThat(outcome, is(1));

	}

	@Test
	void testZahlenRatenKlein() {
		int outcome = classUnderTest.checkNumber(65);
		assertThat(outcome, is(-1));
	}

	@Test
	void testException() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> classUnderTest.checkNumber("a"));
		assertThat(exception.getMessage(), is("Nur Zahlen erlaubt."));
	}

	@Test
	void testSpielende() {
		Mockito.when(zrMockedClass.spielende())
				.thenReturn("Richtig getippt, die Zahl war 67, und du hast 1 Versuche gebraucht!");
		assertThat(zrMockedClass.spielende(),
				is("Richtig getippt, die Zahl war 67, und du hast 1 Versuche gebraucht!"));
	}
}
