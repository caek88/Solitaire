import java.awt.BorderLayout;
public class Solitaire extends CardDisplay {

	public static void main(String args[]) {
		CardDisplay test = new CardDisplay(800, 500, "Solitare");
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
		test.setCard(Card.getValue(10, Card.CLUBS), true, true);
		test.update();
	}

}
