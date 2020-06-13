import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				cards.add(new Card(j, i));
			}
		}
	}
	public void shuffle() {
		ArrayList<Card> cardHold = new ArrayList<Card>();
		for (Card i : cards) {
			cardHold.add(i);
		}
		for (int i = 0; i < cards.size(); i++) {
			cards.set(i, cardHold.remove((int)(Math.random() * cardHold.size())));
		}
	}
	public Card dealCard() {
		return cards.remove(0);
	}
	public int deckSize() {
		return cards.size();
	}
}
