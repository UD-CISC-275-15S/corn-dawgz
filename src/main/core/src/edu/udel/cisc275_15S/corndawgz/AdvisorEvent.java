package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.InputProcessor;

import edu.udel.cisc275_15S.corndawgz.GameScreen;
import edu.udel.cisc275_15S.corndawgz.TempMapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

// this class should be changed to the world map/beginning of game 
public class AdvisorEvent extends GameScreen implements InputProcessor {
	
	public AdvisorEvent(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	private long startTime;
 	boolean step1 = false;
	boolean step2 = false;
	boolean step3 = false;
	boolean step4 = false;
	// List<String> thisisforlater = new String[]{"splashImage", "splashImage2", "splashImage3"};
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
    @Override
    public void render(float delta) {
    	HandleInput();
    	batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if(step1 == false) {
        stage.addActor(splashImage);   
        splashImage.setSize(stage.getWidth(), stage.getWidth());
        }
        if(step2 == true) {
        	splashImage.remove();
        	stage.addActor(splashImage2);
        	 splashImage2.setSize(stage.getWidth(), stage.getWidth());
        }
        if(step3 == true) {
        	splashImage2.remove();
        	stage.addActor(splashImage3);
        	 splashImage3.setSize(stage.getWidth(), stage.getWidth());
        }
        if(step4 == true) {
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
    	texture = new Texture(Gdx.files.internal("locations/Advisor.jpg"));
        texture2 = new Texture(Gdx.files.internal("locations/Fountain1.jpg"));
        texture3 = new Texture(Gdx.files.internal("locations/Fountain2.jpg"));
        splashImage = new Image(texture);
        splashImage2 = new Image(texture2);
        splashImage3 = new Image(texture3);
        Gdx.input.setInputProcessor(this);
    }

    public void HandleInput() {
    	 if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
    		 
    	 if (TimeUtils.millis()>(startTime+2000)) {
 			step1 = true;
 		}
      if (TimeUtils.millis()>(startTime+3000)) {
         
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			step2 = true;
		}
      }
		if (TimeUtils.millis()>(startTime+4000)) {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			step3 = true;
		}
		}
		if (TimeUtils.millis()>(startTime+6000)) {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				step4 = true;
			}
			}
		}
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
    
    @Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
    }

