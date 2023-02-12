package domain.code;

public class Stats {

	private int siegeGGM;
	private int rekordZR;
	private int gespielteSpiele;

	@Override
	public String toString() {
		return "Stats: Siege in Galgenmännchen: " + siegeGGM + ", Niedrigste Anzahl an Versuchen bei ZR: " + rekordZR
				+ ",  Insgesamt gespielte Spiele: " + gespielteSpiele;
	}

	public Stats(int siegeGGM, int rekordZR, int gespielteSpiele) {
		super();
		this.siegeGGM = siegeGGM;
		this.rekordZR = rekordZR;
		this.gespielteSpiele = gespielteSpiele;
	}

	public int getSiegeGGM() {
		return siegeGGM;
	}

	public void setSiegeGGM(int siegeGGM) {
		this.siegeGGM = siegeGGM;
	}

	public int getRekordZR() {
		return rekordZR;
	}

	public void setRekordZR(int siegeSV) {
		this.rekordZR = siegeSV;
	}

	public int getGespielteSpiele() {
		return gespielteSpiele;
	}

	public void setGespielteSpiele(int gespielteSpiele) {
		this.gespielteSpiele = gespielteSpiele;
	}

}
