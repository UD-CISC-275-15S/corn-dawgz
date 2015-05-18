package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen extends GameScreen {
	private SpriteBatch batch;
    private Texture texture;
    private Image background;
    private long startTime;
    private Stage stage;
    
    public SplashScreen(Game g) {
    	super(g);
    }
    
    @Override
    public void show() {
    	batch = new SpriteBatch();
    	texture = new Texture(Gdx.files.internal("boss-pre-screen/tompls/start.png")); //** texture is now the splash image **//
    	background = new Image(texture);
    	background.setFillParent(true);
    	startTime = TimeUtils.millis();
    	stage = new Stage();
    	stage.addActor(background);
    	
    }
   
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.draw();	
        batch.end();
        if (TimeUtils.millis()>(startTime+2000)) 
        	game.setScreen(new StartScreen(game));
        
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }

}

