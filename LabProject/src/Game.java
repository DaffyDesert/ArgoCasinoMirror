
public class Game {
	String[] questions = new String [10];
	String[] anwers = new String[10];
	int numOfQuestions;
	public Game(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}
	public Game() {
		numOfQuestions = 10;
	}
	
	public void LoadQuestions() {
	 //loads questions into a question bank and anwers
	}
	public String GetQestion(int index) {
		return questions[index];
	}
	public String GetAnwer(int index) {
		return anwers[index];
	}
	public void IncreaseQuestionCount() {
		//increase the number of questions
	}
	public int GetNumOfQuestions() {
		return numOfQuestions;
	}
}
