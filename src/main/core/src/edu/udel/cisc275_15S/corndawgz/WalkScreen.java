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
// this class should be changed to the world map/beginning of game 
public class WalkScreen extends GameScreen {
	SpriteBatch batch;
	Texture thumbImg;
	Rectangle thumb; 
	BitmapFont font;
	Texture mapImg;
	Rectangle map;
	ShapeRenderer shaperenderer;
	
	public WalkScreen(Game g) {
		super(g);
	}

	public void show() {
		shaperenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		thumbImg = new Texture("headtom.png");
		thumb = new Rectangle();
		thumb.x = 0;
		thumb.y = 0;
		thumb.height = thumbImg.getHeight();
		thumb.width = thumbImg.getDepth();
		mapImg = new Texture("CampusMap.png");
		map = new Rectangle();
		map.x = 0;
		map.y = 0;
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mapImg, 0, 0, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
		batch.draw(thumbImg, thumb.x, thumb.y, 85, 131);
		font.draw(batch, "Click Enter to go to the test", 0, Gdx.graphics.getHeight() - 10f);
		
		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.circle(225, 190, 5);//center of memorial hall (225, 290) for getX, getY
		shaperenderer.setColor(Color.RED);
		
		shaperenderer.end();
		batch.end();
	
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) thumb.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) thumb.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.UP)) thumb.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) thumb.y -= 200 * Gdx.graphics.getDeltaTime();
	    ///System.out.println(thumb.x + " " + thumb.y);
	    if(thumb.x > 170 && thumb.x < 175 && thumb.y < 150 && thumb.y > 145){
	    	game.setScreen(new TempHallLocation(game));
	    }
	    //if(Gdx.input.isKeyPressed(Keys.ENTER)) game.setScreen(new TestScreen(game));

	    if(thumb.x < 0) thumb.x = 0;
	    if(thumb.y < 0) thumb.y = 0;
	    
	    if(Gdx.input.isButtonPressed(Buttons.LEFT)){
	    	if((Gdx.input.getX() < 230) && (Gdx.input.getX() > 220)  && (Gdx.input.getY() > 280) && (Gdx.input.getY() < 300)){
	    		game.setScreen(new TestScreen(game));
	    	}
	    };
	}
	
	public void dispose() {
		thumbImg.dispose();
		batch.dispose();
	}
}
