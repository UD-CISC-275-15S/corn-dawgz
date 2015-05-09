package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class QuestionStage extends Stage {
	private FileHandle file;
	private Skin skin;
	private QuestionReader screen;
	private String question;
	
	private BossButton aButton;
	private BossButton bButton;
	private BossButton cButton;
	private BossButton dButton;
	
	public QuestionStage(FileHandle f, Skin s, QuestionReader sc) {
		super();
		file = f;
		skin = s;
		screen = sc; // observer
		//aButton = new BossButton("", skin, "A");
		//bButton = new BossButton("", skin, "B");
		//cButton = new BossButton("", skin, "C");
		//dButton = new BossButton("", skin, "D");
	}
	
	public void clicked() {
		//screen.next();
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
		
//		aButton.addListener(file);
//		bButton.addListener(file);
//		cButton.addListener(file);
//		dButton.addListener(file);
		
		addActor(aButton);
		addActor(bButton);
		addActor(cButton);
		addActor(dButton);
	}
	
	public String getQuestion(){
		return question;
	}
}
