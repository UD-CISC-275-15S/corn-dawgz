package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

// this class should be changed to the world map/beginning of game 
public class WalkScreen extends GameScreen implements InputProcessor {

	// final float WORLD_WIDTH = 16;
	// final float WORLD_HEIGHT = 12;
	SpriteBatch batch;
	Texture thumbImg;
	Rectangle thumb;
	Texture mapImg;
	ShapeRenderer shaperenderer;
	Phone phone;
	OrthographicCamera camera;
	Sprite map;

	public WalkScreen(Game g) {
		super(g);
	}

	public WalkScreen(Game g, String name) {
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
		phone = new Phone();

		mapImg = new Texture("CampusMap.png");
		mapImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		map = new Sprite(mapImg);
		map.setOrigin(0, 0);
		map.setPosition(-map.getWidth() / 2, -map.getHeight() / 2);

		camera = new OrthographicCamera(mapImg.getWidth(), mapImg.getHeight());

		Gdx.input.setInputProcessor(this);
	}

	public void render(float delta) {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		map.draw(batch);
		batch.end();
		batch.begin();
		batch.draw(phone.getTexture(), phone.getX() - (map.getWidth() / 2),
				phone.getY() - (map.getHeight() / 2));

		batch.draw(thumbImg, thumb.x - (map.getWidth() / 2),
				thumb.y - (map.getHeight() / 2), 80, 120);
		batch.end();

		// shaperenderer.begin(ShapeType.Filled);
		// shaperenderer.circle(225, 190, 5);// center of memorial hall (225,
		// 290)
		// // for getX, getY
		// shaperenderer.setColor(Color.RED);
		// shaperenderer.end();
		// batch.draw(phone.getTexture(), phone.getX(), phone.getY());
	}

	public void dispose() {
		thumbImg.dispose();
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.RIGHT)
			thumb.x += 200 * Gdx.graphics.getDeltaTime();

		if (keycode == Input.Keys.LEFT)
			thumb.x -= 200 * Gdx.graphics.getDeltaTime();

		if (keycode == Input.Keys.UP)
			thumb.y += 200 * Gdx.graphics.getDeltaTime();

		if (keycode == Input.Keys.DOWN)
			thumb.y -= 200 * Gdx.graphics.getDeltaTime();

		if (thumb.x > 170 && thumb.x < 175 && thumb.y < 150 && thumb.y > 145)
			game.setScreen(new TempHallLocation(game, "memhall"));

		System.out.println("thumb: " + thumb.x + ", " + thumb.y);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("x: " + (screenX - phone.getX()) + " y: " + (screenY - phone.getY()));
		if (screenX >= phone.getX()
				&& screenX <= phone.getX() + phone.getWidth()
				&& screenY >= phone.getY()
				&& screenY <= phone.getY() + phone.getHeight()) {
			phone.clicked(screenX - phone.getX(), screenY - phone.getY());
		}
		if ((screenX < 230) && (screenY > 220) && (screenX > 280)
				&& (screenY < 300)) {
			game.setScreen(new TempHallLocation(game, "purnhall"));
		}
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
