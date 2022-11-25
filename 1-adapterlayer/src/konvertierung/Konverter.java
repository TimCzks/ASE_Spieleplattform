package konvertierung;

import domain.code.Stats;
import domain.code.User;
import koordination.KonverterInterface;

public class Konverter implements KonverterInterface {

	public User erstelleUser(String username) {
		InputInterface inputObj = new Input(); // TODO Input hier verwenden und importieren oder auf Interface
												// zugreifen?
		String[] s = inputObj.leseUserDateiEin(username);
		return new User(username, new Stats(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
	}

}
