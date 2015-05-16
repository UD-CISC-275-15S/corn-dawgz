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
		stage.addImages(new Image(new Texture (Gdx.files.internal("update/ImageFour.png"))),
				new DialogueBox("Welcome to the Study Abroad lecture, I have a lot for you all to learn.\n" 
						+ "I am Dr. Dewyu, the future of this school. Listen to me, and have no concerns.\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("update/ImageFour.png"))),
				new DialogueBox("What? Who are you? You've come here to fight me?.\n" 
						+ "I'm in the middle of something? Besides, I'm stronger than you'll ever be!\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("update/ImageFour.png"))),
				new DialogueBox("Well, if it's a fight you want, you better be ready for the test!.\n" 
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
	}
	public boolean done() {
		return done;
	}

	@Override
	public String getEventType() {
		return "StudyAbroad";
	}
}