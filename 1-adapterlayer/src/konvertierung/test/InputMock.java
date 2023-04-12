package konvertierung.test;

import ase.projekt.inputOutput.Input;

public class InputMock extends Input {

	@Override
	public String[] leseDatenVonDatei(String dateiname, String seperator) {
		if (dateiname.equals("GalgenmaennchenWoerter")) {
			return new String[] { "Loesungswort" };
		}
		return new String[] { "0", "0", "0", "0", "0", "0" };
	}

	@Override
	public boolean pruefeObUserBereitsExistiert(String username) {
		return true;
	}

	@Override
	public String[] ermittleAlleUsernamen() {
		return null;
	}
}
