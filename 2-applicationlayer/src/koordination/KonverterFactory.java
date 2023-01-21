package koordination;

import konvertierung.InputFactory;
import konvertierung.Konverter;

public class KonverterFactory {
	public Konverter getKonverter() {
		return new Konverter(new InputFactory().getInput());
	}
}
