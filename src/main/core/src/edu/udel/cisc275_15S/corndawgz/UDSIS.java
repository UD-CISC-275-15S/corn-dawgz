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
	private ImageSequence stage;
	private boolean done;
	
	public UDSIS(){
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		stage = new ImageSequence();
	
		stage.setFillParentTrue();
		stage.update();

		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
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
