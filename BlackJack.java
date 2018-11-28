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
	
	private static boolean gameOn = true;
	private static boolean playerTurn = true;
	private static char[] playerHand = new char[] {
	'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'
	};

	private static int cardCounter = 2;
	private static int playerScore = 0;
	private static int dealerScore = 0;


	public static void main(String[] args){
		
		while (gameOn == true) {
		
			menu();
			
			firstDeal();	
			
			while ( playerTurn == true) {
				playerTurn();
			}
				
			
			if ( playerScore < 22 ) {
				dealerTurn();
			}
			endOfGame();			
			
		
		}
				
		


	}

	private static void menu() {

		System.out.println("Welcome to the game!");
	}
	

	private static void firstDeal() {
		cardCounter = 2;
		terminal.clearScreen();
		playerHand[0] = getOneRandomCard();
		playerScore = calculatePoints(playerHand[0]);
		playerHand[1] = getOneRandomCard();
		playerScore += calculatePoints(playerHand[1]);
		System.out.println("You got two cards: " + Character.toString(playerHand[0]) + " and " + Character.toString(playerHand[1]));

		System.out.println("Your score is " + Integer.toString(playerScore));
	}

	private static void playerTurn() {
		
		System.out.println("Do you want another card? (Y/N)");
		
		char playerInput = scanner.next().charAt(0);

		if (playerInput == 'y' || playerInput == 'Y') {

			playerHand[cardCounter] = getOneRandomCard();
			playerScore += calculatePoints(playerHand[cardCounter]);
			System.out.println("You got: " + Character.toString(playerHand[cardCounter]));
			System.out.println("Your new score: " + Integer.toString(playerScore));
			cardCounter += 1;
			if (playerScore > 21) {
				System.out.println("Sorry, you lost :(");
				playerTurn = false;	
			}

		} 
		if (playerInput == 'n' || playerInput == 'N') {	
			playerTurn = false;
		}
	}
	
	private static void dealerTurn() {
		cardCounter = 2;
		playerHand[0] = getOneRandomCard();
		dealerScore = calculatePoints(playerHand[0]);
		playerHand[1] = getOneRandomCard();
		dealerScore += calculatePoints(playerHand[1]);
		System.out.println("Dealer got two cards: " + Character.toString(playerHand[0]) + " and " + Character.toString(playerHand[1]));
		System.out.println("Dealer's score: " + Integer.toString(dealerScore));
		
		while (dealerScore < 17) {
			playerHand[cardCounter] = getOneRandomCard();
			dealerScore += calculatePoints(playerHand[cardCounter]);
			System.out.println("Dealer got: " + Character.toString(playerHand[cardCounter]));
			System.out.println("Dealer's new score: " + Integer.toString(dealerScore));
			cardCounter += 1;
		}
		
	}

	private static void endOfGame() {
		if (dealerScore > 21) {
			System.out.println("Dealer lost, free money for everyone!!! -yes you have just won-");
		}
		
		if (dealerScore > playerScore && dealerScore < 22) {
			System.out.println("Dealer was victorious this time, but not for long..");
		}
		
		if (dealerScore == playerScore) {
			System.out.println("It's a tie, you get your money back!!");
		}
		
		if (dealerScore < playerScore && playerScore < 22) {
			System.out.println("Wow, you won 5 if statements, you win for now...");
		}
		
		System.out.println("Press 'A' to play again / 'Q' to quit.");
		char playerInput = scanner.next().charAt(0);
		
		if (playerInput == 'A' || playerInput == 'a') {
			playerTurn = true;
			
		}
		
		else {
			gameOn = false;
		}
		
		
	}

	private static char getOneRandomCard(){
        Random rand = new Random();
        char dealtCards = '2';

        int randomIndex = rand.nextInt(52);
        dealtCards = cards[randomIndex];

        return dealtCards;

    }

    private static int calculatePoints(char card) {
        int points = 0;
	int score = dealerScore;
	if (playerTurn == true) {
		score = playerScore;
	}
        switch (card) {
            case '2' :
                points = 2;
		break;
            case '3' :
                points = 3;
		break;
            case '4' :
                points = 4;
		break;
            case '5' :
                points = 5;
		break;
            case '6' :
                points = 6;
		break;
            case '7' :
                points = 7;
		break;
            case '8' :
                points = 8;
		break;
            case '9' :
                points = 9;
		break;
            case '0' :
            case 'J' :
            case 'Q' :
            case 'K' :
                points = 10;
		break;
	    case 'A' :
	        if ((score + 11) > 21) {
	            points = 1;
		    break;
	        } else {
	            points = 11;
		    break;
	        }
           } return points;
       }

}
