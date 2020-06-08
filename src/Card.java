import javax.swing.ImageIcon;
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
	//private JLabel cardLabel;
	
	public int posX;
	public int posY;
	
	public Card(int value) {
		super(new ImageIcon("src\\cards\\back1.GIF"));
		this.value = value;
		setPosition(10, 10);
	}
	public Card(int rank, int suit) {
		super(new ImageIcon("src\\cards\\back1.GIF"));
		value = suit * 13 + (rank - 2);
		setPosition(10, 10);
	}
	public Card(int value, int posX, int posY) {
		super(new ImageIcon("src\\cards\\back1.GIF"));
		this.value = value;
		setPosition(posX, posY);
	}
	public Card(int rank, int suit, int posX, int posY) {
		super(new ImageIcon("src\\cards\\back1.GIF"));
		value = suit * 13 + (rank - 2);
		setPosition(posX, posY);
	}
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	public int getRank() {
		return value % 13 + 2;
	}
	public int getSuit() {
		return value / 13;
	}
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
		setIcon(new ImageIcon("src\\cards\\" + toString() + ".GIF"));
	}
	public boolean isFlipped() {
		return flipped;
	}
	public String toString() {
		if (flipped) {
			return Card.ranks[getRank()] + Card.suits[getSuit()];
		}
		return "back1";
	}
	//public JLabel getCardLabel() {
	//	return cardLabel;
	//}
}