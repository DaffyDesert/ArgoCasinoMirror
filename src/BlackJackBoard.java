import javax.swing.JPanel;

public class BlackJackBoard extends Board{

	JPanel mainPanel;
	
	public BlackJackBoard() {
		
		super(2, 0, 6);
		
		getPlayers().get(0).setStackName("Dealer Hand");
		getPlayers().get(1).setStackName("Player Hand");
	}
	
	public CardStack playerHand() {
		return getPlayers().get(1);
	}
	
	public CardStack dealerHand() {
		return getPlayers().get(0);
	}
	
	public void setBoard() {
		getDeck().shuffleStack();
		setCardValues();
		
		playerHand().addToTop(getDeck().dealTopCard()); 
		playerHand().flipTopCard();
		
		dealerHand().addToTop(getDeck().dealTopCard()); 
		dealerHand().flipTopCard();
		
		playerHand().addToTop(getDeck().dealTopCard()); 
		playerHand().flipTopCard();
		
		dealerHand().addToTop(getDeck().dealTopCard()); 

	}
	
	public void setCardValues() {
		
		for (int i = 0; i < getDeck().getStackSize(); i++) {
			
			char faceValue = getDeck().peekCard(i).getValue().charAt(0);
			int numericValue;
			
			if(faceValue == 'J')
				numericValue = 10;
			else if(faceValue == 'Q')
				numericValue = 10;
			else if(faceValue == 'K')
				numericValue = 10;
			else if(faceValue == 'A')
				numericValue = 11;
			else if(faceValue == '1') //cover the 10 card
				numericValue = 10;
			
			else
				numericValue = Character.getNumericValue(getDeck().peekCard(i).getValue().charAt(0));
			
			getDeck().peekCard(i).setRank(numericValue);
		}
	}
	
	public boolean is21(CardStack hand) {
		if(handTotal(hand) == 21) 
			return true;
		else
			return false;
	}
	
	public boolean isBust(CardStack hand) {
		if(handTotal(hand) > 21)
			return true;
		else
			return false;
	}
	
	public int handTotal(CardStack hand) {
		int handTotal = 0;
		for (int i = 0; i < hand.getStackSize(); i++)
			handTotal += hand.peekCard(i).getRank();
		
		if (handTotal > 21) {
			for(int i = 0; i < acesInHand(hand); i++) {
				handTotal -= 10;
				if(handTotal == 21)
					return handTotal;
				else if(handTotal < 21)
					return handTotal;
			}
		}	
		return handTotal;
	}
	
	public int acesInHand(CardStack hand) {
		
		int acesInHand = 0;
		for(int i = 0; i < hand.getStackSize(); i++)
			if (hand.peekCard(i).getValue().charAt(0) == 'A')
				acesInHand++;
		return acesInHand;
	}

	public boolean hit(CardStack hand) {
		hand.addToTop(getDeck().dealTopCard());
		hand.flipTopCard();
		
		if(is21(hand) || isBust(hand))
			return false;
		else
			return true;
	}
	
	public void stay() {
		startDealerTurn();
	}

	public void startDealerTurn() {
		while(handTotal(dealerHand()) <= 16) {
			hit(dealerHand());		
			System.out.println(handTotal(dealerHand()));
		}
	}
	
	
}
