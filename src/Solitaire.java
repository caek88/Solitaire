import java.awt.BorderLayout;
public class Solitaire extends CardDisplay {
	public void cardClicked(Card c) {
		System.out.println(c.getRank());
		System.out.println(c.getSuit());
		c.setDark(!c.getDark());
		update();
	}
	public Solitaire() {
		super(800, 500, "Solitaire");
	}
	public static void main(String args[]) {
		Solitaire test = new Solitaire();
		Deck testDeck = new Deck();
		testDeck.shuffle();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card cardI = testDeck.dealCard();
				cardI.setPosition(i * 100, j * 25);
				cardI.setFlipped(true);
				test.addCard(cardI);
			}
		}
		test.update();
	}

}
