package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartScreen extends GameScreen {
	private SpriteBatch batch;
	private FileHandle file;
	private TextField textField;
	private Skin skin;
	private Stage stage;
	private final float buttonHeight = 20;
	private final float buttonWidth = 100;
	private final float textFieldHeight = 20;
	private final float textFieldWidth = 200;
	private Image background;

	
	public StartScreen(Game g){
		super(g);
	}
	
	@Override
	public void show (){	
		
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		file = Gdx.files.local("data/myfile.txt");
		
		background = new Image(new Texture("boss-pre-screen/tompls/Enter.png"));
		background.setFillParent(true);
		
		// Set up the textField
		textField = new TextField(" ", skin);
		textField.setHeight(textFieldHeight);
		textField.setWidth(textFieldWidth);
		textField.setPosition(Gdx.graphics.getWidth() /2 - textFieldWidth/2 - 75f, 
				Gdx.graphics.getHeight()/2 - textFieldHeight/2);
		
		// Set up the TextButton
		final TextButton button = new TextButton("Enter", skin);
		button.setWidth(buttonWidth);
		button.setHeight(buttonHeight);
		button.setPosition(Gdx.graphics.getWidth() /2 - buttonWidth/2 + 75f, 
				Gdx.graphics.getHeight()/2 - buttonHeight/2);
		// add a ClickListener to the button
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// write the String in the textField to the file
				// only shows up in the Desktop assets 
				file.writeString(textField.getText() + "@", false);
				button.setTouchable(Touchable.disabled);
				game.setScreen(new TestingCinematic(game));
			}
		});
		// add the button and textField to the stage
		stage.addActor(background);
		stage.addActor(button);
		stage.addActor(textField);
		// set the input processor to the stage
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

}
