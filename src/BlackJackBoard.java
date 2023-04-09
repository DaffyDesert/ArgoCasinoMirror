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
		setCardPointValues();
		
		playerHand().addToTop(getDeck().dealTopCard()); 
		playerHand().flipTopCard();
		
		dealerHand().addToTop(getDeck().dealTopCard()); 
		dealerHand().flipTopCard();
		
		playerHand().addToTop(getDeck().dealTopCard()); 
		playerHand().flipTopCard();
		
		dealerHand().addToTop(getDeck().dealTopCard()); 

	}
	
	public void setCardPointValues() {
		
		for (int i = 0; i < getDeck().getStackSize(); i++) {
			
			int defaultRank = getDeck().peekCard(i).getRank();
			int pointValue;
			
			if(defaultRank == 11)//jack
				pointValue = 10;
			else if(defaultRank == 12)//queen
				pointValue = 10;
			else if(defaultRank == 13)//king
				pointValue = 10;
			else if(defaultRank == 1)//ace
				pointValue = 11;
			else
				pointValue = defaultRank;
			
			getDeck().peekCard(i).setPointValue(pointValue);
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
		for (int i = 0; i < hand.getStackSize(); i++) {
			handTotal += hand.peekCard(i).getPointValue();
		}
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
			if (hand.peekCard(i).toString().charAt(0) == 'A')
				acesInHand++;
		return acesInHand;
	}

	public boolean hit(CardStack hand) {
		hand.addToTop(getDeck().dealTopCard());
		hand.flipTopCard();
		
		if(is21(hand) || isBust(hand) || dealerOver16AndNoBust())
			return false;
		else
			return true;
	}
	
	public void playerStay() {
		startDealerTurn();
	}

	public void startDealerTurn() {
		while(handTotal(dealerHand()) < 17) {
			if(dealerOver16AndNoBust())
				break;
			hit(dealerHand());		
		}
	}
	
	private boolean dealerOver16AndNoBust() {
		int handTotal = 0;
		for (int i = 0; i < dealerHand().getStackSize(); i++)
			handTotal += dealerHand().peekCard(i).getPointValue();
		if(handTotal >= 17 && handTotal <= 21)
			return true;
		else 
			return false;

	}
	
	
}
