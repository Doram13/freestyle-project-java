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
	
	private static int turn = 1;
	private static boolean gameOn = true;
	private static boolean matchOn = false;
	private static boolean playerInGame = true;
	private static boolean player2InGame = true;
	
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
				while ( playerInGame == true) {
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
				while (matchOn == true) {
					if (playerInGame==true && turn==1) {
					playerTurn();}
					turn = 2;
					if (player2InGame == true && turn==2) {
					player2Turn();}
					turn = 1;
					if (player2InGame==false && playerInGame==false) {
						matchOn = false;
					}
				}
				
				if ( playerScore < 22 || player2Score < 22) {
					dealerTurn();
				}
				endOfMulti();

			}
		}
	}

	private static int menu() {
		
		System.out.println("Welcome to the game!");
		while (playerNum > 2) {
		System.out.println("Press 1 to play against the Dealer. Press 2 to play with a friend (or foe)");
		try {
			playerNum = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter 1 or 2!");
		}
		}
		matchOn = true;
		return playerNum;
	}
	

	private static void firstDeal() {
		cardCounter = 2;
		//terminal.clearScreen();
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
		//terminal.clearScreen();
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
			turn+=1;
			if (playerScore > 21) {
				System.out.println("Player1: Sorry, you lost :(");
				playerScore = -1;
				playerInGame = false;
				
			}

		} 
		if (playerInput == 'n' || playerInput == 'N') {	
			playerInGame = false;
		}
		
	}


	private static void player2Turn() {
		
		System.out.println("Player2: Do you want another card? (Y/N)");
		
		char playerInput = scanner.next().charAt(0);

		if (playerInput == 'y' || playerInput == 'Y') {

			player2Hand[cardCounter] = getOneRandomCard();
			player2Score += calculatePointsPlayer2(player2Hand[cardCounter]);
			System.out.println("Player2: You got: " + Character.toString(player2Hand[cardCounter]));
			System.out.println("Player2: Your new score: " + Integer.toString(player2Score));
			cardCounter += 1;
			turn += 1;
			if (player2Score > 21) {
				System.out.println("Player2: Sorry, you lost :(");
				player2Score = -1;
				player2InGame = false;	
			}

		} 
		if (playerInput == 'n' || playerInput == 'N') {	
			player2InGame = false;
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
			playerInGame = true;
			
		}
		
		else {
			gameOn = false;
		}
		
		
	}

	private static void endOfMulti() {
		if (dealerScore > 21) {
			System.out.println("Dealer lost, free money for everyone!!! -yes you have just won-");
		}
		else if (dealerScore > playerScore && dealerScore > player2Score) {
			System.out.println("Dealer was victorious this time, but not for long..");
		}
		
		else if (dealerScore == playerScore && dealerScore == player2Score) {
			System.out.println("It's a tie, you get your money back!!");
		}
		
		else if (dealerScore < playerScore && playerScore < 22 && playerScore > player2Score) {
			System.out.println("PLAYER1 is Victorious! You won 10 Terminal Coins! Spend it wisely.");
		}

		else if (dealerScore < player2Score && player2Score < 22 && player2Score > playerScore) {
			System.out.println("PLAYER2 is Victorious! You won 10 Terminal Coins! Spend it wisely.");
		};
		
		System.out.println("Press 'A' to play again / 'Q' to quit.");
		char playerInput = scanner.next().charAt(0);
		
		if (playerInput == 'A' || playerInput == 'a') {
			playerInGame = true;
			player2InGame = true;
			
		}
		
		else {
			gameOn = false;
		}

		turn=1;
		// if some player gets 21, but then the dealer loses, then both player 1 and 2 win?
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
	if (playerInGame == true) {
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
	   
	private static int calculatePointsPlayer2(char card) {
		int points = 0;
		int score = dealerScore;
		if (playerInGame == true) {
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
