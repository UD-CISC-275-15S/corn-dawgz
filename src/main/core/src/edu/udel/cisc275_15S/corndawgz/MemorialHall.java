package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class MemorialHall implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ClickableSequenceStage stage;
	private boolean done;
	
	public MemorialHall() {
    	batch = new SpriteBatch();
        startTime = TimeUtils.millis();
        stage = new ClickableSequenceStage();
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/MemorialSouth.jpg"))));
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/Memorial.jpg"))));
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/memhall.jpg"))));
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
    }
    
    @Override
	public boolean done() {
		return done;
	}

	@Override
	public String getEventType() {
		return "MemorialHall";
	}
}