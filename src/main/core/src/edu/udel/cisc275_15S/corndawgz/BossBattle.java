package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BossBattle extends GameScreen {
	private SpriteBatch batch;
	private QuestionReader questions;
	private Image boss;
	private Image background;
	private BitmapFont font;
	private Stage stage;
	private float bossScale;
	private Skin skin;
	private BossButton a;
	private BossButton b;
	private BossButton c;
	private BossButton d;
	
	public BossBattle(Game g) {
		super(g);
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		font = new BitmapFont();
		questions = new QuestionReader();
		background = new Image(new Texture("startdawgz.png"));
		background.setFillParent(true);
		boss = new Image(new Texture("headtom.png")); // NEED TO ADD
		bossScale = 1;
		boss.setScale(bossScale);
		boss.setPosition(50, 30);
		
		a = new BossButton("a", skin, 0, this);
		b = new BossButton("b", skin, 1, this);
		c = new BossButton("c", skin, 2, this);
		d = new BossButton("d", skin, 3, this);
		
		stage.addActor(background);
		stage.addActor(boss);
		stage.addActor(a);
		stage.addActor(b);
		stage.addActor(c);
		stage.addActor(d);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clicked(String s) {
		// TODO: write to file
		if(s.equals(questions.getQuestion().getCorrect())) {
			bossScale = bossScale - .2f;
		} else {
			bossScale = bossScale + .2f;
		}
		boss.setScale(bossScale);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
		
		batch.begin();
		font.drawMultiLine(batch, questions.getQuestion().toString(), 10, Gdx.graphics.getHeight() - 100f);
		batch.end();
	}
	
	@Override 
	public void dispose() {
		
	}

}
