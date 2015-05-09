package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class QuestionStage extends Stage {
	private FileHandle file;
	private Skin skin;
	private TestScreen screen;
	private String question;
	
	private TestButton aButton;
	private TestButton bButton;
	private TestButton cButton;
	private TestButton dButton;
	
	public QuestionStage(FileHandle f, Skin s, TestScreen sc) {
		super();
		file = f;
		skin = s;
		screen = sc; // observer
		aButton = new TestButton("", skin, "A");
		bButton = new TestButton("", skin, "B");
		cButton = new TestButton("", skin, "C");
		dButton = new TestButton("", skin, "D");
	}
	
	public void clicked() {
		screen.nextQuestion();
	}
	
	// makes TestButtons with the Strings in the Question object
	// and places them in the correct spot
	public void setQuestion(Question q){
		question = q.getQuestion();
		
		aButton.setText(q.getAnswerA());
		bButton.setText(q.getAnswerB());
		cButton.setText(q.getAnswerC());
		dButton.setText(q.getAnswerD());	
		
		aButton.setSize(q.getAnswerA().length() * 1, 20);
		bButton.setSize(q.getAnswerB().length() * 2, 20);
		cButton.setSize(q.getAnswerC().length() * 4, 20);
		dButton.setSize(q.getAnswerD().length() * 8, 20);
		
		// 0 is top 3 is bottom
		aButton.setPosition(0, q.getAnswerA().length() * 8);
		bButton.setPosition(1, q.getAnswerA().length() * 8);
		cButton.setPosition(2, q.getAnswerA().length() * 8);
		dButton.setPosition(3, q.getAnswerA().length() * 8);
		
		aButton.addListener(file, this);
		bButton.addListener(file, this);
		cButton.addListener(file, this);
		dButton.addListener(file, this);
		
		addActor(aButton);
		addActor(bButton);
		addActor(cButton);
		addActor(dButton);
	}
	
	public String getQuestion(){
		return question;
	}
}
