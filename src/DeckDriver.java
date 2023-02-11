
public class DeckDriver {

	public static void main(String[] args) {
		
		Deck cardDeck = new Deck();
		//Card ace = new Card(0, 0);
		
		Deck discardPile = new Deck();  //create a disccard pile with standard 52 cards
		discardPile.emptyDeck();		//empty the deck to create a discard pile (these could be player hands etc...)
		
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck(); 		//print fresh deck	
		cardDeck.shuffleDeck();		//shuffle that deck
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck();		//print shuffled deck
		
		cardDeck.dealTopCard();		//deal top card 
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck();		//show top card dealt

		cardDeck.dealTopCard(2);	//deal top 2 cards
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck();		//show top cards dealt

		cardDeck.dealTopCard(3);	//deal top 3 cards
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck();		//show top 3 cards dealt

		cardDeck.dealTopCard(4);	//deal top 4 card
		System.out.print("Deck Pile: "); 
		cardDeck.printDeck();		//show top 4 cards dealt
		
		discardPile.addCard(cardDeck.dealTopCard());
		System.out.print("Discard Pile: "); 
		discardPile.printDeck();

		discardPile.addCard(cardDeck.dealTopCard(3));
		System.out.print("Discard Pile: "); 
		discardPile.printDeck();

		discardPile.addCard(cardDeck.dealTopCard(4));
		System.out.print("Discard Pile: "); 
		discardPile.printDeck();

		
		System.out.println("exit"); 

	}

}
