package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
// this class should be changed to the world map/beginning of game 

public class Library implements MyEvent {

	private long startTime;
	private SpriteBatch batch;
	private ImageSequence stage;
	private boolean done;
	
	public Library() {
		done = false;
		stage = new ImageSequence();
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();


		stage.addImages(new Image(new Texture(Gdx.files
				.internal("locations/LIBRARYSCENE1.png"))),
		new DialogueBox("Hey Roomie, YouDees gone missing since DeeYou and him never made amends.\n"
						+ "You been having a good day? You make any friends? !\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture(Gdx.files
				.internal("locations/LIBRARYSCENE2.png"))),
				new DialogueBox("Jeeze, if I knew when drop/add period was I could know if I should drop these classes.\n"
						+ "Then my schedule would be free, to tell you where YouDee keeps his glasses.!\n", DialogueBox.BOTTOMWIDE));
		stage.addImages(new Image(new Texture(Gdx.files
				.internal("locations/LIBRARYSCENE3.png"))),
				new DialogueBox("Wow, you really are smart. If you want to find DeeYou, you should learn about your career.\n"
						+ "The last place you're gonna find an evil hen like him is in here!\n", DialogueBox.BOTTOMWIDE));

		stage.setFillParentTrue();
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

	public boolean done() {
		return done;
	}

	@Override
	public String getEventType() {
		return "Library";
	}
}
