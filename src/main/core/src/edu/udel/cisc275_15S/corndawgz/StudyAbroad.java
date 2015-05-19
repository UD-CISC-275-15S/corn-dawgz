package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
 
public class StudyAbroad implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;

	public StudyAbroad() {
		startTime = TimeUtils.millis();
		batch = new SpriteBatch();
		stage = new ImageSequence();
		stage.addImages(new Image(new Texture (Gdx.files.internal("UDSIS/StudyAbroad1.png"))),
				new DialogueBox("Welcome to Elliot Hall, you are here for study abroad and to learn.\n" 
						+ "I am Dr. Deeyu, the future of this school. Listen to me, and have no concerns.\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("UDSIS/StudyAbroad2.png"))),
				new DialogueBox("What? Who are you? You've come here to fight me?\n" 
						+ "I'm in the middle of something! Can't you see I'm stronger than you'll ever be!\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("UDSIS/StudyAbroad4.jpg"))),
				new DialogueBox("Hey roomie, uhh... I'm gonna go back to the room.\n" 
						+ "Remember what you learned and goodluck with this crazy loon!\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("UDSIS/StudyAbroad3.jpg"))),
				new DialogueBox("Well, if it's a fight you want, you better be ready for the test!\n" 
						+ "You better check your notes if you want to battle the best!\n", DialogueBox.BOTTOMWIDE));
		stage.setFillParentTrue();
		stage.update();
	}

	public void render(float delta) {
		HandleInput();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
	}

	public void HandleInput() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (TimeUtils.millis() > startTime + 1000) {
				if(stage.hasNext()) {
					stage.nextImage();
					startTime = TimeUtils.millis();
				} else {
					done = true;
				}
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (TimeUtils.millis() > startTime + 1000) {
				if (stage.hasNext()) {
					stage.nextImage();
					startTime = TimeUtils.millis();
				} else {
					done = true;
				}
			}
		}
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		if (TimeUtils.millis() > startTime + 1000) {
    			if (stage.hasPrev()) {
    				stage.prevImage();
    				startTime = TimeUtils.millis();
    			}
    		}
    	}
	}
	public boolean done() {
		return done;
	}

	@Override
	public String getEventType() {
		return "StudyAbroad";
	}
}