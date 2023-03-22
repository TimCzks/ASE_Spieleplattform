package konvertierung.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ase.projekt.inputOutput.Input;
import ase.projekt.inputOutput.Output;
import domain.code.Stats;
import domain.code.User;
import konvertierung.ErbauerKonverter;

class KonverterTest {

	private ErbauerKonverter classUnderTest = Mockito.mock(ErbauerKonverter.class);
	private Input input = Mockito.mock(Input.class);
	private Output output = Mockito.mock(Output.class);

	@BeforeEach
	void setInput() {
		Mockito.doCallRealMethod().when(classUnderTest).setInputObj(Mockito.isA(Input.class));
		classUnderTest.setInputObj(input);
		Mockito.doCallRealMethod().when(classUnderTest).setOutputObj(Mockito.isA(Output.class));
		classUnderTest.setOutputObj(output);
	}

	@Test
	void testErstelleUser() {
		Mockito.when(input.leseDatenVonDatei(Mockito.isA(String.class), Mockito.isA(String.class)))
				.thenReturn(new String[] { "0", "0", "0", "0", "0", "0" });
		Mockito.doCallRealMethod().when(classUnderTest).erstelleUser(Mockito.isA(String.class));
		User userByMethod = classUnderTest.erstelleUser("username");
		User userToCompare = new User("username", new Stats(0, 0, 0, 0, 0, 0));
		assertEquals(userToCompare.getUsername(), userByMethod.getUsername());
	}

	@Test
	void testErmittleLoesungswortFuerGalgenMaennchen() {
		Mockito.when(input.leseDatenVonDatei("GalgenmaennchenWoerter", ","))
				.thenReturn(new String[] { "Loesungswort" });
		Mockito.doCallRealMethod().when(classUnderTest).ermittleLoesungswortFuerGalgenmaennchen();
		assertEquals("Loesungswort", classUnderTest.ermittleLoesungswortFuerGalgenmaennchen());
	}

	@Test
	void testPruefeObUserBereitsExistiert() {
		Mockito.when(input.pruefeObUserBereitsExistiert(Mockito.isA(String.class))).thenReturn(true);
		Mockito.doCallRealMethod().when(classUnderTest).pruefeObUserBereitsExistiert(Mockito.isA(String.class));
		assertEquals(true, classUnderTest.pruefeObUserBereitsExistiert("username"));
	}

	@Test
	void testSpeichereUserAb() {
		User testUser = new User("username", new Stats(0, 0, 0, 0, 0, 0));
		Mockito.doCallRealMethod().when(classUnderTest).speichereUserAb(testUser);
		Mockito.doNothing().when(output).speichereUserAb(Mockito.isA(String[].class));
		classUnderTest.speichereUserAb(testUser);
		Mockito.verify(classUnderTest, Mockito.times(1)).speichereUserAb(testUser);
	}

}
