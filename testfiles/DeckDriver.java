
public class DeckDriver {

	public static void main(String[] args) {
		
		CardStack deck = new CardStack("deck");
		
		CardStack discardPile = new CardStack();  	//create a discard pile with standard 52 cards

		deck.createStandardDeck();
		
		System.out.println("Printing the 52 card deck created by constructor"); 
		deck.printStack();
		System.out.print("Deck Pile: "); 
		deck.shuffleStack();
		System.out.println("Printing the suffled deck"); 
		deck.printStack();
		System.out.print("Deck Pile: "); 
		

		System.out.println("Printing deck after top card dealt"); 
		System.out.print("Deck Pile: "); 



		System.out.println("Printing deck after top 2 cards dealt"); 
		System.out.print("Deck Pile: "); 

		System.out.println("Printing deck after top 3 cards dealt"); 
		System.out.print("Deck Pile: "); 

		System.out.println("Printing deck after top 4 cards dealt"); 
		System.out.print("Deck Pile: "); 
		
		System.out.println("Printing discard pile after top card dealt to it"); 
		System.out.print("Discard Pile: "); 

		System.out.println("Printing discard pile after top 3 cards dealt to it"); 
		System.out.print("Discard Pile: "); 

		System.out.println("Printing discard pile after top 4 cards dealt to it"); 
		System.out.print("Discard Pile: "); 

		System.out.println("Printing deck 8 cards dealt from top"); 
		System.out.print("Deck Pile: "); 

		
		System.out.println("exit"); 

	}

}
