package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TempMapScreen extends GameScreen implements Screen {
	private SpriteBatch batch;
	private Skin skin;
	private Stage stage;
	private final float BUTTON_HEIGHT = 60;
	private final float BUTTON_WIDTH = 100;
	private Image background;
	private boolean otherStage = false;
	private boolean advisorBool = false;
	private boolean libraryBool = false;
	private boolean memorialBool = false;
	private boolean udsisBool = false;	//udsis
	private MyEvent myEvent;
	private TextButton button1;
	private TextButton button2;	
	private TextButton button3;
	private TextButton button4;		//udsis

	public TempMapScreen(Game g) {
		super(g);
	}

	public TempMapScreen(Game g, String name) {
		super(g);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();

		background = new Image(new Texture("CampusMap.png"));

		button1 = new TextButton("Library", skin);
		button1.setColor(Color.RED);
		button1.setWidth(BUTTON_WIDTH);
		button1.setHeight(BUTTON_HEIGHT);
		button1.setPosition(Gdx.graphics.getWidth() / 2 - 100,
				Gdx.graphics.getHeight() / 2 - 25);

		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Library();
				otherStage = true;
			}
		});

		button2 = new TextButton("Memorial Hall", skin);
		button2.setColor(Color.RED);
		button2.setWidth(BUTTON_WIDTH);
		button2.setHeight(BUTTON_HEIGHT);
		button2.setPosition(Gdx.graphics.getWidth() / 2 - 30,
				Gdx.graphics.getHeight() / 2 + 75);

		button2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new MemorialHall();
				otherStage = true;
			}
		});

		button3 = new TextButton("Advisor's Office", skin);
		button3.setColor(Color.RED);
		button3.setWidth(BUTTON_WIDTH);
		button3.setHeight(BUTTON_HEIGHT);
		button3.setPosition(Gdx.graphics.getWidth() / 2 - 170,
				Gdx.graphics.getHeight() / 2 + 100);

		button3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Advisor();
				otherStage = true;
			}
		});
		
		//UDSIS
		button4 = new TextButton("UDSIS", skin);
		button4.setColor(Color.RED);
		button4.setWidth(BUTTON_WIDTH);
		button4.setHeight(BUTTON_HEIGHT);
		button4.setPosition(Gdx.graphics.getWidth() / 2 + 50,
				Gdx.graphics.getHeight() / 2 - 100);

		button4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new UDSIS();
				otherStage = true;
			}
		});

		// add the button and textField to the stage
		stage.addActor(background);
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);	//udsis
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		if (!otherStage) {
			//System.out.println("not other stage");
			Gdx.gl.glClearColor(0, 0, 0.4f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			stage.draw();
			batch.end();
		} else {
			myEvent.render(delta);
			if(myEvent.done()) {
				otherStage = false;
				completed(myEvent.getEventType());
			}
		}
		if (advisorBool && libraryBool && memorialBool && udsisBool) {	//udsis
			game.setScreen(new TestScreen(game));
		}
	}

	public void completed(String s) {
		if (s.equals("Advisor")) {
			advisorBool = true;
			button3.setColor(Color.GREEN);
		}
		if (s.equals("Library")) {
			libraryBool = true;
			button1.setColor(Color.GREEN);
		}
		if (s.equals("MemorialHall")) {
			memorialBool = true;
			button2.setColor(Color.GREEN);
		}
		if(s.equals("UDSIS")) {
			udsisBool = true;
			button4.setColor(Color.GREEN);
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}