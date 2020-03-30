package blackJack;

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;

import java.awt.*;
import javax.swing.*;

class Draw extends JPanel
{
	static Image cardImage;
	static Image backGround;
	static ImageIcon imageIcon;
	

	static boolean isDealer = false;
	//static String cardType = "";
	static int faceValue = 0;
	static int dealerX = 900;
	static int dealerY = 250;
	static int playerX = 600;
	static int playerY = 660;
	static int dealerNumCard =0;
	static int playerNumCard =0;
	
	static double[] dealerCards;
	static double[] playerCards;
	
	
	
	public Draw()
	{
		imageIcon = new ImageIcon("res/blackJackTable.png");
		backGround = imageIcon.getImage();
		//repaint();
		//paint(null);
	}
	
	public void setVariable(double dealerCard[], double playerCards[], int dealerNum, int playNum)
	{
		//this.isDealer = isDealer;
		this.dealerCards = dealerCard;
		this.playerCards = playerCards;
		dealerNumCard = dealerNum;
		playerNumCard = playNum;
		
		repaint();
	}
	
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(backGround, 0, 0, null);
			g.setFont(new Font("serif", Font.BOLD, 40));
			String type = "";
			String value = "";

			for(int i =0; i< dealerNumCard; i++)
			{
				if(dealerCards[i] != -1)
				{
					type = "res/" +getType(dealerCards[i]);
					
					value = getValue((int)dealerCards[i]);
				}
				else
				{
					type = "res/backCard.png";
				}
				
				cardImage = new ImageIcon(type).getImage();
				
				g.drawImage(cardImage, dealerX, dealerY, null);
				g.drawString(value, dealerX + 5, dealerY + 40);
				g.drawString(value, dealerX + 68, dealerY + 130);
				dealerX = dealerX - 100;
				value = "";
			}
			
			for(int i =0; i< playerNumCard; i++)
			{
				
				type = "res/" +getType(playerCards[i]);
				value = getValue((int)playerCards[i]);
				
				cardImage = new ImageIcon(type).getImage();
				g.drawImage(cardImage, playerX, playerY, null);
				g.drawString(value, playerX + 5, playerY + 40);
				g.drawString(value, playerX + 68, playerY + 130);
				playerX = playerX + 50;
				playerY = playerY - 50;
				value = "";
			}
			
			
		}
		
		public static String getValue(int rawValue)
		{
			String value = "";
			
			if(rawValue == 1) {
				value = "A";
			}
			else if(rawValue == 11)
			{
				value = "J";
			}
			else if(rawValue == 12)
			{
				value = "Q";
			}
			else if(rawValue == 13)
			{
				value = "K";
			}
			else
			{
				value = String.valueOf(rawValue);
			}
			
			
			return value;
		}
		public static String getType(double rawValue)
		{
			//System.out.println(rawValue);
			
			int suitValue = (int) (Math.round((rawValue - (int)(rawValue))*10));
			//System.out.println(suitValue);

			String type = "";
			switch(suitValue)
			{
				case(0):
				{
					type = "diamond.png";
					break;
				}
				case(1):
				{
					type = "club.png";
					break;
				}
				case(2):
				{
					type = "heart.png";
					break;
				}
				case(3):
				{
					type = "spade.png";	
					break;
				}
			}
			return type;
			
			
		}
		
		}
	



public class Display extends JFrame{
	public static double dealerCard[];
	public static double playerCard[];
	public static boolean over = false;
	public static int numPlayerCard = 0;
	public static int numDealerCard = 0;
	
	
	
	//static BufferedImage cardImage;
	
	//static JFrame frame;
	static JLabel label;
	static JLayeredPane layer;
	static Draw update ;
	public void display()
	{
		
		
			System.out.print("Dealer: ");
	
			
				//String type = getTypeCard(dealerCard[i]);
				//repaint();
				
				update.setVariable(dealerCard, playerCard, numDealerCard, numPlayerCard);
				/*
				System.out.print(type +" "+(int)dealerCard[i] +"   ");
				*/
			
				
		
				/*
			String type = getTypeCard(playerCard[i]);
			System.out.print(type +" "+(int)playerCard[i] +"   ");
			*/
			
			
		
		if(over == true)
		{
			System.out.println("game over");

		}
		System.out.println();
		System.out.println();

		setVisible(true);
	}
	public Display ()
	{
		//frame = new JFrame("Black Jack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1392, 851);
		setResizable(false);
		
		//display();
		update = new Draw();
		getContentPane().add(update);
		
		setVisible(true);
		
		
		
	}

	
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
	
	

	
	
	
	
	
	//--------------------------------------------------------------------
	
	
	
	
	
	public void setOver(boolean isOver)
	{
		over = isOver;
	}
	
	
}
