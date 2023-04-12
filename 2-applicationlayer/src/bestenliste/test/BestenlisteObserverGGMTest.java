package bestenliste.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bestenliste.Bestenliste;
import bestenliste.BestenlisteObserverGGM;
import domain.code.User;
import koordination.PlattformVerwaltung;

public class BestenlisteObserverGGMTest {

	private PlattformVerwaltung spielePlattformMock = mock(PlattformVerwaltung.class);
	private BestenlisteObserverGGM classUnderTest = mock(BestenlisteObserverGGM.class);
	private Bestenliste bestenlisteMock = mock(Bestenliste.class);
	User userMock = mock(User.class);

	@BeforeEach
	public void setUp() {
		when(classUnderTest.getAktuelleBestenliste()).thenReturn(bestenlisteMock);
		Mockito.doCallRealMethod().when(classUnderTest).setAktuelleBestenliste(bestenlisteMock);
		classUnderTest.setAktuelleBestenliste(bestenlisteMock);
	}

	@Test
	public void testUpdateWithNewHighScore() {
		Mockito.doNothing().when(bestenlisteMock).ermittleBestenliste();
		Mockito.doNothing().when(classUnderTest).uebergebeNeueBestenlisteAn(spielePlattformMock);
		Mockito.doCallRealMethod().when(classUnderTest).update(5, "'Galgenmännchen'", spielePlattformMock);

		when(spielePlattformMock.getAktuellerUser()).thenReturn(userMock);
		when(userMock.getUsername()).thenReturn("TestUser");
		when(bestenlisteMock.getBestenlisteInZahlen()).thenReturn(new ArrayList<>(List.of(0)));
		when(bestenlisteMock.getBestenlisteAlsStringausdruck()).thenReturn(new ArrayList<>(List.of(
				Map.entry("'Galgenmännchen'", "Beste*r Spieler*in in 'Galgenmännchen' ist TestUser mit 5 Siegen."))));

		classUnderTest.update(5, "'Galgenmännchen'", spielePlattformMock);

		verify(bestenlisteMock).ermittleBestenliste();
		verify(classUnderTest).uebergebeNeueBestenlisteAn(spielePlattformMock);
	}

	@Test
	public void testUpdateMitNiedrigeremScore() {
		when(bestenlisteMock.getBestenlisteInZahlen()).thenReturn(List.of(10));

		classUnderTest.update(5, "'Galgenmännchen'", spielePlattformMock);

		verify(bestenlisteMock, never()).ermittleBestenliste();
		verify(spielePlattformMock, never()).speichereUserAb();
		verify(spielePlattformMock, never()).setBestenliste(classUnderTest.getAktuelleBestenliste());
	}

	@Test
	public void testUebergebeNeueBestenlisteAn() {
		Mockito.doCallRealMethod().when(classUnderTest).uebergebeNeueBestenlisteAn(spielePlattformMock);
		Mockito.doNothing().when(spielePlattformMock).speichereUserAb();
		Mockito.doNothing().when(spielePlattformMock).setBestenliste(Mockito.isA(Bestenliste.class));

		classUnderTest.uebergebeNeueBestenlisteAn(spielePlattformMock);

		verify(spielePlattformMock).speichereUserAb();
		verify(spielePlattformMock).setBestenliste(classUnderTest.getAktuelleBestenliste());
	}
}
