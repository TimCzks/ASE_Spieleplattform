package koordination.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import domain.code.User;
import konvertierung.ErbauerKonverter;
import koordination.DirektorKonverter;

class DirektorTest {

	private DirektorKonverter classUnderTest = Mockito.mock(DirektorKonverter.class);
	private ErbauerKonverter erbauer = Mockito.mock(ErbauerKonverter.class);
	private User userMock = Mockito.mock(User.class);

	@BeforeEach
	void setErbauer() {
		Mockito.doCallRealMethod().when(classUnderTest).setErbauer(erbauer);
		classUnderTest.setErbauer(erbauer);
	}

	@Test
	void testErstelleUser() {
		Mockito.doCallRealMethod().when(classUnderTest).erstelleUser(Mockito.isA(String.class));
		Mockito.when(erbauer.erstelleUser(Mockito.isA(String.class))).thenReturn(userMock);
		assertEquals(userMock, classUnderTest.erstelleUser("user"));
	}

	@Test
	void testSpeichereUserAb() {
		Mockito.doCallRealMethod().when(classUnderTest).speichereUserAb(Mockito.isA(User.class));
		Mockito.doNothing().when(erbauer).speichereUserAb(Mockito.isA(User.class));
		classUnderTest.speichereUserAb(userMock);
		Mockito.verify(classUnderTest, Mockito.times(1)).speichereUserAb(userMock);
	}

	@Test
	void testErmittleLoesungswortFuerGalgenmaennchen() {
		Mockito.doCallRealMethod().when(classUnderTest).ermittleLoesungswortFuerGalgenmaennchen();
		Mockito.when(erbauer.ermittleLoesungswortFuerGalgenmaennchen()).thenReturn("TestWort");
		assertEquals("TestWort", classUnderTest.ermittleLoesungswortFuerGalgenmaennchen());
	}

	@Test
	void testPruefeObUserBereitsExistiert() {
		Mockito.doCallRealMethod().when(classUnderTest).pruefeObUserBereitsExistiert(Mockito.isA(String.class));
		Mockito.when(erbauer.pruefeObUserBereitsExistiert(Mockito.isA(String.class))).thenReturn(true);
		assertEquals(true, classUnderTest.pruefeObUserBereitsExistiert("user"));
	}

}
