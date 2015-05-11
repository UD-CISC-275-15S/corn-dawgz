package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class QuestionReader {
	private FileHandle file;
	private ArrayList<Question> questions;
	private int index;

	public QuestionReader(String answerFile, String questionFile, String writeFile){
		file = Gdx.files.internal(answerFile);
		String cor = file.readString();
		// clean up the file (the numbers were to help make file readable)
		cor = cor.replaceAll(":", "");
		cor = cor.replaceAll("1", "");
		cor = cor.replaceAll("2", "");
		cor = cor.replaceAll("3", "");
		cor = cor.replaceAll("4", "");
		cor = cor.replaceAll("5", "");
		cor = cor.replaceAll("6", "");
		cor = cor.replaceAll("7", "");
		cor = cor.replaceAll("8", "");
		cor = cor.replaceAll("9", "");
		cor = cor.replaceAll("\n","");
		char[] correct = cor.toCharArray();

		file = Gdx.files.local(questionFile);
		questions = new ArrayList<Question>();
		
		// parse the question file
		String f = file.readString();
		int numQuestions = 0;
		for (char c : f.toCharArray()) {
			if (c == '@') {
				numQuestions++;
			}
		}
		
		int next = f.indexOf("@");
		int i = 0;
		for (int j = 0; j < numQuestions-1; j++) {
			Question question = new Question();
			int q = f.indexOf("<Q>", next);
			int a = f.indexOf("<A>", next);
			int b = f.indexOf("<B>", next);
			int c = f.indexOf("<C>", next);
			int d = f.indexOf("<D>", next);
			next = f.indexOf("@", next + 1);
			question.setQuestion(f.substring(q+3, a));
			question.setAnswerA(f.substring(a+3, b));
			question.setAnswerB(f.substring(b+3, c));
			question.setAnswerC(f.substring(c+3, d));
			question.setAnswerD(f.substring(d+3, next));
			question.setCorrect(Character.toString(correct[i]));
			questions.add(question);
			i++;
		}
		index = 0;
		
		file = Gdx.files.local(writeFile);
	}

	public Question nextQuestion() {
		index++;
		if (index >= questions.size()){
			index = 0;
		}
		return questions.get(index);
	}
	
	public Question getQuestion() {
		return questions.get(index);
	}
	
	public void writeFile(String s) {
		file.writeString(s, true);
	}

}
