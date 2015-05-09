package edu.udel.cisc275_15S.corndawgz;
// structure to hold the answers and the question
// can change to array of answers for more options than 4 in future
public class Question {
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String correct;
	public Question(){
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswerA() {
		return answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public String getCorrect() {
		return correct;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public void setCorrect(String cor) {
		this.correct = cor;
	}

	@Override
	public String toString() {
		return question + "\n" + "A: " + answerA + "\n" 
							   + "B: " + answerB + "\n" 
							   + "C: " + answerC + "\n" 	
							   + "D: " + answerD + "\n"
							   + "Correct: " + correct;
	}
}
