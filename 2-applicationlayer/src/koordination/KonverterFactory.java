package koordination;

import konvertierung.InputFactory;
import konvertierung.Konverter;
import konvertierung.OutputFactory;

public class KonverterFactory {
	public Konverter getKonverter() {
		return new Konverter(new InputFactory().getInput(), new OutputFactory().getOutput());
	}
}
