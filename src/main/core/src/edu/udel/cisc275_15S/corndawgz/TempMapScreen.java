package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TempMapScreen extends GameScreen implements Screen {
	private SpriteBatch batch;
	private FileHandle file;
	private TextField textField;
	private Skin skin;
	private Stage stage;
	private final float buttonHeight = 60;
	private final float buttonWidth = 100;
	private final float textFieldHeight = 100;
	private final float textFieldWidth = 200;
	private Image background;
	
	
	
	public TempMapScreen(Game g){
		super(g);
	}
	public TempMapScreen(Game g, String name) {
		super(g);
	}
	
	@Override
	public void show (){	
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		file = Gdx.files.local("data/myfile.txt");
		
		background = new Image(new Texture("CampusMap.png"));
	
		
		// Set up the TextButton
		final TextButton button = new TextButton("Library", skin);
		button.setWidth(buttonWidth);
		button.setHeight(buttonHeight);
		button.setPosition(Gdx.graphics.getWidth()/2 - 100, 
				Gdx.graphics.getHeight()/2 - 25);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// write the String in the textField to the file
				// only shows up in the Desktop assets 
				game.setScreen(new LibraryEvent(game));
			}
		});
		
	
		// Set up the TextButton again
		final TextButton button2 = new TextButton("Memorial Hall", skin);
		button2.setWidth(buttonWidth);
		button2.setHeight(buttonHeight);
		button2.setPosition(Gdx.graphics.getWidth() /2 - 30, 
				Gdx.graphics.getHeight()/2 + 75);
	
		button2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// write the String in the textField to the file
				// only shows up in the Desktop assets 
				game.setScreen(new MemorialHallEvent(game));
			}
		});
		
		
		
		// Set up the TextButton again
		final TextButton button3 = new TextButton("Advisor's Office", skin);
		button3.setWidth(buttonWidth);
		button3.setHeight(buttonHeight);
		button3.setPosition(Gdx.graphics.getWidth()/2 - 170, 
				Gdx.graphics.getHeight()/2 + 100);		
		
		
		button3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// write the String in the textField to the file
				// only shows up in the Desktop assets 
				game.setScreen(new AdvisorEvent(game));
			}
		});
		
		
		
		// add the button and textField to the stage
		stage.addActor(background);
		stage.addActor(button);
		stage.addActor(button2);
		stage.addActor(button3);
		// set the input processor to the stage
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// draw the stage
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

}