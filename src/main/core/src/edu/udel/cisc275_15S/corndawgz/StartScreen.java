package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen extends GameScreen {
	private BitmapFont font;
	private Texture title;
	private SpriteBatch batch;
	private float time = 0;
	
	public StartScreen(Game g){
		super(g);
	}
	
	@Override
	public void show (){
		font = new BitmapFont();
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);
		font.setScale(3);
		font.setColor(new Color(Color.LIGHT_GRAY));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Click Any Button to Continue", 125, 275);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.setScreen(new WalkScreen(game));
			}
		}
	}
	
	@Override
	public void hide(){
		Gdx.app.debug("SimpleSimon", "dispose StartScreen");
		batch.dispose();
		font.dispose();
	}

}
