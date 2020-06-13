import java.util.ArrayList;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Solitaire extends CardDisplay {
	private int locationPos[][] = {{20, 130}, {120, 130}, {220, 130}, {320, 130}, {420, 130}, {520, 130}, {620, 130}, {320, 20}, {420, 20}, {520, 20}, {620, 20}, {20, 20}, {120, 20}};
	private Column cardColumns[] = new Column[7];
	private Top cardsTop[] = new Top[4];
	private Deck cardDeck = new Deck();
	private ArrayList<Card> leftStack = new ArrayList<Card>();
	private ArrayList<Card> rightStack = new ArrayList<Card>();
	public static final int CARD_WIDTH = 71;
	public static final int CARD_HEIGHT = 96;
	
	private Card currentCard = null;
	
	//Locations: 7 rows: 0-6, 4 top positions: 7-10, 11: left stack, 12: right stack
	public boolean moveCard(Card c, int location) {//Returns true if it allowed to move the card to the specified location
		if (location < 7 && location >= 0) {
			if (c.getRank() == Card.K && cardColumns[location].bottomCards.size() == 0 && cardColumns[location].visibleCards.size() == 0) {
				return true;
			}
			if (cardColumns[location].bottomCards.size() == 0 && cardColumns[location].visibleCards.size() == 0) {
				return false;
			}
			Card topCard = cardColumns[location].visibleCards.get(cardColumns[location].visibleCards.size() - 1);
			if (c.getRank() == topCard.getRank() - 1 || (c.getRank() == Card.A && topCard.getRank() == 2)) {
				System.out.println(topCard.value);
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
			if (c.getRank() == Card.A && cardsTop[location - 7].cardStack.size() == 0) {
				return true;
			}
			if (cardsTop[location - 7].cardStack.size() != 0) {
				Card topCard = cardsTop[location - 7].cardStack.get(cardsTop[location - 7].cardStack.size() - 1);
				return ((c.getRank() == topCard.getRank() + 1 || (c.getRank() == 2 && topCard.getRank() == Card.A)) && c.getSuit() == topCard.getSuit());
			}
		}
		return false;
	}
	public int findCardLocation(Card c) {
		//Check each column of cards
		for (int i = 0; i < cardColumns.length; i++) {
			for (Card cardI: cardColumns[i].bottomCards) {
				if (cardI.equals(c)) {
					return i;
				}
			}
			for (Card cardI: cardColumns[i].visibleCards) {
				if (cardI.equals(c)) {
					return i;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (Card cardI: cardsTop[i].cardStack) {
				if (cardI.equals(c)) {
					return i + 7;
				}
			}
		}
		for (Card cardI: leftStack) {
			if (cardI.equals(c)) {
				return 11;
			}
		}
		for (Card cardI: rightStack) {
			if (cardI.equals(c)) {
				return 12; 
			}
		}
		return -1;
	}
	private class Column {
		public ArrayList<Card> bottomCards = new ArrayList<Card>();
		public ArrayList<Card> visibleCards = new ArrayList<Card>();
	}
	private class Top {
		public ArrayList<Card> cardStack = new ArrayList<Card>();
	}
	public void cardClicked(Card c) {
		int cardLocation = findCardLocation(c);
		System.out.println(moveCard(c, cardLocation));
		
		if (cardLocation == 11) {
			for (int i = 0; i < 3; i++) {
				if (leftStack.size() == 0) {
					break;
				}
				rightStack.add(leftStack.remove(leftStack.size() - 1));
			}
		}
		if (c.isFlipped() && currentCard == null) {
			if (cardLocation != 12) {
				currentCard = c;
				c.setDark(true);
			} else if (c.equals(rightStack.get(rightStack.size() - 1))) {
				currentCard = c;
				c.setDark(true);
			}
		} else if (c.isFlipped() && currentCard != null && moveCard(currentCard, cardLocation)){
			int currentCardLocation = findCardLocation(currentCard);
			if (currentCardLocation >= 7 && currentCardLocation <= 10) {
				cardColumns[cardLocation].visibleCards.add(cardsTop[currentCardLocation - 7].cardStack.remove(cardsTop[currentCardLocation - 7].cardStack.size() - 1));
			} else if (currentCardLocation >= 0 && currentCardLocation < 7) {
				if (cardLocation >= 0 && cardLocation < 7) {
					for (int i = 0; i < cardColumns[currentCardLocation].visibleCards.size(); i++) {
						if (cardColumns[currentCardLocation].visibleCards.get(i).equals(currentCard)) {
							int cardsToMove = cardColumns[currentCardLocation].visibleCards.size();
							for (int j = i; j < cardsToMove; j++) {
								cardColumns[cardLocation].visibleCards.add(cardColumns[currentCardLocation].visibleCards.remove(i));
							}
							break;
						}
					}
				} else if (cardLocation >= 7 && cardLocation < 11) {
					cardsTop[cardLocation - 7].cardStack.add(cardColumns[currentCardLocation].visibleCards.remove(cardColumns[currentCardLocation].visibleCards.size() - 1));
				}
			} else if (currentCardLocation == 12) {
				if (cardLocation >= 0 && cardLocation < 7) {
					cardColumns[cardLocation].visibleCards.add(rightStack.remove(rightStack.size() - 1));
				} else if (cardLocation >= 7 && cardLocation < 11){
					cardsTop[cardLocation - 7].cardStack.add(rightStack.remove(rightStack.size() - 1));
				}
			}
			currentCard.setDark(false);
			currentCard = null;
		} else {
			if (currentCard != null) {
				currentCard.setDark(false);
			}
			currentCard = null;
		}
		displayCards();
	}
	public void locationClicked(int x, int y) {
		int location = -1;
		for (int i = 0; i < 12; i++) {
			if (x > locationPos[i][0] && x < locationPos[i][0] + CARD_WIDTH - 2 && y > locationPos[i][1] && y < locationPos[i][1] + CARD_HEIGHT - 2) {
				location = i;
			}
		}
		if (location == 11) {
			int rightCards = rightStack.size();
			for (int i = 0; i < rightCards; i++) {
				leftStack.add(rightStack.remove(rightStack.size() - 1));
			}
			displayCards();
			return;
		}
		if (currentCard == null) {
			return;
		}
		int currentCardLocation = findCardLocation(currentCard);
		if (!(currentCardLocation >= 7 && currentCardLocation < 11) && location >= 7 && location < 11 && moveCard(currentCard, location)) {
			if (currentCardLocation == 12 && location >= 7 && location < 11) {
				cardsTop[location - 7].cardStack.add(rightStack.remove(rightStack.size() - 1));
			} else {
				cardsTop[location - 7].cardStack.add(cardColumns[currentCardLocation].visibleCards.remove(cardColumns[currentCardLocation].visibleCards.size() - 1));
			}
		}
		if (location >= 0 && location < 7 && moveCard(currentCard, location)) {
			if (currentCardLocation == 12 && location >= 0 && location < 7) {
				cardColumns[location].visibleCards.add(rightStack.remove(rightStack.size() - 1));
			} else if (currentCardLocation >= 7 && currentCardLocation <= 10) {
				cardColumns[location].visibleCards.add(cardsTop[currentCardLocation - 7].cardStack.remove(cardsTop[currentCardLocation - 7].cardStack.size() - 1));
			} else if (currentCardLocation >= 0 && currentCardLocation < 7) {
				if (location >= 0 && location < 7) {
					for (int i = 0; i < cardColumns[currentCardLocation].visibleCards.size(); i++) {
						if (cardColumns[currentCardLocation].visibleCards.get(i).equals(currentCard)) {
							int cardsToMove = cardColumns[currentCardLocation].visibleCards.size();
							for (int j = i; j < cardsToMove; j++) {
								cardColumns[location].visibleCards.add(cardColumns[currentCardLocation].visibleCards.remove(i));
							}
							break;
						}
					}
				} else if (location >= 7 && location < 11) {
					cardsTop[location - 7].cardStack.add(cardColumns[currentCardLocation].visibleCards.remove(cardColumns[currentCardLocation].visibleCards.size() - 1));
				}
			}
		}
		currentCard.setDark(false);
		currentCard = null;
		displayCards();
	}
	public void drawGraphics(JPanel panel) {
		JLabel place1 = new JLabel(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("cards/CardStruggle.gif")));
		JLabel place2 = new JLabel(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("cards/CardStruggle.gif")));
		JLabel place3 = new JLabel(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("cards/CardStruggle.gif")));
		JLabel place4 = new JLabel(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("cards/CardStruggle.gif")));
		Dimension size = place1.getPreferredSize();
		place1.setBounds(locationPos[7][0], locationPos[7][1], size.width, size.height);
		place2.setBounds(locationPos[8][0], locationPos[8][1], size.width, size.height);
		place3.setBounds(locationPos[9][0], locationPos[9][1], size.width, size.height);
		place4.setBounds(locationPos[10][0], locationPos[10][1], size.width, size.height);
		panel.add(place1);
		panel.add(place2);
		panel.add(place3);
		panel.add(place4);
	}
	public void displayCards() {
		cards = new ArrayList<Card>();
		for (int i = 0; i < 7; i++) {
			if (cardColumns[i].visibleCards.size() == 0 && cardColumns[i].bottomCards.size() != 0) {
				cardColumns[i].visibleCards.add(cardColumns[i].bottomCards.remove(cardColumns[i].bottomCards.size() - 1));
			}
			for (int j = 0; j < cardColumns[i].bottomCards.size(); j++) {
				cardColumns[i].bottomCards.get(j).setPosition(locationPos[i][0], locationPos[i][1] + j * 5);
				cardColumns[i].bottomCards.get(j).setFlipped(false);
				cards.add(cardColumns[i].bottomCards.get(j));
			}
			for (int j = 0; j < cardColumns[i].visibleCards.size(); j++) {
				cardColumns[i].visibleCards.get(j).setPosition(locationPos[i][0], locationPos[i][1] + j * 20 + cardColumns[i].bottomCards.size() * 5);
				cardColumns[i].visibleCards.get(j).setFlipped(true);
				cards.add(cardColumns[i].visibleCards.get(j));
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < cardsTop[i].cardStack.size(); j++) {
				cardsTop[i].cardStack.get(j).setPosition(locationPos[i + 7][0], locationPos[i + 7][1]);
				cardsTop[i].cardStack.get(j).setFlipped(true);
				cards.add(cardsTop[i].cardStack.get(j));
			}
		}
		for (int i = 0; i < leftStack.size(); i++) {
			leftStack.get(i).setFlipped(false);
			leftStack.get(i).setPosition(locationPos[11][0], locationPos[11][1]);
			cards.add(leftStack.get(i));
		}
		for (int i = 0; i < rightStack.size(); i++) {
			if (rightStack.size() - i > 2) {
				rightStack.get(i).setFlipped(true);
				rightStack.get(i).setPosition(locationPos[12][0], locationPos[12][1]);
				cards.add(rightStack.get(i));
			} else {
				rightStack.get(i).setFlipped(true);
				if (rightStack.size() - i == 2) {
					rightStack.get(i).setPosition(locationPos[12][0] + 12, locationPos[12][1]);
				} else {
					rightStack.get(i).setPosition(locationPos[12][0] + 24, locationPos[12][1]);
				}
				cards.add(rightStack.get(i));
			}
		}
		update();
	}
	public Solitaire() {
		super(800, 500, "Solitaire");
		cardDeck.shuffle();
		for (int i = 0; i < 7; i++) {
			cardColumns[i] = new Column();
		}
		for (int i = 0; i < 4; i++) {
			cardsTop[i] = new Top();
		}
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if (i == j) {
					cardColumns[j].visibleCards.add(cardDeck.dealCard());
				}
				if (i < j) {
					cardColumns[j].bottomCards.add(cardDeck.dealCard());
				}
			}
		}
		while (cardDeck.deckSize() > 0) {
			leftStack.add(cardDeck.dealCard());
		}
		displayCards();
	}
	
	public static void main(String args[]) {
		Solitaire test = new Solitaire();
		
	}

}
