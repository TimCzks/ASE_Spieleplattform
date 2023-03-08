package spiele;

import java.util.Random;
import java.util.Scanner;

public class ZahlenRaten {
	private final int number;
	private int count;
	private Scanner sc;

	public ZahlenRaten(Scanner sc) {
		super();
		this.sc = sc;
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
				inGame = false;
				break;
			}
		} while (inGame);
	}

	public int validiereSpielergebnis() {
		System.out
				.println("Richtig getippt, die Zahl war " + number + ", und du hast " + count + " Versuche gebraucht!");
		return ++count;
	}
}
