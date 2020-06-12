import java.util.ArrayList;

public class Solitaire extends CardDisplay {
	private int columnXPos[] = {20, 120, 220, 320, 420, 520, 620};
	private int topPos[] = {320, 420, 520, 620};
	private Column cardColumns[] = new Column[7];
	private Top cardsTop[] = new Top[4];
	private Deck cardDeck = new Deck();
	
	//Locations: 7 rows: 0-6, 4 top positions: 7-10
	public boolean moveCard(Card c, int location) {//Returns true if it allowed to move the card to the specified location
		if (location < 7 && location > 0) {
			Card topCard = cardColumns[location].visibleCards.get(cardColumns[location].visibleCards.size() - 1);
			if (c.getRank() == topCard.getRank() - 1) {
				if (c.getSuit() == Card.CLUBS || c.getSuit() == Card.SPADES) {
					if (topCard.getSuit() == Card.HEARTS || topCard.getSuit() == Card.DIAMONDS) {
						return true;
					} else {
						return false;
					}
				}
				if (c.getSuit() == Card.HEARTS || c.getSuit() == Card.DIAMONDS) {
					if (topCard.getSuit() == Card.CLUBS || topCard.getSuit() == Card.SPADES) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		if (location >= 7 && location < 11) {
			Card topCard = cardColumns[location].visibleCards.get(cardColumns[location].visibleCards.size() - 1);
			return (c.getRank() == topCard.getRank() - 1 && c.getSuit() == topCard.getSuit());
		}
		return false;
	}
	private class Column {
		public ArrayList<Card> bottomCards = new ArrayList<Card>();
		public ArrayList<Card> visibleCards = new ArrayList<Card>();
	}
	private class Top {
		public ArrayList<Card> cardStack = new ArrayList<Card>();
	}
	public void cardClicked(Card c) {
		System.out.println(c.getRank());
		System.out.println(c.getSuit());
		c.setDark(!c.getDark());
		update();
	}
	public Solitaire() {
		super(800, 500, "Solitaire");
		cardDeck.shuffle();
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
