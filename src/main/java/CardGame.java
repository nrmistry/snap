import java.util.ArrayList;
import java.util.Collections;

class CardGame {
    private ArrayList<Card> deck;
    private String name;

    public CardGame(String name) {
        this.name = name;
        this.deck = initialiseDeck();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    private ArrayList<Card> initialiseDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = {"\u2666", "\u2665", "\u2663", "\u2660"};
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (int i = 0; i < symbols.length; i++) {
                int value = i + 2;
                Card card = new Card(suit, symbols[i], value);
                newDeck.add(card);
            }
        }

        return newDeck;
    }

    public void printDeck() {
        System.out.println("Deck for " + name + ":");
        for (Card card : deck) {
            System.out.println(card.toString());
        }
    }

    public Card dealCard() {
        if (deck.isEmpty()) {
            return null;
        }

        Card dealtCard = deck.remove(0);
        System.out.println("Dealt card: " + dealtCard.toString());
        return dealtCard;
    }

    public void sortDeckInNumberOrder() {
        Collections.sort(deck, (card1, card2) -> Integer.compare(card1.getValue(), card2.getValue()));
        System.out.println("Deck sorted in number order.");
    }

    public void sortDeckIntoSuits() {
        Collections.sort(deck, (card1, card2) -> {
            int suitComparison = card1.getSuit().compareTo(card2.getSuit());
            if (suitComparison == 0) {
                return Integer.compare(card1.getValue(), card2.getValue());
            }
            return suitComparison;
        });
        System.out.println("Deck sorted into suits.");
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
        System.out.println("Deck shuffled.");
    }

}

