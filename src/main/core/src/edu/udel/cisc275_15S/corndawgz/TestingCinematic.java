package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

// CLASS :: TestingCinematic has a stage with three actors. 
// The first actor is present at inception,
// while the other three actors are added
// and removed at two different intervals. 
// The screen is then set in the third interval
// to a new WalkScreen.

public class TestingCinematic extends GameScreen implements Screen {
	private SpriteBatch batch;
	private ImageSequence clickStage;
	private long startTime;
	public int count = 0;
	public TestingCinematic(Game g) {
		super(g);
	}

	@Override	
	public void show() {
	if (startTime > 0) {
		gamesound.stop();
	}
		gamesound.loop();
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		clickStage = new ImageSequence();
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/sequenceone.png"))),
				new DialogueBox("At the University of Delaware, the sun was shining and the Blue Hens were bright.\n" 
								+ "Everyone was happy throughout the days and the nights.\n", DialogueBox.BOTTOMWIDE));
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/sequencetwo.png"))),
				new DialogueBox("Except one evil bird, DeeYou, began to hatch his evil scheme.\n"
								+"He went and stole YouDee without being seen!\n", DialogueBox.BOTTOMWIDE));
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/sequencethree.png"))), 
				new DialogueBox("DeeYou wanted to be the new mascot, but we can't let that be.\n"
								+ "We believe you could you be the hero to set YouDee free!\n", DialogueBox.BOTTOMWIDE));
		clickStage.setFillParentTrue();
		clickStage.update();
	}

	@Override
	public void render(float delta) {
		HandleInput();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		clickStage.draw();
		batch.end();
	}

	 public void HandleInput() {
		 if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				if (TimeUtils.millis() > startTime + 1000) {
					if(clickStage.hasNext()) {
						clickStage.nextImage();
						startTime = TimeUtils.millis();
					} else {
						game.setScreen(new MapScreen(game));
					}
				}
			}
	    }
	
	@Override
	public void dispose() {
		clickStage.dispose();
	}

}
