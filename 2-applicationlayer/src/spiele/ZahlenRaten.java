package spiele;

import java.util.Random;
import java.util.Scanner;

import domain.code.User;

public class ZahlenRaten {
	private final int number;
	private int count;
	private Scanner sc;
	private User user;

	public ZahlenRaten(Scanner sc, User user) {
		super();
		this.sc = sc;
		this.user = user;
		this.count = 0;
		this.number = new Random().nextInt(100);
	}

	public ZahlenRaten(int number) {
		this.number = number;
	}

	public int checkNumber(int guess) {
		if (guess > number)
			return 1;
		if (guess < number)
			return -1;
		return 0;
	}

	public int checkNumber(String string) {
		try {
			return checkNumber(Integer.parseInt(string));
		} catch (Exception e) {
			throw new NumberFormatException("Nur Zahlen erlaubt.");
		}
	}

	public void startGame() {
		boolean inGame = true;
		do {
			System.out.println("Bitte gib eine Zahl zwischen 0 und 100 ein.");
			switch (checkNumber(sc.next())) {
			case -1:
				count++;
				System.out.println("Die Zahl ist zu klein. Bisherige Anzahl Versuche: " + count);
				break;
			case 1:
				count++;
				System.out.println("Die Zahl ist zu groß. Bisherige Anzahl Versuche: " + count);
				break;
			case 0:
				System.out.println(spielende());
				inGame = false;
				break;
			}
		} while (inGame);
	}

	public String spielende() {
		this.user.getStats().setGespielteSpiele(this.user.getStats().getGespielteSpiele() + 1);
		if (this.user.getStats().getRekordZR() > ++count) {
			this.user.getStats().setRekordZR(count);
		}
		return "Richtig getippt, die Zahl war " + number + ", und du hast " + count + " Versuche gebraucht!";
	}
}
