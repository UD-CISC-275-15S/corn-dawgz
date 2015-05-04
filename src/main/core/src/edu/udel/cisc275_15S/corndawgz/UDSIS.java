package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class UDSIS implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ClickableSequenceStage stage;
	private boolean done;
	
	public UDSIS(){
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		stage = new ClickableSequenceStage();
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/start.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register1.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register2.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register3.png"))));
		stage.update();
	}

	@Override
	public void render(float delta) {
		HandleInput();
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
	}
	
	public void HandleInput(){
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
	}

	@Override
	public boolean done() {
		return done;
	}

	@Override
	public String getEventType() {
		return "UDSIS";
	}

}
