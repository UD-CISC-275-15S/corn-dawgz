package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class CareerAdvisement implements MyEvent {
	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;
	public DialogueBox step1;
	
	public CareerAdvisement() {
    	batch = new SpriteBatch();
		startTime = TimeUtils.millis();
        stage = new ImageSequence();
        step1 = new DialogueBox("Click the computer to access UDSIS", DialogueBox.TOPLEFT);
		stage.addImages(new Image(new Texture(Gdx.files.internal("update/ImageThree.png"))),
				new DialogueBox("You can't beat DeeYou without knowing your stuff.\n"
						+ "UDSIS will help your brain become strong and buff!\n", DialogueBox.BOTTOMWIDE));

		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/start.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register1.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register2.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/register3.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/change1.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/change2.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/audit1.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/audit2.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/audit3.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/audit4.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("UDSIS/audit5.png"))));
		stage.addImages(new Image(new Texture(Gdx.files.internal("update/ImageThree.png"))),
				new DialogueBox("Well, what are you still doing here?\n"
						+ "Quickly visit your advisor, I think DeeYou is near!\n", DialogueBox.BOTTOMWIDE));
        stage.setFillParentTrue();
        stage.update();
        Gdx.input.setInputProcessor(stage);
	}
	
    @Override
    public void render(float delta) {
    	HandleInput();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw(); 
        batch.end();
    }
    
    public void HandleInput() {
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
		return "CareerAdvisement";
	}
}