package blackJack;

public class Display {
	public static double dealerCard[];
	public static double playerCard[];
	public static boolean over = false;
	public static int numPlayerCard = 0;
	public static int numDealerCard = 0;


	
	public void setDealer(CardsInHand dealer)
	{
		dealerCard = dealer.getCards();
		numDealerCard = dealer.getNumCard();
		display();
		
	}
	
	public void setPlayer(CardsInHand player)
	{
		playerCard = player.getCards();
		numPlayerCard = player.getNumCard();
		display();
	}
	
	public void display()
	{
		
		
			
			System.out.print("Dealer: ");
	
			for(int i =0; i<numDealerCard; i++)
			{
				if(dealerCard[i] != -1)
				{
				String type = getTypeCard(dealerCard[i]);
				
				System.out.print(type +" "+(int)dealerCard[i] +"   ");
				}
				else
				{
					System.out.print("X   ");
				}
			}
			
			System.out.println();
			
		
			System.out.print("Player: ");
			for(int i =0; i<numPlayerCard; i++)
			{
			String type = getTypeCard(playerCard[i]);
			
			System.out.print(type +" "+(int)playerCard[i] +"   ");
			
			}
			
		
		if(over == true)
		{
			System.out.println("game over");

		}
		System.out.println();
		System.out.println();

		
	}
	
	public void setOver(boolean isOver)
	{
		over = isOver;
	}
	
	public static String getTypeCard(double rawValue)
	{
		//System.out.println(rawValue);
		
		int suitValue = (int) (Math.round((rawValue - (int)(rawValue))*10));
		//System.out.println(suitValue);

		String type = "";
		switch(suitValue)
		{
			case(0):
			{
				type = "Diamond";
				break;
			}
			case(1):
			{
				type = "Club";
				break;
			}
			case(2):
			{
				type = "Heart";
				break;
			}
			case(3):
			{
				type = "Spade";	
				break;
			}
		}
		return type;
		
		
	}
}
