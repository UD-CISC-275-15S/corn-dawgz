package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class Roommate implements MyEvent{
		private long startTime;
		private SpriteBatch batch;
		private ImageSequence stage;
		private boolean done;

		public Roommate() {
			startTime = TimeUtils.millis();
			batch = new SpriteBatch();
			stage = new ImageSequence();
			stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/Room1.jpg"))),
					new DialogueBox("If you are serious about wanting to beat DeeYou, I can show you the way.\n" 
							+ "The answers are here on campus but I won't delay.\n", DialogueBox.BOTTOMWIDE));
			stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/Room1.jpg"))),
					new DialogueBox("The secrets are hidden in 016 Memorial Hall -- the writing center.\n" 
							+ "But I know them and together we'll stop that evil dissenter!\n", DialogueBox.BOTTOMWIDE));
			stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/Room1.jpg"))),
					new DialogueBox("There's one other subject that DeeYou hates more than the rest.\n" 
							+ "Math! If you need help with math, don't start to stress.\n", DialogueBox.BOTTOMWIDE));
			stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/Room1.jpg"))),
					new DialogueBox("There's a math lab on campus at 053 McKinly.\n" 
							+ "There are tutors and tests there to help you succeed nimbly!\n", DialogueBox.BOTTOMWIDE));
			stage.addImages(new Image(new Texture (Gdx.files.internal("imagesforbeta/Room1.jpg"))),
					new DialogueBox("Okay, quickly! We have no time to waste!\n" 
							+ "Let's go get DeeYou and throw our knowledge in his face!\n", DialogueBox.BOTTOMWIDE));
			stage.setFillParentTrue();
			stage.update();
		}

		public void render(float delta) {
			HandleInput();
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			stage.draw();
			batch.end();
		}

		public void HandleInput() {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				if (TimeUtils.millis() > startTime + 1000) {
					if(stage.hasNext()) {
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
			return "Roommate";
		}
}
