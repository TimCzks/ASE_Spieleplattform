package konvertierung;

import dateien.ein.auslesen.Input;
import domain.code.Stats;
import domain.code.User;

public class Konverter {

	public User erstelleUser(String username) {
		Input inputObj = new Input();
		String[] s = inputObj.leseUserDateiEin(username);
		return new User(username, new Stats(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
	}

}
