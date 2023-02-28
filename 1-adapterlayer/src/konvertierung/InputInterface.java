package konvertierung;

public interface InputInterface {

	public String[] leseDatenVonUserDatei(String username);

	public String[] leseLoesungswoerterVonDatei();

	public boolean pruefeObUserBereitsExistiert(String username);
}
