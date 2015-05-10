package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class MemorialHall implements MyEvent {
	private MyEvent myEvent;
	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;
	public DialogueBox step1 = new DialogueBox("hey I am testing the dialogue ok lets see if this works yo");
	private Image Comp;
	
	public MemorialHall() {
    	batch = new SpriteBatch();
        startTime = TimeUtils.millis();
        stage = new ImageSequence();
    	Comp = new Image(new Texture("phone/Unknown.jpeg"));
    	Comp.setPosition(250, 150);
		Comp.setScale(.75f);
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/careeradvisor.png"))));
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/careeradvisor.png"))));
        stage.addImages(new Image(new Texture(Gdx.files.internal("locations/careeradvisor.png"))));
        stage.setFillParentTrue();
        Comp.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			System.out.println("Hey");
/// WANT TO MAKE IT SO WHEN YOU CLICK ON THE COMPUTER SCREEN, IT GOES TO UDSIS
				}
			
		});
        stage.update();
	}
	
	
	
    @Override
    public void render(float delta) {
    	HandleInput();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw(); 
        stage.addActor(step1);
		stage.addActor(Comp);
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