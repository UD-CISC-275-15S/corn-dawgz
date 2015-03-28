package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class StartScreen extends GameScreen {
	// i think we need some sort of input processor to get the text
	// from the textField??? 
	private BitmapFont font;
	private SpriteBatch batch;
	private float time = 0;
	private FileHandle fileHandle;
	private TextField textField;
	private Skin skin;
	private Button button;
	
	public StartScreen(Game g){
		super(g);
	}
	
	@Override
	public void show (){
		font = new BitmapFont();
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);
		font.setScale(3);
		font.setColor(new Color(Color.LIGHT_GRAY));
		fileHandle = Gdx.files.internal("data/myfile");
		// not sure how to import a skin
		skin = new Skin();
		TextFieldStyle s = new TextFieldStyle(font, Color.GREEN, null, null, null);
		textField = new TextField("Some Text Area", s);
		button = new Button(skin, "Enter");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// cant figure out how to draw button or textfield
		batch.draw(button, 0, 0);
		batch.draw(textField, 0, 75);
		font.draw(batch, "Enter your UDel Email: ", 125, 400);
		batch.end();
		
		
		time += delta;
		if (time > 1) {
			// when they press enter
			if (Gdx.input.isKeyPressed(Keys.ENTER)) {
				// write the string in the textField, false means content of the file
				// will be overwritten
				fileHandle.writeString(textField.getText(), false);
				game.setScreen(new WalkScreen(game));
			}
		}
	}
	
	@Override
	public void hide(){
		Gdx.app.debug("SimpleSimon", "dispose StartScreen");
		batch.dispose();
		font.dispose();
	}

}
