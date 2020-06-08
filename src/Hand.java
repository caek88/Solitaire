import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card newCard) {
		cards.add(newCard);
	}
}
