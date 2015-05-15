package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BossBattle extends GameScreen {
	private SpriteBatch batch;
	private QuestionReader questions;
	private ImageSequence boss;
	private Image background;
	private Stage stage;
	//private float bossScale;
	private Skin skin;
	private BossButton a;
	private BossButton b;
	private BossButton c;
	private BossButton d;
	private Sound sound;
	public BossBattle(Game g) {
		super(g);
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		questions = new QuestionReader("data/correctAnswers.txt", 
				"data/TestQuestions.txt", "data/myfile.txt");
		background = new Image(new Texture("bossBattle/background.png"));
		background.setFillParent(true);
		boss = new ImageSequence();
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/freemusic.mp3"));
		sound.play(1.0f);
		boss.addImages(new Image(new Texture("bossBattle/Test6.png")));
		boss.addImages(new Image(new Texture("bossBattle/Test5.png")));
		boss.addImages(new Image(new Texture("bossBattle/Test4.png")));
		boss.addImages(new Image(new Texture("bossBattle/Test3.png")));
		boss.addImages(new Image(new Texture("bossBattle/Test2.png")));
		boss.addImages(new Image(new Texture("bossBattle/Test1.png")));
		//bossScale = .6f;
		boss.setAllScale(.6f);//bossScale);
		boss.setAllPosition(100, 30);
		
		a = new BossButton(questions.getQuestion().getAnswerAformated(), skin, 0, this, "a");
		b = new BossButton(questions.getQuestion().getAnswerBformated(), skin, 1, this, "b");
		c = new BossButton(questions.getQuestion().getAnswerCformated(), skin, 2, this, "c");
		d = new BossButton(questions.getQuestion().getAnswerDformated(), skin, 3, this, "d");
		
		stage.addActor(background);
		stage.addActor(boss.getBackgroundImage());
		stage.addActor(new DialogueBox(questions.getQuestion().getQuestion()));
		stage.addActor(a);
		stage.addActor(b);
		stage.addActor(c);
		stage.addActor(d);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clicked(String s) {
		questions.writeFile(s);
		if(s.equals(questions.getQuestion().getCorrect())) {
			//bossScale = bossScale*.9f;
			if (boss.hasNext()) {
				boss.nextImage();
			}
		} else {
			//bossScale = bossScale*1.1f;
			if (boss.hasPrev()) {
				boss.prevImage();
			}
		}
		
		//boss.setAllScale(bossScale);
		if (questions.hasNext()) {
			questions.nextQuestion();
		} else {			
			questions.getNumberCorrect(); // This prints the number of correct to console
			sound.stop();
			game.setScreen(new SplashScreen(game));
		}
		
		stage.clear();
		stage.addActor(background);
		stage.addActor(boss.getBackgroundImage());
		stage.addActor(new DialogueBox(questions.getQuestion().getQuestion()));
		
		a.setText(questions.getQuestion().getAnswerAformated());
		b.setText(questions.getQuestion().getAnswerBformated());
		c.setText(questions.getQuestion().getAnswerCformated());
		d.setText(questions.getQuestion().getAnswerDformated());
		
		stage.addActor(a);
		stage.addActor(b);
		stage.addActor(c);
		stage.addActor(d);
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
		
	}

}
