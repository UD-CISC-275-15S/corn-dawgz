package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class EndScreen extends GameScreen {
	private SpriteBatch batch;
    private Texture victorytexture;
    private Image victoryimage;
    private Texture defeattexture;
    private Image defeatimage;
    private long startTime;
    private Stage stage;
    private boolean music = false;
    
    public EndScreen(Game g) {
    	super(g);
    }
    
    @Override
    public void show() {
    	batch = new SpriteBatch();
    	victorytexture = new Texture(Gdx.files.internal("end/Victory.png")); //** texture is now the splash image **//
    	victoryimage = new Image(victorytexture);
    	victoryimage.setFillParent(true);
    	defeattexture = new Texture(Gdx.files.internal("end/Defeat.png")); //** texture is now the splash image **//
    	defeatimage = new Image(defeattexture);
    	defeatimage.setFillParent(true);
    	startTime = TimeUtils.millis();
    	stage = new Stage();
    	if(winorlose == 1) {
        	stage.addActor(victoryimage);
        	}
        	if(winorlose == 0) {
        	stage.addActor(defeatimage);
        	}
    
    }
   
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.draw();	
        batch.end();
        if (TimeUtils.millis()>(startTime+10000)) {
        	game.setScreen(new StartScreen(game));
        }
    }

    @Override
    public void dispose() {
        victorytexture.dispose();
        defeattexture.dispose();
        batch.dispose();
    }

}
