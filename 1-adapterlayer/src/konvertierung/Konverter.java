package konvertierung;

import domain.code.Stats;
import domain.code.User;
import koordination.KonverterInterface;

public class Konverter implements KonverterInterface {

	private InputInterface inputObj;

	public Konverter(InputInterface inputObj) {
		super();
		this.inputObj = inputObj;
	}

	public User erstelleUser(String username) {
		String[] s = inputObj.leseUserDateiEin(username);
		return new User(username, new Stats(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
	}

}
