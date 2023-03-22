package koordination;

import domain.code.User;

public class DirektorKonverter {

	private ErbauerInterface erbauer = new KonverterFactory().getKonverter();

	public User erstelleUser(String username) {
		return erbauer.erstelleUser(username);
	}

	public void speichereUserAb(User user) {
		erbauer.speichereUserAb(user);
	}

	public String ermittleLoesungswortFuerGalgenmaennchen() {
		return erbauer.ermittleLoesungswortFuerGalgenmaennchen();
	}

	public boolean pruefeObUserBereitsExistiert(String username) {
		return erbauer.pruefeObUserBereitsExistiert(username);
	}

	public void setErbauer(ErbauerInterface erbauer) {
		this.erbauer = erbauer;
	}
}
