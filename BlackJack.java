import com.codecool.termlib.*;
import java.util.*;
import java.io.*;
import java.math.*;


public class BlackJack {

	private static final char[] cards = new char[] {
	'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A',
	'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A',
	'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A',
	'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'
	};

	private static final Scanner scanner = new Scanner(System.in);
	private static final Terminal terminal = new Terminal();
	
	private static boolean gameOn = false;


	public static void main(String[] args){
		
		
		while (gameOn == false) {
			gameOn = menu();
		}
			

		while(gameOn == true) {
			System.out.println("game is ON");
		}
			
		


	}

	private static boolean menu() {

		System.out.println("Welcome to the game!");
		System.out.println("Press 1 to play single!");
		if (scanner.next().charAt(0) == '1') {
			return true;			
		}
		else {
			return false;
		}
		
		
		

	}

}
