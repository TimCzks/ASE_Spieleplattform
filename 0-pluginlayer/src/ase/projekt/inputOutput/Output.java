package ase.projekt.inputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import konvertierung.OutputInterface;

public class Output implements OutputInterface {

	private final String PATH = "./resources/Users/";

	@Override
	public void speichereUserAb(String[] userInfos) {
		File f = new File(PATH + userInfos[0] + ".txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(userInfos[1] + "," + userInfos[2] + "," + userInfos[3] + "," + userInfos[4] + ","
					+ userInfos[5] + "," + userInfos[6]);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
