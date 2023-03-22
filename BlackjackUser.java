public class BlackjackUser {
    private String name;
    private double currency;
    private boolean isAdmin;
    private int blackjackHighScore;

    public BlackjackUser(String name, double currency, boolean isAdmin, int blackjackHighScore) {
        this.name = name;
        this.currency = currency;
        this.isAdmin = isAdmin;
        this.blackjackHighScore = blackjackHighScore;
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

    public int getHighScore() {
        return blackjackHighScore;
    }

    public void setHighScore(int highScore) {
        this.blackjackHighScore = highScore;
    }
}