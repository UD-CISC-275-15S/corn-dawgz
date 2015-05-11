package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CareerAdvisement implements MyEvent {
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;
	public DialogueBox step1;
	
	public CareerAdvisement() {
    	batch = new SpriteBatch();
        stage = new ImageSequence();

        step1 = new DialogueBox("Click the computer to access UDSIS");
        
		ArrayList<Image> list = new ArrayList<Image>();
		Image image1 = new Image(new Texture(Gdx.files.internal("locations/careeradvisor.png")));
		Image image2 = new Image(new Texture("phone/Unknown.jpeg"));
		image2.setPosition(250, 150);
		image2.setScale(.2f);
		image2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				done = true;
			}
		});
		
		list.add(image1);
		list.add(image2);
		stage.addImages(list, step1);
        stage.setFillParentTrue();
        stage.update();
        Gdx.input.setInputProcessor(stage);
	}
	
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
		return "CareerAdvisement";
	}
}