package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
 
public class Advisor implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;

	public Advisor() {
		startTime = TimeUtils.millis();
		batch = new SpriteBatch();
		stage = new ImageSequence();
		stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/advisor.png"))),
				new DialogueBox("Welcome to my advising office, where all your dreams will come true.\n" 
						+ "Would you care to read about what it is we do?\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("update/Advisor1.png"))));
		stage.addImages(new Image(new Texture (Gdx.files.internal("update/Advisor2.png"))));
		stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/advisor.png"))),
				new DialogueBox("Well, that's all there is to know to defeat DeeYou.\n" 
						+ "I do not know where he is but check out what else there is to do.\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/advisor.png"))),
				new DialogueBox("The Study Abroad lecture is a pretty exciting showing.\n" 
						+ "Dee... Dr. Deeyu is the study abroad enthusiast on campus. He's really easy-going.\n", DialogueBox.BOTTOMWIDE));
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
		return "Advisor";
	}
}
