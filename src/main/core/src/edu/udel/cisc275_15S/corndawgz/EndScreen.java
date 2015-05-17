package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

public class EndScreen extends GameScreen {
	private SpriteBatch batch;
    private Image image;
    private long startTime;
    private Stage stage;
    private boolean music = false;
    private float correct;
    private final float PASS_PERCENTAGE = .70f;
    private boolean pass;
    
    public EndScreen(Game g, float cor) {
    	super(g);
    	correct = cor;
    }
    
    @Override
    public void show() {
    	batch = new SpriteBatch();
    	startTime = TimeUtils.millis();
    	stage = new Stage();
    	if(correct >=  PASS_PERCENTAGE) {
    		image = new Image(new Texture(Gdx.files.internal("end/Victory.png")));
    		image.setFillParent(true);
        	stage.addActor(image);
        	pass = true;
        } else {
        	image = new Image(new Texture(Gdx.files.internal("end/Defeat.png")));
        	image.setFillParent(true);
        	stage.addActor(image);
        	pass = false;
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
        if (TimeUtils.millis()>(startTime+1000) && !pass) 
        	if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        		game.setScreen(new MapScreen(game, true));
    }

    @Override
    public void dispose() {
    	stage.dispose();
        batch.dispose();
    }

}
