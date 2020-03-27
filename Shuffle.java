package blackJack;

import java.util.Random;

public class Shuffle {
	
	private static int allCards[][] = new int[4][13];
	private static double deck[] = new double[312];

	public Shuffle()
	{
		shuffle();
	}
	
	public double[] getDeck()
	{
		return deck;
	}
	
	public static void resetCards()
	{
		
		 for(int a = 0; a < 4; a++)
		 {
			 for(int b = 0; b < 13; b++)
			 {
				 allCards[a][b] = b + 1; 
				 

			 }
		 }

	}
	
	public void shuffle()
	{
		
		Random rand = new Random();
		double cardValue;
		int suit;
		int defaultCardOrder;
		
		for(int numDeck = 0; numDeck < 6 ; numDeck ++)
		{
			resetCards();
			int placeInDeck = numDeck * 52;
			for(int i = placeInDeck; i < (placeInDeck + 52); i++)
			{
				cardValue = 0;
				suit = 0;
				defaultCardOrder = 0;
				while(cardValue == 0)
				{
					suit = rand.nextInt(4);
					defaultCardOrder = rand.nextInt(13);
					cardValue = allCards[suit][defaultCardOrder];
				}
				//cardValue = cardValue + suit/10;
				//System.out.println((double)suit/10);

				deck[i] = cardValue + (double)suit/10;

				allCards[suit][defaultCardOrder] = 0;
			}
			
			
		}
		
		
		
		

	}
}
