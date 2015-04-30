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
	}
	
	public void clicked() {
		screen.nextQuestion();
	}
	
	// makes TestButtons with the Strings in the Question object
	// and places them in the correct spot
	public void setQuestion(Question q){
		question = q.getQuestion();
		
		
		aButton = new TestButton(q.getAnswerA(), skin, "A");
		bButton = new TestButton(q.getAnswerB(), skin, "B");
		cButton = new TestButton(q.getAnswerC(), skin, "C");
		dButton = new TestButton(q.getAnswerD(), skin, "D");		
		
		aButton.setSize(q.getAnswerA().length() * 10, 20);
		bButton.setSize(q.getAnswerB().length() * 10, 20);
		cButton.setSize(q.getAnswerC().length() * 10, 20);
		dButton.setSize(q.getAnswerD().length() * 10, 20);
		
		// 0 is top 3 is bottom
		aButton.setPosition(0);
		bButton.setPosition(1);
		cButton.setPosition(2);
		dButton.setPosition(3);
		
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
