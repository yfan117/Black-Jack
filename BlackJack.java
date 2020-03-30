package blackJack;

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
	static int dealerX = 750;
	static int dealerY = 250;
	static int playerX = 600;
	static int playerY = 660;
	static int dealerNumCard =0;
	static int playerNumCard =0;
	
	static double[] dealerCards;
	static double[] playerCards;
	
	static JButton hit;
	static JButton stand;
	
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
	
	public void displayButton()
	{
		hit = new JButton(new ImageIcon("res/hitButton.png"));
		hit.setBounds(1100, 480, 200, 100);
		add(hit);
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
				if((int)dealerCards[i] == 10)
				{
					g.drawString(value, dealerX + 35, dealerY + 130);
				}
				else
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
				if((int)playerCards[i] == 10)
				{
					g.drawString(value, playerX + 35, playerY + 130);
				}
				else
				g.drawString(value, playerX + 68, playerY + 130);
				playerX = playerX + 50;
				playerY = playerY - 50;
				value = "";
			}
			
		
			
			dealerX = 750;
			dealerY = 250;
			playerX = 600;
			playerY = 660;
			
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
	
	static Draw update ;

	public Display ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1392, 851);
		setResizable(false);

		update = new Draw();
		getContentPane().add(update);
		
		setVisible(true);		

	}
	
	public void updateDisplay()
	{
		update.setVariable(dealerCard, playerCard, numDealerCard, numPlayerCard);

	}

	public void displayButton()
	{
		update.displayButton();
	}
	
	public void setDealer(CardsInHand dealer)
	{
		dealerCard = dealer.getCards();
		numDealerCard = dealer.getNumCard();
		update.setVariable(dealerCard, playerCard, numDealerCard, numPlayerCard);

		//display();
		
	}
	
	public void setPlayer(CardsInHand player)
	{
		playerCard = player.getCards();
		numPlayerCard = player.getNumCard();
		update.setVariable(dealerCard, playerCard, numDealerCard, numPlayerCard);
		
		//display();
	}
	

	public void setOver(boolean isOver)
	{
		over = isOver;
	}
	
	
}
