package koordination;

import domain.code.User;

public class KonverterService {

	private final KonverterInterface konverter;

	public KonverterService(KonverterInterface konverter) {
		super();
		this.konverter = konverter;
	}

	public User erstelleUser(String username) {
		return konverter.erstelleUser(username);
	}
}
