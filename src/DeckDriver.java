
public class DeckDriver {

	public static void main(String[] args) {
		
		CardStack cardDeck = new Deck("deck");
		//Card ace = new Card(0, 0);
		
		CardStack discardPile = new CardStack();  	//create a discard pile with standard 52 cards
		//discardPile.emptyStack();					//empty the deck to create a discard pile (these could be player hands etc...)
		
		System.out.println("Printing the 52 card deck created by constructor"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack(); 						//print fresh deck	
		cardDeck.shuffleStack();					//shuffle that deck
		System.out.println("Printing the suffled deck"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();						//print shuffled deck
		
		cardDeck.dealTopCard();						//deal top card 
		System.out.println("Printing deck after top card dealt"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();						//show top card dealt

		cardDeck.dealTopCard(2);					//deal top 2 cards
		System.out.println("Printing deck after top 2 cards dealt"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();						//show top cards dealt

		cardDeck.dealTopCard(3);					//deal top 3 cards
		System.out.println("Printing deck after top 3 cards dealt"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();						//show top 3 cards dealt

		cardDeck.dealTopCard(4);					//deal top 4 card
		System.out.println("Printing deck after top 4 cards dealt"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();						//show top 4 cards dealt
		
		discardPile.addToTop(cardDeck.dealTopCard());
		System.out.println("Printing discard pile after top card dealt to it"); 
		System.out.print("Discard Pile: "); 
		discardPile.printStack();

		discardPile.addToTop(cardDeck.dealTopCard(3));
		System.out.println("Printing discard pile after top 3 cards dealt to it"); 
		System.out.print("Discard Pile: "); 
		discardPile.printStack();

		discardPile.addToTop(cardDeck.dealTopCard(4));
		System.out.println("Printing discard pile after top 4 cards dealt to it"); 
		System.out.print("Discard Pile: "); 
		discardPile.printStack();
		System.out.println("Printing deck 8 cards dealt from top"); 
		System.out.print("Deck Pile: "); 
		cardDeck.printStack();

		
		System.out.println("exit"); 

	}

}
