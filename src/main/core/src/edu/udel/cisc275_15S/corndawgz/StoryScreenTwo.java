package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import edu.udel.cisc275_15S.corndawgz.StoryScreenOne;
// this class should be changed to the world map/beginning of game 
public class StoryScreenTwo extends GameScreen {
	Game g;
	SpriteBatch batch;
	Texture thumbImg;
	Rectangle thumb; 
	BitmapFont font;
	Texture mapImg;
	private long startTime;
	private int rendCount;
	
	public StoryScreenTwo(Game g) {
		super(g);
	}

	public void show() {
		batch = new SpriteBatch();
		mapImg = new Texture("startscreentwo.png");
		startTime = TimeUtils.millis();
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mapImg, 0, 0, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());	
		batch.end();
		rendCount++;
		if (TimeUtils.millis()>(startTime+3500)) game.setScreen(new StoryScreenThree(game));
	}
	
	public void dispose() {
		thumbImg.dispose();
		batch.dispose();
	}
}
