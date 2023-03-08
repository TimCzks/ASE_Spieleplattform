package ase.projekt.inputOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import konvertierung.InputInterface;

public class Input implements InputInterface {

	private final String PATH = "./resources/";

	@Override
	public String[] leseDatenVonDatei(String dateiname, String seperator) {
		File f = new File(PATH + dateiname + ".txt");
		String[] geleseneWoerter = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while (br.ready()) {
				geleseneWoerter = br.readLine().split(seperator);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return geleseneWoerter;
	}

	@Override
	public boolean pruefeObUserBereitsExistiert(String username) {
		return new File(PATH + username + ".txt").exists();
	}
}
