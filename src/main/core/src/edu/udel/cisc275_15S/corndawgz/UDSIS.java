package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class UDSIS implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;

	private Skin skin;
	private TextButton nextButton;
	private TextButton backButton;
	private final float BUTTON_HEIGHT = 60;
	private final float BUTTON_WIDTH = 100;
	
	public UDSIS(){
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		stage = new ImageSequence();
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/start.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register1.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register2.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register3.png"))));
		stage.setFillParentTrue();
		stage.update();
	}

	public void show(){
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		nextButton = new TextButton("Next", skin);
		nextButton.setColor(Color.BLUE);
		nextButton.setWidth(BUTTON_WIDTH);
		nextButton.setHeight(BUTTON_HEIGHT);
		nextButton.setPosition(Gdx.graphics.getWidth() *3/4, 
				Gdx.graphics.getHeight() * 7/8);
		nextButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(TimeUtils.millis() > startTime + 1000){
					if(stage.hasNext()){
						stage.nextImage();
						startTime = TimeUtils.millis();
					}
					else{
						done = true;
					}
				}
			}
		});
		
		backButton = new TextButton("Next", skin);
		backButton.setColor(Color.BLUE);
		backButton.setWidth(BUTTON_WIDTH);
		backButton.setHeight(BUTTON_HEIGHT);
		backButton.setPosition(Gdx.graphics.getWidth() *4/8, 
				Gdx.graphics.getHeight() * 4/8);
		nextButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(TimeUtils.millis() > startTime + 1000){
					if(stage.hasPrev()){
						stage.prevImage();
						startTime = TimeUtils.millis();
					}
				}
			}
		});
		
		stage.addActor(nextButton);
		stage.addActor(backButton);
		Gdx.input.setInputProcessor(stage);
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
