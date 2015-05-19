package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
// this class should be changed to the world map/beginning of game 

public class Library implements MyEvent {

	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;
	
	public Library() {
		done = false;
		stage = new ImageSequence();
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();


		stage.addImages(new Image(new Texture(Gdx.files
				.internal("update/ImageSeven.png"))),
		new DialogueBox("Morning Roomie, I can't believe you've been chosen to get YouDee back.\n"
						+ "DeeYou's one weakness is common Blue Hen knowledge, a knowledge attack!\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture(Gdx.files
				.internal("update/ImageSeven.png"))),
				new DialogueBox("The drop/add period is one of the most vital things to use as weaponry.\n"
						+ "Before the drop/add period ends, you can swap classes without penalty.\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture(Gdx.files
				.internal("update/ImageSeven.png"))),
				new DialogueBox("But dropping after the drop/add period ends causes a 'withdrawal' on your record.\n"
						+ "Remember what I've told you! This knowledge will be your shield and sword!\n", DialogueBox.BOTTOMWIDE));

		stage.setFillParentTrue();
		stage.update();
	}

	@Override
	public void render(float delta) {
		HandleInput();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
	}

	public void HandleInput() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if (TimeUtils.millis() > startTime + 1000) {
				if (stage.hasNext()) {
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
		return "Library";
	}
}
