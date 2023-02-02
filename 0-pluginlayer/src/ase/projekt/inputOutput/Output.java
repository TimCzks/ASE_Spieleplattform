package ase.projekt.inputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import konvertierung.OutputInterface;

public class Output implements OutputInterface {

	@Override
	public void speichereUserAb(String[] userInfos) {
		File f = new File("C:\\ASE_Spieleplattform\\" + userInfos[0] + ".txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(userInfos[1] + "," + userInfos[2] + "," + userInfos[3]);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
