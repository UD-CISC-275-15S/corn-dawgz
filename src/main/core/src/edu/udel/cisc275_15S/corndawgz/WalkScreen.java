package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class WalkScreen extends GameScreen {
	SpriteBatch batch;
	Texture guyImg;
	Rectangle guy; 
	
	public WalkScreen(Game g) {
		super(g);
	}

	public void show() {
		batch = new SpriteBatch();
		guyImg = new Texture("walkingGuy.png");
		guy = new Rectangle();
		guy.x = 0;
		guy.y = 0;
		guy.height = 512;
		guy.width = 405;
	}
	
	public void render(float delta) {
		batch.begin();
		batch.draw(guyImg, guy.x, guy.y);
		batch.end();
	
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) guy.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) guy.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.UP)) guy.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) guy.y -= 200 * Gdx.graphics.getDeltaTime();

	    if(guy.x < 0) guy.x = 0;
	    if(guy.x > 800 - 512) guy.x = 800 - 512;
	    if(guy.y < 0) guy.y = 0;
	    if(guy.y > 800 - 405) guy.y = 800 - 405;
	}
	
	public void dispose() {
		guyImg.dispose();
		batch.dispose();
	}
}
