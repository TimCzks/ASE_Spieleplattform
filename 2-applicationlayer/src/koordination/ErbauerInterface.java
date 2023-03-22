package koordination;

import domain.code.User;

public interface ErbauerInterface {

	public User erstelleUser(String username);

	public void speichereUserAb(User user);

	public String ermittleLoesungswortFuerGalgenmaennchen();

	public boolean pruefeObUserBereitsExistiert(String username);
}
