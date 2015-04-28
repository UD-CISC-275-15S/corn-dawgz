package edu.udel.cisc275_15S.corndawgz;

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
public class WalkScreen extends GameScreen implements InputProcessor {

	private static final int WORLD_WIDTH = 1000;
	private static final int WORLD_HEIGHT = 1000;
	SpriteBatch batch;
	Texture headImg;
	Rectangle head;
	Phone phone;
	OrthographicCamera camera;
	Sprite mapSprite;

	public WalkScreen(Game g) {
		super(g);
	}

	public WalkScreen(Game g, String name) {
		super(g);
	}

	public void show() {
		batch = new SpriteBatch();

		headImg = new Texture("headtom.png");
		head = new Rectangle();
		head.x = 0;
		head.y = 0;
		head.height = headImg.getHeight();
		head.width = headImg.getWidth();

		phone = new Phone();

		mapSprite = new Sprite(new Texture("CampusMap.png"));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_WIDTH * (h / w));
		camera.position.set(camera.viewportWidth / 2f,
				camera.viewportHeight / 2f, 0);
		//camera.setToOrtho(true);
		camera.update();
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		camera.viewportWidth = WORLD_WIDTH; 
		camera.viewportHeight = WORLD_WIDTH * (h / w);
		camera.update();
	}

	public void render(float delta) {
		HandleInput();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		mapSprite.draw(batch);
		batch.draw(phone.getTexture(), phone.getX(), phone.getY());
		 
		batch.draw(headImg, head.x, head.y, 60, 80);
		 
		batch.end();
		
		//System.out.println("head.x: " + head.x + " head.y: " + head.y);
	}

	public void HandleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			head.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			head.x += 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			head.y -= 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			head.y += 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			float x = Gdx.input.getX();
			float y = Gdx.input.getY();
			System.out.println("x: " + x + "y: " + y);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new TestScreen(game));
		}
	}

	public void dispose() {
		headImg.dispose();
		batch.dispose();
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
		Vector3 pos = new Vector3(screenX, screenY, 0);
		camera.unproject(pos); // changed
		System.out.println("x!!: " + pos.x + "  y!!: " + pos.y);
		phone.clicked(screenX, screenY);
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