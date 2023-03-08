package konvertierung;

public interface InputInterface {

	public String[] leseDatenVonDatei(String dateiname, String seperator);

	public boolean pruefeObUserBereitsExistiert(String username);
}
