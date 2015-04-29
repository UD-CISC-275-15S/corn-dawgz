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
	private ClickableSequenceStage clickStage;
	private long startTime;

	public TestingCinematic(Game g) {
		super(g);
	}

	@Override	
	public void show() {
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		clickStage = new ClickableSequenceStage();
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/startscreenone.png"))));
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/startscreentwo.png"))));
		clickStage.addImages(new Image(new Texture(Gdx.files.internal("story/startscreenthree.png"))));
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
						game.setScreen(new TempMapScreen(game));
					}
				}
			}
	    }
	
	@Override
	public void dispose() {
		clickStage.dispose();
	}

}
