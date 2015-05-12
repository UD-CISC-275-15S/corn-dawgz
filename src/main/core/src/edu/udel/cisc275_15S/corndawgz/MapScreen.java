package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapScreen extends GameScreen {
	private SpriteBatch batch;
	private Skin skin;
	private Stage stage;
	private final float BUTTON_HEIGHT = 30;
	private final float BUTTON_WIDTH = 50;
	private final float LARGE_PHONE_X = 150;
	private final float LARGE_PHONE_Y = 20;
	
	private DialogueBox tutorial;

	private ImageSequence phone;
	private Image smallPhone;
	private boolean showLarge;

	private Image background;

	private boolean otherStage = false;
	private boolean advisorBool = false;
	private boolean libraryBool = false;
	private boolean careerAdvisementBool = false;
	private boolean udsisBool = false; // udsis
	private boolean studyBool = false;

	private MyEvent myEvent;
	private TextButton studyButton;
	private TextButton libraryButton;
	private TextButton careerAdvisementButton;
	private TextButton advisorButton;

	private int time;

	public MapScreen(Game g) {
		super(g);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();

		background = new Image(new Texture("CampusMap.png"));
		background.setFillParent(true);

		showLarge = false;
		smallPhone = new Image(new Texture("phone/phone.png"));
		smallPhone.setPosition(20, 20);
		smallPhone.setScale(.5f);
		smallPhone.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showLarge = !showLarge;
				if (showLarge) {
					stage.addActor(phone.getBackgroundImage());
				} else {
					phone.getBackgroundImage().remove();
				}
			}
		});

		phone = new ImageSequence();
		phone.addImages(new Image(new Texture("phone/meetAtLibrary.png")));
		phone.addImages(new Image(new Texture("phone/phoneLarge.png")));
		phone.setAllScale(.7f);
		phone.setAllPosition(LARGE_PHONE_X, LARGE_PHONE_Y);
		phone.update();

		libraryButton = new TextButton("Library", skin);
		libraryButton.setColor(Color.YELLOW);
		libraryButton.setWidth(BUTTON_WIDTH);
		libraryButton.setHeight(BUTTON_HEIGHT);
		libraryButton.setPosition(Gdx.graphics.getWidth() / 2 - 100,
				Gdx.graphics.getHeight() / 2 - 25);

		libraryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Library();
				otherStage = true;
			}

		});

		careerAdvisementButton = new TextButton("Career Advisement Center",
				skin);
		careerAdvisementButton.setColor(Color.RED);
		careerAdvisementButton.setWidth(BUTTON_WIDTH);
		careerAdvisementButton.setHeight(BUTTON_HEIGHT);
		careerAdvisementButton.setPosition(Gdx.graphics.getWidth() / 2 - 30,
				Gdx.graphics.getHeight() / 2 + 75);

		careerAdvisementButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new CareerAdvisement();
				otherStage = true;

			}
		});

		advisorButton = new TextButton("Advisor's Office", skin);
		advisorButton.setColor(Color.RED);
		advisorButton.setWidth(BUTTON_WIDTH);
		advisorButton.setHeight(BUTTON_HEIGHT);
		advisorButton.setPosition(Gdx.graphics.getWidth() / 2 - 170,
				Gdx.graphics.getHeight() / 2 + 100);

		advisorButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Advisor();
				otherStage = true;
			}
		});
		
		
		studyButton = new TextButton("Study Abroad", skin);
		studyButton.setColor(Color.RED);
		studyButton.setWidth(BUTTON_WIDTH);
		studyButton.setHeight(BUTTON_HEIGHT);
		studyButton.setPosition(Gdx.graphics.getWidth() / 2 - 190,
				Gdx.graphics.getHeight() / 2 + 150);

		studyButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new StudyAbroad();
				otherStage = true;
			}
		});
		tutorial = new DialogueBox("Welcome to University of Delaware!\n"
				+ "Here you will navigate campus and learn all you need "
				+ "to know before starting your journey. Click on the library "
				+ "to start. And pay attention; there is a lot for you to learn "
				+ "before you can become a hero.\nGood luck! YouDee needs you.");
		tutorial.setAlignment(1, 1);
		
		libraryButton.setTouchable(Touchable.enabled);
		careerAdvisementButton.setTouchable(Touchable.disabled);
		advisorButton.setTouchable(Touchable.disabled);
		studyButton.setTouchable(Touchable.disabled);
		stage.addActor(background);
		stage.addActor(libraryButton);
		stage.addActor(careerAdvisementButton);
		stage.addActor(advisorButton);
		stage.addActor(studyButton);
		stage.addActor(smallPhone);
		stage.addActor(tutorial);
		Gdx.input.setInputProcessor(stage);

		time = 0;
	}

	@Override
	public void render(float delta) {
		time++;
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!otherStage) {
			batch.begin();
			stage.draw();
			batch.end();
			studyButton.setTouchable(Touchable.enabled);
			libraryButton.setTouchable(Touchable.enabled);
			careerAdvisementButton.setTouchable(Touchable.enabled);
			advisorButton.setTouchable(Touchable.enabled);
		}
		if (otherStage) {
			studyButton.setTouchable(Touchable.disabled);
			libraryButton.setTouchable(Touchable.disabled);
			careerAdvisementButton.setTouchable(Touchable.disabled);
			advisorButton.setTouchable(Touchable.disabled);

			myEvent.render(delta);
			if (myEvent.done()) {
				otherStage = false;
				completed(myEvent.getEventType());
				Gdx.input.setInputProcessor(stage);
			}
		}
		if (advisorBool && libraryBool && careerAdvisementBool && udsisBool && studyBool) {
			game.setScreen(new BossBattle(game));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (time > 50) {
				game.setScreen(new BossBattle(game));
			}
		}
	}

	public void completed(String s) {
		if (s.equals("Advisor")) {
			advisorBool = true;
			advisorButton.setColor(Color.GREEN);
			studyButton.setColor(Color.YELLOW);
			studyButton.setTouchable(Touchable.enabled);
		}
		if (s.equals("Library")) {
			libraryBool = true;
			libraryButton.setColor(Color.GREEN);
			careerAdvisementButton.setColor(Color.YELLOW);
			careerAdvisementButton.setTouchable(Touchable.enabled);
		}
		if (s.equals("CareerAdvisement")) {
			careerAdvisementBool = true;
			careerAdvisementButton.setColor(Color.GREEN);
			advisorButton.setColor(Color.YELLOW);
			advisorButton.setTouchable(Touchable.enabled);
			myEvent = new UDSIS();
			otherStage = true;
			Gdx.input.setInputProcessor(stage);
		}
		if (s.equals("UDSIS")) {
			udsisBool = true;
			otherStage = false;
			
		}
		if (s.equals("StudyAbroad")) {
			studyBool = true;
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}