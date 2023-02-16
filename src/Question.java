
public class Question {
	private String qtext;
	private String[] acceptableAnswers;
	private int difficulty;
	private int pointValue;
	
	public Question() {
		qtext = "";
		difficulty = 1;
		pointValue = 0;
	}

	public Question(String qtext, String[] acceptableAnswers, int difficulty, int pointValue) {
		super();
		this.qtext = qtext;
		this.acceptableAnswers = acceptableAnswers;
		this.difficulty = difficulty;
		this.pointValue = pointValue;
	}
	
	public String getAnswers() {
		String returnString = "";
		for (int i = 0; i < acceptableAnswers.length; i++) {
			returnString += acceptableAnswers[i];
		}
		return returnString;
	}

	public String getQtext() {
		return qtext;
	}

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	
}
