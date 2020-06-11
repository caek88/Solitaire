import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class CardDisplay{
	private JFrame frame;
	private JPanel panel;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public CardDisplay(int x, int y, String name) {//Sets up display and initializes all instance variables
		frame = new JFrame("Demo Frame");
	    panel = new JPanel();
	    frame.getContentPane();
	    panel.setLayout(null);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setSize(x, y);
	    frame.setVisible(true);
	    CardListener listenCard = new CardListener();
	    panel.addMouseListener(listenCard);
	}
	public void addCard(Card card) {//Adds a card to the list of cards to be displayed and sets bounds of the card image
		cards.add(card);
	}
	public void setCard(int value, int xPos, int yPos, boolean isFlipped) {//Sets the properties of a card of a specified value to a new position/flipped state
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isValue(value)) {
				cards.get(i).setFlipped(isFlipped);
				cards.get(i).setPosition(xPos,  yPos);
			}
		}
	}
	public void setCard(int value, int xPos, int yPos, boolean isFlipped, boolean dark) {//Sets the properties of a card of a specified value to a new position/flipped/dark state
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isValue(value)) {
				cards.get(i).setFlipped(isFlipped);
				cards.get(i).setPosition(xPos,  yPos);
				cards.get(i).setDark(dark);
			}
		}
	}
	public void setCard(int value, boolean isFlipped) {//Sets the properties of a card of a specified value to a new flipped state
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isValue(value)) {
				cards.get(i).setFlipped(isFlipped);
			}
		}
	}
	public void setCard(int value, boolean isFlipped, boolean dark) {//Sets the properties of a card of a specified value to a new flipped/dark state
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isValue(value)) {
				cards.get(i).setFlipped(isFlipped);
				cards.get(i).setDark(dark);
			}
		}
	}
	public void update() {//Reprints all cards on the screen
		panel.removeAll();
		for (int i = cards.size() - 1; i >= 0; i--) {
			panel.add(cards.get(i));
		}
		frame.revalidate();
		frame.repaint();
	}
	
	public abstract void cardClicked(Card c);//Method for card games need to implement to handle when cards are clicked
	//Implement mouse handling
	private class CardListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}