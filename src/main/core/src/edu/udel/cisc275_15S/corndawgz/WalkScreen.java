package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
// this class should be changed to the world map/beginning of game
public class WalkScreen extends GameScreen {
	SpriteBatch batch;
	Texture thumbImg;
	Rectangle thumb; 
	BitmapFont font;
	
	public WalkScreen(Game g) {
		super(g);
	}

	public void show() {
		batch = new SpriteBatch();
		thumbImg = new Texture("thumbsUp.png");
		thumb = new Rectangle();
		thumb.x = 0;
		thumb.y = 0;
		thumb.height = thumbImg.getHeight();
		thumb.width = thumbImg.getDepth();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(thumbImg, thumb.x, thumb.y);
		font.draw(batch, "Click Enter to go to the test", 0, Gdx.graphics.getHeight() - 10f);
		batch.end();
	
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) thumb.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) thumb.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.UP)) thumb.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) thumb.y -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.ENTER)) game.setScreen(new TestScreen(game));

	    if(thumb.x < 0) thumb.x = 0;
	    if(thumb.y < 0) thumb.y = 0;
	}
	
	public void dispose() {
		thumbImg.dispose();
		batch.dispose();
	}
}
