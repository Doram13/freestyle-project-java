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
	private static boolean player2Turn = true;
	
	private static char[] playerHand = new char[] {
	'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'
	};
	private static char[] player2Hand = new char[] {
		'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'
		};

	private static int cardCounter = 2;
	private static int playerScore = 0;
	private static int player2cardCounter = 2;
	private static int player2Score = 0;
	private static int dealerScore = 0;
	private static int playerNum = 3; //Too clever code! (should be 0...)


	public static void main(String[] args){
		while (gameOn == true) {
			
			playerNum = menu();
			if (playerNum == 1) {
				firstDeal();	
				while ( playerTurn == true) {
					playerTurn();
				}	
				if ( playerScore < 22 ) {
					dealerTurn();
				}
				endOfGame();
			}
			else {
				firstDeal();
				firstDealPlayer2();
				while (playerTurn == true) {
					playerTurn();
				}
				while (player2Turn == true) {
					player2Turn();
				}
				if ( playerScore < 22 || player2Score < 22) {
					dealerTurn();
				}
				endOfGame();
			}
		}
				
		


	}
	//b n
	private static int menu() {
		
		System.out.println("Welcome to the game!");
		while (playerNum > 2) {
		System.out.println("Press 1 to play against the Dealer. Press 2 to play with a friend (or foe)");
		try {
			playerNum = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number");
		}
		}
		return playerNum;
	}
	

	private static void firstDeal() {
		cardCounter = 2;
		terminal.clearScreen();
		playerHand[0] = getOneRandomCard();
		playerScore = calculatePoints(playerHand[0]);
		playerHand[1] = getOneRandomCard();
		playerScore += calculatePoints(playerHand[1]);
		// We should change char '0' to string '10'!
		System.out.println("Player1: You got two cards: " + Character.toString(playerHand[0]) + " and " + Character.toString(playerHand[1]));
		//Change the color of Player1
		System.out.println("Player1: Your score is " + Integer.toString(playerScore));
	}

	private static void firstDealPlayer2() {
		player2cardCounter = 2;
		terminal.clearScreen();
		player2Hand[0] = getOneRandomCard();
		player2Score = calculatePoints(player2Hand[0]);
		player2Hand[1] = getOneRandomCard();
		player2Score += calculatePoints(player2Hand[1]);
		System.out.println("Player2: You got two cards: " + Character.toString(player2Hand[0]) + " and " + Character.toString(player2Hand[1]));
		//Change color of Player2
		System.out.println("Player2: Your score is " + Integer.toString(player2Score));
	}

	private static void playerTurn() {
		
		System.out.println("Player1: Do you want another card? (Y/N)");
		
		char playerInput = scanner.next().charAt(0);

		if (playerInput == 'y' || playerInput == 'Y') {

			playerHand[cardCounter] = getOneRandomCard();
			playerScore += calculatePoints(playerHand[cardCounter]);
			System.out.println("Player1: You got: " + Character.toString(playerHand[cardCounter]));
			System.out.println("Player1: Your new score: " + Integer.toString(playerScore));
			cardCounter += 1;
			if (playerScore > 21) {
				System.out.println("Player1: Sorry, you lost :(");
				playerTurn = false;	
			}

		} 
		if (playerInput == 'n' || playerInput == 'N') {	
			playerTurn = false;
		}
	}

	private static void player2Turn() {
		
		System.out.println("Player2: Do you want another card? (Y/N)");
		
		char playerInput = scanner.next().charAt(0);

		if (playerInput == 'y' || playerInput == 'Y') {

			player2Hand[cardCounter] = getOneRandomCard();
			player2Score += calculatePoints(player2Hand[cardCounter]);
			System.out.println("Player2: You got: " + Character.toString(player2Hand[cardCounter]));
			System.out.println("Player2: Your new score: " + Integer.toString(player2Score));
			cardCounter += 1;
			if (player2Score > 21) {
				System.out.println("Player2: Sorry, you lost :(");
				player2Turn = false;	
			}

		} 
		if (playerInput == 'n' || playerInput == 'N') {	
			player2Turn = false;
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
