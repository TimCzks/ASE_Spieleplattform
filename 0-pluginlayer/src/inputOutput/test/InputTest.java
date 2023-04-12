package inputOutput.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ase.projekt.inputOutput.Input;

class InputTest {

	private Input classUnderTest = new Input();

	@Test
	void testLeseDatenVonDatei() {
		String[] actual = classUnderTest.leseDatenVonDatei("Users/TestUser", ",");
		String[] expected = new String[] { "1", "7", "0", "0", "0", "2" };
		assertEquals(expected[1], actual[1]);
	}

	@Test
	void testPruefeObUserBereitsExistiert() {
		boolean actual = classUnderTest.pruefeObUserBereitsExistiert("TestUser");
		assertEquals(true, actual);
	}

}
