import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class CardDisplay {
	private JFrame frame;
	private JPanel panel;
	private ArrayList<JLabel> cardLabels;
	public CardDisplay(int x, int y, String name) {
		cardLabels = new ArrayList<JLabel>();
		frame = new JFrame("Demo Frame");
	    panel = new JPanel();
	    frame.getContentPane();
	    panel.setLayout(null);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setSize(x, y);
	    frame.setVisible(true);
	}
	public void addCard(Card card, int x, int y) {
		
		Dimension size = card.getPreferredSize();
	    card.setBounds(x, y, size.width, size.height);
		panel.add(card);
		cardLabels.add(card);
	}
	public static void main(String args[]) {
		CardDisplay test = new CardDisplay(800, 500, "Solitare");
		Deck testDeck = new Deck();
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(10, 1));
		//for (int i = 0; i < 4; i++) {
		//	for (int j = 2; j < 15; j++) {
		//		cards.add(new Card(j, i));
		//	}
		//}
		//Card testCard = new Card(10, 1);
		//Card testCard2 = new Card(11, 1);
		//testCard.setFlipped(true);
		//test.addCard(testCard, 100, 100);
		//test.addCard(testCard2, 200, 100);
		//testDeck.shuffle();
		test.addCard(cards.remove(0), 100, 100);
		System.out.println("Done");
		//test.addCard(testDeck.dealCard(), 0, 10);
		//System.out.println(testDeck.dealCard());
	}
}