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

public class TestingCinematic extends GameScreen implements Screen {
	public TestingCinematic(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	private SpriteBatch batch;
	private Texture texture = new Texture(Gdx.files.internal("startscreenone.png"));
    private Texture texture2 = new Texture(Gdx.files.internal("startscreentwo.png"));
    private Texture texture3 = new Texture(Gdx.files.internal("Library1.jpg"));
    private Image splashImage = new Image(texture);
    private Image splashImage2 = new Image(texture2);
    private Image splashImage3 = new Image(texture3);
    private Stage stage = new Stage();
    private long startTime;
    @Override
    public void render(float delta) {
    	batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        splashImage.setSize(stage.getWidth(), stage.getWidth());
		if (TimeUtils.millis()>(startTime+3500)) {
        	splashImage.remove();
        	stage.addActor(splashImage2);
        	 splashImage2.setSize(stage.getWidth(), stage.getWidth());
        }
//		if (TimeUtils.millis()>(startTime+6500)) {
 //       	splashImage2.remove();
  //      	stage.addActor(splashImage3);
   //     	 splashImage3.setSize(stage.getWidth(), stage.getWidth());
   //     }
		if (TimeUtils.millis()>(startTime+5500)) {
			game.setScreen(new WalkScreen(game));
        }
		batch.end();
    }

    @Override
    public void resize(int width, int height) {     
    }

    @Override
    public void show() {
    	batch = new SpriteBatch();
        stage.addActor(splashImage);
        startTime = TimeUtils.millis();
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
