import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Class representing a User Profile in the Argo Casino Game.
 * @author RoeMello Holiday
 * @contributor Alexander DeAngelis
 */
public class User {
    private String name;
    private double currency;
    private boolean isAdmin;
    private int blackjackWinCount;
    private LocalTime warBestTime;
    private int solitaireHighScore;

    public User() {
    	name = "NoName";
    	currency = 500;
    	isAdmin = false;
    	blackjackWinCount = 0;
    	warBestTime = LocalTime.of(0, 0, 0);
    	solitaireHighScore = 0;
    }
    
    public User(String name, double currency, boolean isAdmin, int blackjackWinCount, LocalTime warBestTime, int solitaireHighScore) {
        this.name = name;
        this.currency = currency;
        this.isAdmin = isAdmin;
        this.blackjackWinCount = blackjackWinCount;
        this.warBestTime = warBestTime;
        this.solitaireHighScore = solitaireHighScore;
    }
    
    /**
     * @author Alexander DeAngelis
     */
    @Override
    public String toString() {
    	if (isAdmin) {
    		return name + "\n" + currency + "\n" + "Admin" + "\n" + blackjackWinCount + "\n" +  warBestTime.format( DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" + solitaireHighScore;
    	}
    	else {
    		return name + "\n" + currency + "\n" + "notAdmin" + "\n" + blackjackWinCount + "\n" +  warBestTime.format( DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" + solitaireHighScore;
    	}
    	
    }
    
    /**
     * @author Alexander DeAngelis
     * @return String representing a User's full stats for GUI output.
     */
    public String statDisplay() {
    	if (isAdmin) {
    		return "Admin" + "\n" + "Username: " + name + "\n" + "Cash: $" + currency + "\n" + "BlackJack Wins: " + blackjackWinCount + "\n" + "Best Time in War: " + warBestTime.format( DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" + "Solitaire High Score: " + solitaireHighScore;
    	}
    	else {
    		return "\n" + "Username: " + name + "\n" + "Cash: $" + currency + "\n" + "BlackJack Wins: " + blackjackWinCount + "\n" + "Best Time in War: " + warBestTime.format( DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" + "Solitaire High Score: " + solitaireHighScore;
    	}
    }
    
    /**
     * @author Alexander DeAngelis
     * @return String representing the file in which the user's encrypted password is stored.
     */
    public String getSaveFile() {
    	return "saves/" + name + ".txt";
    }

     // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

	public int getBlackjackWinCount() {
		return blackjackWinCount;
	}

	public void setBlackjackWinCount(int blackjackWinCount) {
		this.blackjackWinCount = blackjackWinCount;
	}

	public LocalTime getWarBestTime() {
		return warBestTime;
	}

	public void setWarBestTime(LocalTime warBestTime) {
		this.warBestTime = warBestTime;
	}

	public int getSolitaireHighScore() {
		return solitaireHighScore;
	}

	public void setSolitaireHighScore(int solitaireHighScore) {
		this.solitaireHighScore = solitaireHighScore;
	}
}