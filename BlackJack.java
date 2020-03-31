package blackJack;

import java.util.Scanner;

public class BlackJack {
	
	static Scanner scan = new Scanner(System.in);

	static double deck[] ;
	//static double deck[] = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
	static boolean over = false;

	static int placeInDeck = 0;
	static Display table = new Display();;
	
	public static void main(String[] args) {
		 
		Shuffle newDeck = new Shuffle();
		
		newDeck.shuffle();
		deck = newDeck.getDeck();
		
		int input = 3;
		while(input == 3)
		{
		//System.out.println("here");
		runGame();
		input = table.getResult();
		table.setOver(false);
		}
	}
	
	public static void runGame()
	{		
		
		CardsInHand dealer = new CardsInHand();
		CardsInHand player = new CardsInHand();
		
		String announce = "";
		
		

		playerHit(table, player);
		
		dealerHit(table, dealer);
		
		playerHit(table, player);
		
		dealer.setHidden(deck[placeInDeck]);
		placeInDeck++;
		table.setDealer(dealer);

		if(( dealer.getDealerBlackJack() == true) && ( player.getPlayerBlackJack() == false))
		{
			announce = "Dealer black jack !";
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		else if (( dealer.getDealerBlackJack() == true) && ( player.getPlayerBlackJack() == true))
		{
			announce = "Tie";
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		else if (( dealer.getDealerBlackJack() == false) && ( player.getPlayerBlackJack() == true))
		{
			announce = "Player black jack !";
			over = true;
			dealer.revealHidden();
			table.updateDisplay();
		}
		
		
		if(over == false)
		{
			
			int input = table.getResult();
			
			while(input == 1) 
			{
				//System.out.println(input);
				playerHit(table, player);
				
				int scoreAce_1 = player.getScore_1();
				int scoreAce_11 = player.getScore_11();
				
				if((scoreAce_1 > 21) && (scoreAce_11 > 21))
				{
					announce = "Player bust, dealer wins";
					over = true;
					break;
				}
				
				if((scoreAce_1 == 21) || (scoreAce_11 == 21))
				{
					//announce = "Player wins, dealer wins";
					//over = true;
					break;
				}
				
				//System.out.println("hit?");
				
				input = table.getResult();
				
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
						announce = "Dealer bust, player wins";
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
					announce = "Player wins "+playerScore +" over " +dealerScore;

				}
				else if(playerScore < dealerScore)
				{
					announce = "Dealer wins "+dealerScore +" over " +playerScore;

				}
				else
				{
					announce = "Tie by " +dealerScore;

				}
				
			}
			
			
		}
		
		
			
		table.setOver(true);
		table.setAnnounce(announce);
		over = false;
		
	}
	

	public static void dealerHit(Display table, CardsInHand dealer)
	{
		dealer.addHand(deck[placeInDeck]);
		
		placeInDeck++;
		
		table.setDealer(dealer);
		
	}
	
	public static void playerHit(Display table, CardsInHand player)
	{
		
		player.addHand(deck[placeInDeck]);
		
		placeInDeck++;

		table.setPlayer(player);

	}
	
	
}
