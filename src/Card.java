import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.*;

public class Card extends JLabel{
	public static final int J = 11;
	public static final int Q = 12;
	public static final int K = 13;
	public static final int A = 14;
	
	public static final int CLUBS = 0;
	public static final int DIAMONDS = 1;
	public static final int HEARTS = 2;
	public static final int SPADES = 3;
	public static final String ranks[] = {null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
	public static final String suits[] = {"clubs", "diamonds", "hearts", "spades"};
	
	private int value;
	private boolean flipped = false;
	private boolean isDark = false;
	//private JLabel cardLabel;
	
	public int posX;
	public int posY;
	
	public Card(int value) {//Creates a card object with the specified value
		super(new ImageIcon("src\\cards\\back1.GIF"));
		this.value = value;
		setPosition(10, 10);
		Dimension size = getPreferredSize();
	    setBounds(posX, posY, size.width, size.height);
	}
	public Card(int rank, int suit) {//Creates a card Object with the specified rank and suit
		super(new ImageIcon("src\\cards\\back1.GIF"));
		value = suit * 13 + (rank - 2);
		setPosition(10, 10);
		Dimension size = getPreferredSize();
	    setBounds(posX, posY, size.width, size.height);
	}
	public Card(int value, int posX, int posY) {//Creates a card object with the specified value and position
		super(new ImageIcon("src\\cards\\back1.GIF"));
		this.value = value;
		setPosition(posX, posY);
		Dimension size = getPreferredSize();
	    setBounds(posX, posY, size.width, size.height);
	}
	public Card(int rank, int suit, int posX, int posY) {//Creates a card object with the specified rank, suit, and location
		super(new ImageIcon("src\\cards\\back1.GIF"));
		value = suit * 13 + (rank - 2);
		setPosition(posX, posY);
		Dimension size = getPreferredSize();
	    setBounds(posX, posY, size.width, size.height);
	}
	public void setPosition(int posX, int posY) {//Sets the card's position
		this.posX = posX;
		this.posY = posY;
	}
	public int getRank() {//Returns the card's rank
		return value % 13 + 2;
	}
	public int getSuit() {//Returns the card's suit
		return value / 13;
	}
	public void setFlipped(boolean flipped) {//Sets whether the card is flipped
		this.flipped = flipped;
		setIcon(new ImageIcon("src\\cards\\" + toString() + ".GIF"));
	}
	public void setDark(boolean dark) {//Sets the to light/dark
		isDark = dark;
		setIcon(new ImageIcon("src\\cards\\" + toString() + ".GIF"));
	}
	public boolean isFlipped() {//Returns whether or not the card's face is showing
		return flipped;
	}
	public boolean isValue(int value) {//Returns whether this card is the given value
		return this.value== value;
	}
	public static int getValue(int rank, int suit) {//Static method that returns the value of the card for a specified rank and suit
		return suit * 13 + (rank - 2);
	}
	public String toString() {//Returns the image name of the card based on the card's settings
		if (flipped) {
			return Card.ranks[getRank()] + Card.suits[getSuit()] + (isDark?"S":"");
		}
		return "back1";
	}
	//public JLabel getCardLabel() {
	//	return cardLabel;
	//}
}