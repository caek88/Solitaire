//-------------------------------------------//
//              CardDisplay.java             //
//              Author: C Ekeman             //
//                 6-13-2020                 //
//    Defines the Card display class which   //
//handles the GUI and defines methods for the//
//           Solitaire class to use          //
//-------------------------------------------//

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class CardDisplay{
	private JFrame frame;
	private JPanel panel;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public CardDisplay(int x, int y, String name) {//Sets up display and initializes all instance variables
		frame = new JFrame(name);
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
		drawGraphics(panel);
		frame.revalidate();
		frame.repaint();
	}
	
	public abstract void cardClicked(Card c);//Method for card games need to implement to handle when cards are clicked
	public abstract void locationClicked(int x, int y);
	public abstract void drawGraphics(JPanel panel);
	//Implement mouse handling
	private class CardListener implements MouseListener{
		public void mousePressed(MouseEvent e) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			for (int i = cards.size() - 1; i >= 0; i--) {
				Point cardOrigin = cards.get(i).getOrigin();
				Dimension cardSize = cards.get(i).getSize();
				if (mouseX > cardOrigin.getX() && mouseX < cardOrigin.getX() + cardSize.width) {
					if (mouseY > cardOrigin.getY() && mouseY < cardOrigin.getY() + cardSize.height) {
						cardClicked(cards.get(i));
						return;
					}
				}
			}
			locationClicked(mouseX, mouseY);
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}