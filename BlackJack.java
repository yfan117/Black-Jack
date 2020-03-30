package blackJack;

import java.util.Scanner;

public class BlackJack {
	
	static Scanner scan = new Scanner(System.in);

	static double deck[] ;
	static boolean over = false;

	static int placeInDeck = 0;

	
	public static void main(String[] args) {
		 
		Shuffle newDeck = new Shuffle();
		newDeck.shuffle();
		deck = newDeck.getDeck();
		
		runGame();
		
	}
	
	public static void runGame()
	{		
		
		CardsInHand dealer = new CardsInHand();
		CardsInHand player = new CardsInHand();

		
		Display table = new Display();

		playerHit(table, player);
		
		dealerHit(table, dealer);

		playerHit(table, player);
		
		dealer.setHidden(deck[placeInDeck]);
		placeInDeck++;
		table.setDealer(dealer);

		if(( dealer.getDealerBlackJack() == true) && ( player.getPlayerBlackJack() == false))
		{
			System.out.println("dealer black jack, dealer wins");
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		else if (( dealer.getDealerBlackJack() == true) && ( player.getPlayerBlackJack() == true))
		{
			System.out.println("tie, push");
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		else if (( dealer.getDealerBlackJack() == false) && ( player.getPlayerBlackJack() == true))
		{
			System.out.println("player black jack, playe wins");
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		
		
		if(over == false)
		{
			table.displayButton();
			System.out.println("hit?");
			int input = scan.nextInt();
			while(input == 1) 
			{
				playerHit(table, player);
				
				int scoreAce_1 = player.getScore_1();
				int scoreAce_11 = player.getScore_11();
				
				if((scoreAce_1 > 21) && (scoreAce_11 > 21))
				{
					System.out.println("player bust, dealer wins");
					over = true;
					break;
				}
				
				if((scoreAce_1 == 21) || (scoreAce_11 == 21))
				{
					//System.out.println("player wins, dealer wins");
					//over = true;
					break;
				}
				
				System.out.println("hit?");
				table.displayButton();
				input = scan.nextInt();

			}
			
			
			
			if(over == true)
			{
				dealer.revealHidden();
				table.updateDisplay();
			}
			else
			{
				dealer.revealHidden();
				table.updateDisplay();
				
				int scoreAce_1 = dealer.getScore_1();
				int scoreAce_11 = dealer.getScore_11();
				
				while((scoreAce_1 < 17) && (scoreAce_11 < 17))
				{
					dealerHit(table, dealer);
					
					scoreAce_1 = dealer.getScore_1();
					scoreAce_11 = dealer.getScore_11();
					//System.out.println(scoreAce_1);
					//System.out.println(scoreAce_11);

					
					if((scoreAce_1 > 21) && (scoreAce_11 > 21))
					{
						System.out.println("dealer bust, player wins");
						over = true;
						break;
					}
					
					//if(((scoreAce_1 > 17) && (scoreAce_1 <= 21)) || ((scoreAce_11 > 17) && (scoreAce_11 <= 21)))
					if((scoreAce_1 > 17) && (scoreAce_1 <= 21))
					{
						break;
					}
					
				}
				
			}
			
			if(over == false)
			{
				int dealerScore = 0;
				int playerScore = 0;
				
				/*
				System.out.println(dealer.getScore_1());
				System.out.println(dealer.getScore_11());
				
				System.out.println(player.getScore_1());
				System.out.println(player.getScore_11());
				*/
				
				if(dealer.getScore_1() > 21)
				{
					dealerScore = dealer.getScore_11();
				}
				else if(dealer.getScore_11() > 21)
				{
					dealerScore = dealer.getScore_1();
				}
				else if(dealer.getScore_1() == dealer.getScore_11())
				{
					dealerScore = dealer.getScore_11();
				}
				else if(dealer.getScore_1() > dealer.getScore_11())
				{
					dealerScore = dealer.getScore_1();
				}
				else if(dealer.getScore_1() < dealer.getScore_11())
				{
					dealerScore = dealer.getScore_11();
				}
				
				if(player.getScore_1() > 21)
				{
					playerScore = dealer.getScore_11();
				}
				else if(player.getScore_11() > 21)
				{
					playerScore = player.getScore_1();
				}
				else if(player.getScore_1() == player.getScore_11())
				{
					playerScore = player.getScore_11();
				}
				else if(player.getScore_1() > player.getScore_11())
				{
					playerScore = player.getScore_1();
				}
				else if(player.getScore_1() < player.getScore_11())
				{
					playerScore = player.getScore_11();
				}
				
				if(playerScore > dealerScore)
				{
					System.out.println("player wins "+playerScore +" over " +dealerScore);

				}
				else if(playerScore < dealerScore)
				{
					System.out.println("dealer wins "+dealerScore +" over " +playerScore);

				}
				else
				{
					System.out.println("tie by " +dealerScore);

				}
				
			}
			
			
			
			
		}
	}
	
	/*
	public static void firstHit(Display table, CardsInHand dealer)
	{
		dealer.setHidden(deck[placeInDeck]);
		placeInDeck++;

	}
	*/
	public static void dealerHit(Display table, CardsInHand dealer)
	{
		//System.out.println(deck[placeInDeck]);
		
		
		
		dealer.addHand(deck[placeInDeck]);
		
		placeInDeck++;
		
		table.setDealer(dealer);
		
	}
	
	public static void playerHit(Display table, CardsInHand player)
	{
		//System.out.println(deck[placeInDeck]);
		
		

		
		player.addHand(deck[placeInDeck]);
		
		placeInDeck++;

		table.setPlayer(player);

	}
	
	
	
	

}
