package ase.projekt.inputOutput;

public class Input {
  public String[] leseUserDateiEin(String username) {
      File f = new File("C:\\ASE_Spieleplattform\\" + username + ".txt");
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
}
