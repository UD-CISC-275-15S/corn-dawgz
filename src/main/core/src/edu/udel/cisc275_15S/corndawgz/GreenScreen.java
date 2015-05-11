package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GreenScreen extends GameScreen{
	SpriteBatch batch;
	Texture youImg;
	Rectangle you; 
	BitmapFont font;
	Texture greenImg;
	Rectangle green;
	Texture roommateImg;
	Rectangle roommate; 
	
	
	public GreenScreen(Game g) {
		super(g);
	}
	
	public void show() {
		batch = new SpriteBatch();
		
		youImg = new Texture("headtom.png");
		you = new Rectangle();
		you.x = 0;
		you.y = 0;
		you.height = youImg.getHeight();
		you.width = youImg.getDepth();
		
		greenImg = new Texture("Green.jpg");
		green  = new Rectangle();
		green.x = 0;
		green.y = 0;
		
		roommateImg = new Texture("Christopher.png");
		roommate = new Rectangle();
		roommate.x = 0;
		roommate.y = 0;
		roommate.height = roommateImg.getHeight();
		roommate.width = roommateImg.getDepth();
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		
	}
	
	
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(greenImg, 0, 0, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
		batch.draw(youImg, you.x, you.y, 85, 131);
		batch.draw(roommateImg, roommate.x, roommate.y, 100, 185);
		
		batch.end();
	}

	public void dispose() {
	}
	
}
