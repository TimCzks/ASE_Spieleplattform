package konvertierung;

import java.util.Random;

import domain.code.Stats;
import domain.code.User;
import koordination.KonverterInterface;

public class Konverter implements KonverterInterface {

	private InputInterface inputObj;
	private OutputInterface outputObj;

	public Konverter(InputInterface inputObj, OutputInterface outputObj) {
		super();
		this.inputObj = inputObj;
		this.outputObj = outputObj;
	}

	@Override
	public User erstelleUser(String username) {
		String[] s = inputObj.leseUserDateiEin(username);
		return new User(username, new Stats(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
	}

	@Override
	public void speichereUser(User user) {
		String[] infos = new String[] { user.getUsername(), String.valueOf(user.getStats().getSiegeGGM()),
				String.valueOf(user.getStats().getRekordZR()), String.valueOf(user.getStats().getGespielteSpiele()) };
		outputObj.speichereUserAb(infos);
	}

	@Override
	public String ermittleLoesungswort() {
		String[] ggmWoerter = inputObj.leseLoesungswoerterEin();
		return ggmWoerter[new Random().nextInt(10)];
	}

}
