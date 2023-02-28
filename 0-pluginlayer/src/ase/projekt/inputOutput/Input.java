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
	public String[] leseDatenVonUserDatei(String username) {
		File f = new File(PATH + username + ".txt");
		String[] s = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while (br.ready()) {
				s = br.readLine().split(",");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public String[] leseLoesungswoerterVonDatei() {
		File f = new File(PATH + "GalgenmaennchenWoerter.txt");
		String[] s = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while (br.ready()) {
				s = br.readLine().split(",");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public boolean pruefeObUserBereitsExistiert(String username) {
		return new File(PATH + username + ".txt").exists();
	}
}
