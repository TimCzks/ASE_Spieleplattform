package koordination;

import domain.code.User;

public interface KonverterInterface {

	public User erstelleUser(String username);

	public void speichereUserAb(User user);

	public String ermittleLoesungswortFuerGalgenmaennchen();

	public boolean pruefeObUserBereitsExistiert(String username);
}
