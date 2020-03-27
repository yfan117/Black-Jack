package blackJack;

import java.util.Scanner;

public class CardsInHand {
	
	//private int handScore = 0;
	private int scoreAce_1 = 0;
	private int scoreAce_11 = 0;

	private double hiddenHand = 0;
	private boolean dealerBlackJack = false;
	private boolean playerBlackJack = false;

	private double cardInHand[] = new double[14];
	private int cardCounter = 0;
	private boolean isDealer = false;
	private 
	
	Scanner input = new Scanner(System.in);
	//private static double hiddenCard;
	//private int numCard = 0;
	
	public void setHidden(double faceValue)
	{
		hiddenHand = faceValue;
		cardInHand[0] = -1;
		//handScore ++;
		cardCounter++;
		isDealer = true;
	}
	
	public void revealHidden()
	{
		cardInHand[0] = hiddenHand;
		
		int faceValue = (int)hiddenHand;

		if(faceValue == 1) 
		{
			scoreAce_1 ++;
			
			if((scoreAce_11 + 11) <= 21)
			{
				scoreAce_11 = scoreAce_11 + 11;
			}
			else
			{
				scoreAce_11 ++;
			}
		}
		else if (faceValue >= 10)
		{
			scoreAce_1 = scoreAce_1 + 10;
			scoreAce_11 = scoreAce_11 + 10;
		}
		else
		{
			scoreAce_1 = scoreAce_1 + faceValue;
			scoreAce_11 = scoreAce_11 + faceValue;
		}
		
	
	}
	
	public void addHand(double rawValue)
	{
		
		cardInHand[cardCounter] = rawValue;
		cardCounter++;
		
		int faceValue = (int)rawValue;
		int hiddenFaceValue = (int)hiddenHand;
		
		if(isDealer == true)
		{
			if((faceValue == 1) && (hiddenFaceValue >= 10))
			{
				//check for ace
				dealerBlackJack = true;
			}
			
			if(faceValue >= 10)
			{
				//ask for insurance
				if(hiddenFaceValue == 1)
				{
					dealerBlackJack = true;
					//pay insurance
				}
				else
				{
					//take insurance
				}
			}
		}
		
		
			
			//handScore = handScore + faceValue;
			
			if(faceValue == 1) 
			{
				scoreAce_1 ++;
				
				if((scoreAce_11 + 11) <= 21)
				{
					scoreAce_11 = scoreAce_11 + 11;
				}
				else
				{
					scoreAce_11 ++;
				}
			}
			else if (faceValue >= 10)
			{
				scoreAce_1 = scoreAce_1 + 10;
				scoreAce_11 = scoreAce_11 + 10;
			}
			else
			{
				scoreAce_1 = scoreAce_1 + faceValue;
				scoreAce_11 = scoreAce_11 + faceValue;
			}
			
			//if( handScore >)
			
			
			if((isDealer == false) && (cardCounter == 2))
			{
				//System.out.println("here");
				if((scoreAce_1 == 21) || (scoreAce_11 == 21))
				{
					//System.out.println("here");
					playerBlackJack = true;
				}
			}
		
		
	}
	
	public int getNumCard()
	{
		return cardCounter;
	}
	
	
	public double getHidden()
	{
		return hiddenHand;
	}
	
	public double[] getCards()
	{
		return cardInHand;
	}
	
	public boolean getDealerBlackJack()
	{
		return dealerBlackJack;
	}
	
	public boolean getPlayerBlackJack()
	{
		return playerBlackJack;
	}
	
	public int getScore_1()
	{
		return scoreAce_1;
	}
	
	public int getScore_11()
	{
		return scoreAce_11;
	}



}
