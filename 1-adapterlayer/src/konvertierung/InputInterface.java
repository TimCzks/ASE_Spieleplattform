package konvertierung;

public interface InputInterface {

	public String[] leseUserDateiEin(String username);

	public String[] leseLoesungswoerterEin();

	public boolean pruefeObUserBereitsExistiert(String username);
}
