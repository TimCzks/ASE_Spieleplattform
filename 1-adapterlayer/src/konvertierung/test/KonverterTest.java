package konvertierung.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ase.projekt.inputOutput.Input;
import domain.code.Stats;
import domain.code.User;
import konvertierung.Konverter;

class KonverterTest {

	private Konverter classUnderTest = Mockito.mock(Konverter.class);
	private Input input = Mockito.mock(Input.class);

	@BeforeEach
	void setInput() {
		Mockito.doCallRealMethod().when(classUnderTest).setInputObj(Mockito.isA(Input.class));
		classUnderTest.setInputObj(input);
	}

	@Test
	void testErstelleUser() {
		Mockito.when(input.leseDatenVonUserDatei(Mockito.isA(String.class)))
				.thenReturn(new String[] { "0", "0", "0", "0", "0", "0" });
		Mockito.doCallRealMethod().when(classUnderTest).erstelleUser(Mockito.isA(String.class));
		User userByMethod = classUnderTest.erstelleUser("username");
		User userToCompare = new User("username", new Stats(0, 0, 0, 0, 0, 0));
		assertEquals(userToCompare.getUsername(), userByMethod.getUsername());
	}

	@Test
	void testErmittleLoesungswort() {
		Mockito.when(input.leseLoesungswoerterVonDatei()).thenReturn(new String[] { "Loesungswort" });
		Mockito.doCallRealMethod().when(classUnderTest).ermittleLoesungswort(Mockito.isA(Integer.class));
		assertEquals("Loesungswort", classUnderTest.ermittleLoesungswort(1));
	}

	@Test
	void testPruefeObUserBereitsExistiert() {
		Mockito.when(input.pruefeObUserBereitsExistiert(Mockito.isA(String.class))).thenReturn(true);
		Mockito.doCallRealMethod().when(classUnderTest).pruefeObUserBereitsExistiert(Mockito.isA(String.class));
		assertEquals(true, classUnderTest.pruefeObUserBereitsExistiert("username"));
	}

}
