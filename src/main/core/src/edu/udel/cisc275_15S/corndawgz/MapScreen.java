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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapScreen extends GameScreen {
	private SpriteBatch batch;
	private Skin skin;
	private Stage stage;
	private final float LARGE_PHONE_X = 150;
	private final float LARGE_PHONE_Y = 20;

	private boolean tutorial;
	private DialogueBox mainTutorial;
	private DialogueBox phoneTutorial;
	private DialogueBox phoneTutorial2;

	private ImageSequence phone;
	private int phonecount;
	private Image smallPhone;
	private boolean showLarge;

	private Image background;

	private boolean otherStage = false;

	private MyEvent myEvent;
	private TextButton studyButton;
	private TextButton libraryButton;
	private TextButton careerAdvisementButton;
	private TextButton advisorButton;
	private TextButton bossButton;
	private TextButton roommateButton;

	private int time;

	private boolean fromBoss;

	public MapScreen(Game g) {
		super(g);
	}

	public MapScreen(Game g, boolean complete) {
		super(g);
		fromBoss = complete;
	}

	public void updateSmallPhone() {
		if (phonecount < 7) {
			smallPhone.remove();

			if (phonecount == 0) {
				smallPhone = new Image(new Texture("phone/phone1.png"));
			}
			if (phonecount == 1) {
				smallPhone = new Image(new Texture("phone/phone3.png"));
			}
			if (phonecount == 2) {
				smallPhone = new Image(new Texture("phone/phone5.png"));
			}
			if (phonecount == 3) {
				smallPhone = new Image(new Texture("phone/phone6.png"));
			}
			if (phonecount == 4) {
				smallPhone = new Image(new Texture("phone/phone7.png"));
			}
			if (phonecount == 5) {
				smallPhone = new Image(new Texture("phone/phone8.png"));
			}
			if (phonecount == 6) {
				smallPhone = new Image(new Texture("phone/phonecomp.png"));
			}
			// smallPhone = new Image(new Texture("phone/phone1.png"));
			smallPhone.setPosition(20, 20);
			smallPhone.setScale(.5f);
			smallPhone.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (tutorial) {
						if (stage.getActors().contains(phoneTutorial2, true)) {
							phoneTutorial2.remove();
							tutorial = false;
						} else {
							phoneTutorial.remove();
							libraryButton.setTouchable(Touchable.enabled);
							stage.addActor(libraryButton);
							stage.addActor(phoneTutorial2);
						}
					}
					showLarge = !showLarge;
					if (showLarge) {
						stage.addActor(phone.getBackgroundImage());
					} else {
						phone.getBackgroundImage().remove();
					}
				}
			});
			stage.addActor(smallPhone);
			phonecount += 1;
		}
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();

		background = new Image(new Texture("CampusMap.png"));
		background.setFillParent(true);
		showLarge = false;

		// phone setting
		phonecount = 1;
		//updateSmallPhone();
		smallPhone = new Image(new Texture("phone/phone1.png"));
		smallPhone.setPosition(20, 20);
		smallPhone.setScale(.5f);
		smallPhone.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (tutorial) {
					if (stage.getActors().contains(phoneTutorial2, true)) {
						phoneTutorial2.remove();
						tutorial = false;
					} else {
						phoneTutorial.remove();
						libraryButton.setTouchable(Touchable.enabled);
						stage.addActor(libraryButton);
						stage.addActor(phoneTutorial2);
					}
				}
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
		libraryButton.setPosition(Gdx.graphics.getWidth() / 2 - 88,
				Gdx.graphics.getHeight() / 2 - 70);

		libraryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Library();
				otherStage = true;
			}
		});

		careerAdvisementButton = new TextButton("Career\nAdvisement\nCenter",
				skin);
		careerAdvisementButton.setColor(Color.RED);
		careerAdvisementButton.setPosition(Gdx.graphics.getWidth() / 2 - 30,
				Gdx.graphics.getHeight() / 2 + 75);

		careerAdvisementButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new CareerAdvisement();
				otherStage = true;
			}
		});

		advisorButton = new TextButton("Advisor's\nOffice", skin);
		advisorButton.setColor(Color.RED);
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
		studyButton.setPosition(Gdx.graphics.getWidth() / 2 - 190,
				Gdx.graphics.getHeight() / 2 + 150);

		studyButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new StudyAbroad();
				otherStage = true;
			}
		});
		
		roommateButton = new TextButton("Rodney Hall", skin);
		roommateButton.setColor(Color.RED);
		roommateButton.setPosition((Gdx.graphics.getWidth()/ 3) * 2, ((Gdx.graphics.getHeight() /4) * 2));
		roommateButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myEvent = new Roommate();
				otherStage = true;
			}
		});

		bossButton = new TextButton("Battle DU!", skin);
		bossButton.setColor(Color.RED);
		bossButton.setPosition(Gdx.graphics.getWidth() / 2 + 100,
				Gdx.graphics.getHeight() / 2 + 150);

		bossButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gamesound.setVolume(0, 0);
				gamesound.dispose();
				game.setScreen(new BossBattle(game));
			}
		});

		mainTutorial = new DialogueBox(
				"Welcome to University of Delaware!\n"
						+ "Here you will navigate campus and learn all you need to know before starting your journey\n"
						+ "Pay attention! There is a lot for you to learn before you can become a hero.\n"
						+ "Good luck! YouDee needs you.\n"
						+ "Click here to continue", DialogueBox.TOPWIDE);
		mainTutorial.setAlignment(1, 1);
		mainTutorial.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				mainTutorial.remove();
				stage.addActor(phoneTutorial);
				stage.addActor(smallPhone);
			}
		});
		phoneTutorial = new DialogueBox(
				"<---- Here is your phone! Click it to view your notes and objectives!",
				DialogueBox.BOTTOM);
		phoneTutorial2 = new DialogueBox("Click again to go back\nto the map!",
				DialogueBox.LEFT, Align.topLeft);

		libraryButton.setTouchable(Touchable.disabled);
		careerAdvisementButton.setTouchable(Touchable.disabled);
		advisorButton.setTouchable(Touchable.disabled);
		roommateButton.setTouchable(Touchable.disabled);
		studyButton.setTouchable(Touchable.disabled);
		stage.addActor(background);
		stage.addActor(mainTutorial);
		Gdx.input.setInputProcessor(stage);
		time = 0;
		tutorial = true;

		if (fromBoss) {
			stage.clear();
			tutorial = false;
			stage.addActor(background);
			stage.addActor(libraryButton);
			stage.addActor(careerAdvisementButton);
			stage.addActor(advisorButton);
			stage.addActor(studyButton);
			stage.addActor(roommateButton);
			stage.addActor(bossButton);
			stage.addActor(smallPhone);
			libraryButton.setTouchable(Touchable.enabled);
			careerAdvisementButton.setTouchable(Touchable.enabled);
			advisorButton.setTouchable(Touchable.enabled);
			studyButton.setTouchable(Touchable.enabled);
			roommateButton.setTouchable(Touchable.enabled);
			libraryButton.setColor(Color.GREEN);
			careerAdvisementButton.setColor(Color.GREEN);
			advisorButton.setColor(Color.GREEN);
			studyButton.setColor(Color.GREEN);
			roommateButton.setColor(Color.GREEN);
			bossButton.setColor(Color.RED);
		}
	}

	@Override
	public void render(float delta) {
		//System.out.println("height: " +Gdx.graphics.getHeight() + " width " + Gdx.graphics.getWidth());
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
			roommateButton.setTouchable(Touchable.enabled);
		}
		if (otherStage) {
			studyButton.setTouchable(Touchable.disabled);
			libraryButton.setTouchable(Touchable.disabled);
			careerAdvisementButton.setTouchable(Touchable.disabled);
			advisorButton.setTouchable(Touchable.disabled);
			roommateButton.setTouchable(Touchable.disabled);

			myEvent.render(delta);
			if (myEvent.done()) {
				otherStage = false;
				completed(myEvent.getEventType());
				Gdx.input.setInputProcessor(stage);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (time > 50) {
				gamesound.setVolume(0, 0);
				gamesound.dispose();
				game.setScreen(new BossBattle(game));
			}
		}
	}

	public void completed(String s) {
		if (s.equals("Library")) {
			libraryButton.setColor(Color.GREEN);
			careerAdvisementButton.setColor(Color.RED);
			careerAdvisementButton.setTouchable(Touchable.enabled);
			stage.addActor(careerAdvisementButton);
			updateSmallPhone();
		}
		if (s.equals("CareerAdvisement")) {
			careerAdvisementButton.setColor(Color.GREEN);
			advisorButton.setColor(Color.RED);
			advisorButton.setTouchable(Touchable.enabled);
			stage.addActor(advisorButton);
			updateSmallPhone();
		}
		if (s.equals("Advisor")) {
			advisorButton.setColor(Color.GREEN);
			roommateButton.setColor(Color.RED);
			roommateButton.setTouchable(Touchable.enabled);
			stage.addActor(roommateButton);
			updateSmallPhone();

		}
		if(s.equals("Roommate")){
			roommateButton.setColor(Color.GREEN);
			studyButton.setTouchable(Touchable.enabled);
			studyButton.setColor(Color.RED);
			stage.addActor(studyButton);
			updateSmallPhone();
		}
		if (s.equals("StudyAbroad")) {
			studyButton.setColor(Color.GREEN);
			bossButton.setTouchable(Touchable.enabled);
			stage.addActor(bossButton);
			updateSmallPhone();
		}
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}