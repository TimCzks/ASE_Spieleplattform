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
		String[] s = inputObj.leseDatenVonUserDatei(username);
		return new User(username, new Stats(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]),
				Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5])));
	}

	@Override
	public void speichereUserAb(User user) {
		String[] infos = new String[] { user.getUsername(), String.valueOf(user.getStats().getSiegeGGM()),
				String.valueOf(user.getStats().getRekordZR()), String.valueOf(user.getStats().getSiegeSSP()),
				String.valueOf(user.getStats().getNiederlagenSSP()),
				String.valueOf(user.getStats().getUnentschiedenSSP()),
				String.valueOf(user.getStats().getGespielteSpiele()) };
		outputObj.speichereUserAb(infos);
	}

	@Override
	public String ermittleLoesungswort() {
		String[] ggmWoerter = inputObj.leseLoesungswoerterVonDatei();
		return ggmWoerter[new Random().nextInt(10)];
	}

	@Override
	public boolean pruefeObUserBereitsExistiert(String username) {
		return inputObj.pruefeObUserBereitsExistiert(username);
	}

}
