import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class CardDisplay {
	private JFrame frame;
	public JPanel panel;
	public CardDisplay(int x, int y, String name) {
		frame = new JFrame("Demo Frame");
	    panel = new JPanel();
	    frame.getContentPane();
	    panel.setLayout(null);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setSize(x, y);
	    frame.setVisible(true);
	}
	public void addCard(Card card) {
		Dimension size = card.getPreferredSize();
	    card.setBounds(card.posX, card.posY, size.width, size.height);
		panel.add(card);
	}
	public void update() {
		frame.repaint();
	}
	public void setCardZValue(int cardIndex, int zOrder) {
		panel.setComponentZOrder(panel.getComponent(cardIndex), zOrder);
	}
	public static void main(String args[]) {
		CardDisplay test = new CardDisplay(800, 500, "Solitare");
		Card card1 = new Card(10, Card.CLUBS);
		Card card2 = new Card(2, Card.DIAMONDS, 25, 25);
		card1.setFlipped(true);//10C
		card2.setFlipped(true);//2D
		test.addCard(card1);
		test.addCard(card2);
		//test.setCardZValue(0, 0);
		//test.setCardZValue(1, 1);
		System.out.println(test.panel.getComponentCount());
		test.panel.getComponent(0).setFlipped(true);
		test.panel.setComponentZOrder(test.panel.getComponent(0), 0);
		test.panel.setComponentZOrder(test.panel.getComponent(1), 1);
		//Deck testDeck = new Deck();
		//testDeck.shuffle();
		/*for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card cardI = testDeck.dealCard();
				cardI.setPosition(i * 100, (13 - j) * 25);
				cardI.setFlipped(true);
				test.addCard(cardI);
			}
		}
		for (int i = 0; i < 49; i++) {
			test.setCardZValue(i, 50 - i);
		}*/
		test.update();
	}
}