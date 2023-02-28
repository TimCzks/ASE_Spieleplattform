package koordination;

import domain.code.User;

public interface KonverterInterface {

	public User erstelleUser(String username);

	public void speichereUserAb(User user);

	public String ermittleLoesungswort();

	public boolean pruefeObUserBereitsExistiert(String username);
}
