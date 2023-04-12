package konvertierung.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ase.projekt.inputOutput.Output;
import domain.code.Stats;
import domain.code.User;
import konvertierung.ErbauerKonverter;

class KonverterTest {

	private InputMock input = new InputMock();
	private Output output = Mockito.mock(Output.class);
	private ErbauerKonverter classUnderTestAsMock = Mockito.mock(ErbauerKonverter.class);
	private ErbauerKonverter classUnderTest = new ErbauerKonverter(input, output);

	@BeforeEach
	void setInput() {
		Mockito.doCallRealMethod().when(classUnderTestAsMock).setOutputObj(Mockito.isA(Output.class));
		classUnderTestAsMock.setOutputObj(output);
	}

	@Test
	void testErstelleUser() {
		User userByMethod = classUnderTest.erstelleUser("username");
		User userToCompare = new User("username", new Stats(0, 0, 0, 0, 0, 0));
		assertEquals(userToCompare.getUsername(), userByMethod.getUsername());
	}

	@Test
	void testErmittleLoesungswortFuerGalgenMaennchen() {
		assertEquals("Loesungswort", classUnderTest.ermittleLoesungswortFuerGalgenmaennchen());
	}

	@Test
	void testPruefeObUserBereitsExistiert() {
		assertEquals(true, classUnderTest.pruefeObUserBereitsExistiert("username"));
	}

	@Test
	void testSpeichereUserAb() {
		User testUser = new User("username", new Stats(0, 0, 0, 0, 0, 0));
		Mockito.doCallRealMethod().when(classUnderTestAsMock).speichereUserAb(testUser);
		Mockito.doNothing().when(output).speichereUserAb(Mockito.isA(String[].class));
		classUnderTestAsMock.speichereUserAb(testUser);
		Mockito.verify(classUnderTestAsMock, Mockito.times(1)).speichereUserAb(testUser);
	}

}
