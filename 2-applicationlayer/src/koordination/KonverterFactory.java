package koordination;

import konvertierung.InputFactory;
import konvertierung.ErbauerKonverter;
import konvertierung.OutputFactory;

public class KonverterFactory {
	public ErbauerKonverter getKonverter() {
		return new ErbauerKonverter(new InputFactory().getInput(), new OutputFactory().getOutput());
	}
}
