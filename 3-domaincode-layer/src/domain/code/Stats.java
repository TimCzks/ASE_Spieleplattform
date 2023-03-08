package domain.code;

public class Stats {

	private int siegeGGM, rekordZR, gespielteSpiele, siegeSSP, niederlagenSSP, unentschiedenSSP;

	public Stats(int siegeGGM, int rekordZR, int siegeSSP, int niederlagenSSP, int unentschiedenSSP,
			int gespielteSpiele) {
		super();
		this.siegeGGM = siegeGGM;
		this.rekordZR = rekordZR;
		this.gespielteSpiele = gespielteSpiele;
		this.siegeSSP = siegeSSP;
		this.niederlagenSSP = niederlagenSSP;
		this.unentschiedenSSP = unentschiedenSSP;
	}

	@Override
	public String toString() {
		return "Stats:\nSiege in Galgenmännchen: " + siegeGGM + "\nNiedrigste Anzahl an Versuchen bei ZR: " + rekordZR
				+ "\nSiege/Niederlagen/Unentschieden in SSP: " + siegeSSP + "/" + niederlagenSSP + "/"
				+ unentschiedenSSP + "\nInsgesamt gespielte Spiele: " + gespielteSpiele;
	}

	public int getSiegeSSP() {
		return siegeSSP;
	}

	public void setSiegeSSP(int siegeSSP) {
		this.siegeSSP = siegeSSP;
	}

	public int getNiederlagenSSP() {
		return niederlagenSSP;
	}

	public void setNiederlagenSSP(int niederlagenSSP) {
		this.niederlagenSSP = niederlagenSSP;
	}

	public int getUnentschiedenSSP() {
		return unentschiedenSSP;
	}

	public void setUnentschiedenSSP(int unentschiedenSSP) {
		this.unentschiedenSSP = unentschiedenSSP;
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
