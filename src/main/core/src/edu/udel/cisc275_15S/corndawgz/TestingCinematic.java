package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;



// CLASS :: TestingCinematic has a stage with three actors. 
// The first actor is present at inception,
// while the other three actors are added
// and removed at two different intervals. 
// The screen is then set in the third interval
// to a new WalkScreen.

public class TestingCinematic extends GameScreen implements Screen {
	public TestingCinematic(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	private SpriteBatch batch;
	// Three textures created for images
	private Texture texture;
    private Texture texture2;
    private Texture texture3;
    // Three images created with the corresponding textures
    private Image splashImage;
    private Image splashImage2;
    private Image splashImage3;
    // Stage created
    private Stage stage = new Stage();
    private long startTime;
    @Override
    public void render(float delta) {
    	batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        stage.addActor(splashImage);
        // Image1 removed, Image 2 added and size set to the full stage size
        splashImage.setSize(stage.getWidth(), stage.getWidth());
		if (TimeUtils.millis()>(startTime+500)) {
        	splashImage.remove();
        	stage.addActor(splashImage2);
      
        	 splashImage2.setSize(stage.getWidth(), stage.getWidth());
        }
		 // Image2 removed, Image 3 added and size set to the full stage size
		if (TimeUtils.millis()>(startTime+1000)) {
			splashImage2.remove();
			stage.addActor(splashImage3);
			splashImage3.setSize(stage.getWidth(), stage.getWidth());
		}
		 // Screen set to WalkScreen
		if (TimeUtils.millis()>(startTime+1200)) {
			game.setScreen(new TempMapScreen(game));
        }
		batch.end();
    }

    @Override
    public void resize(int width, int height) {     
    }

    @Override
    public void show() {
    	batch = new SpriteBatch();
        startTime = TimeUtils.millis();
    	texture = new Texture(Gdx.files.internal("story/startscreenone.png"));
        texture2 = new Texture(Gdx.files.internal("story/startscreentwo.png"));
        texture3 = new Texture(Gdx.files.internal("story/startscreenthree.png"));
        splashImage = new Image(texture);
        splashImage2 = new Image(texture2);
        splashImage3 = new Image(texture3);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {       
    }

    @Override
    public void resume() {      
    }

    @Override
    public void dispose() {
        texture.dispose();
        stage.dispose();
    }

}
