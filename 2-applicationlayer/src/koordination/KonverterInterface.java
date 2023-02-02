package koordination;

import domain.code.User;

public interface KonverterInterface {

	public User erstelleUser(String username);

	public void speichereUser(User user);

	public String ermittleLoesungswort();
}
