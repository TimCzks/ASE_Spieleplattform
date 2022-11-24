package domain.code;

public class Stats {

	private int siegeGGM;
	private int siegeSV;
	private int gespielteSpiele;

	public int getSiegeGGM() {
		return siegeGGM;
	}

	@Override
	public String toString() {
		return "Stats: Siege in Galgenmännchen = " + siegeGGM + ", Siege in Schiffe Versenken = " + siegeSV
				+ ",  Insgesamt gespielte Spiele = " + gespielteSpiele;
	}

	public Stats(int siegeGGM, int siegeSV, int gespielteSpiele) {
		super();
		this.siegeGGM = siegeGGM;
		this.siegeSV = siegeSV;
		this.gespielteSpiele = gespielteSpiele;
	}

	public void setSiegeGGM(int siegeGGM) {
		this.siegeGGM = siegeGGM;
	}

	public int getSiegeSV() {
		return siegeSV;
	}

	public void setSiegeSV(int siegeSV) {
		this.siegeSV = siegeSV;
	}

	public int getGespielteSpiele() {
		return gespielteSpiele;
	}

	public void setGespielteSpiele(int gespielteSpiele) {
		this.gespielteSpiele = gespielteSpiele;
	}

}
