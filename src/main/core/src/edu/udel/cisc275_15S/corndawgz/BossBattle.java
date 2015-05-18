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
import com.badlogic.gdx.scenes.scene2d.utils.Align;

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
		gamesound.stop();
		gamesound.setVolume(0, 0);
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		questions = new QuestionReader("data/correctAnswers.txt", 
				"data/TestQuestions.txt", "data/myfile.txt");
		questions.clearAnswers();
		background = new Image(new Texture("bossBattle/background.png"));
		background.setFillParent(true);
		boss = new ImageSequence();
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/freemusic.mp3"));
		sound.play(1.0f);
		
		Image image1 = new Image(new Texture("bossBattle/DUBB.png"));
		Image image2 = new Image(new Texture("bossBattle/DUBB1.png"));
		Image image3 = new Image(new Texture("bossBattle/DUBB2.png"));
		Image image4 = new Image(new Texture("bossBattle/DUBB3.png"));
		Image image5 = new Image(new Texture("bossBattle/DUBB4.png"));
 
		image1.setScaleX(image1.getWidth()/image1.getWidth() *.6f);
		image1.setScaleY(image1.getHeight()/image1.getHeight() *.6f);
		image2.setScaleX(image2.getWidth()/image1.getWidth() *.6f);
		image2.setScaleY(image2.getHeight()/image1.getHeight() *.6f);
		image3.setScaleX(image3.getWidth()/image1.getWidth() *.6f);
		image3.setScaleY(image3.getHeight()/image1.getHeight() *.6f);
		image4.setScaleX(image4.getWidth()/image1.getWidth() *.6f);
		image4.setScaleY(image4.getHeight()/image1.getHeight() *.6f);
		image5.setScaleX(image5.getWidth()/image1.getWidth() *.6f);
		image5.setScaleY(image5.getHeight()/image1.getHeight() *.6f);
		
		boss.addImages(image1);
		boss.addImages(image2);
		boss.addImages(image3);
		boss.addImages(image4);
		boss.addImages(image5);
		boss.setAllPosition(160, 90);
		
		a = new BossButton(questions.getQuestion().getAnswerAformated(), skin, 0, this, "a");
		b = new BossButton(questions.getQuestion().getAnswerBformated(), skin, 1, this, "b");
		c = new BossButton(questions.getQuestion().getAnswerCformated(), skin, 2, this, "c");
		d = new BossButton(questions.getQuestion().getAnswerDformated(), skin, 3, this, "d");
		
		stage.addActor(background);
		stage.addActor(boss.getBackgroundImage());
		stage.addActor(new DialogueBox(questions.getQuestion().getQuestion() + "\n \n \n \n \n ",
				       DialogueBox.BOTTOMWIDE,
				       Align.left));
		stage.addActor(a);
		stage.addActor(b);
		stage.addActor(c);
		stage.addActor(d);
		music = false;
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clicked(String s) {
		questions.writeFile(s);
		if(s.equals(questions.getQuestion().getCorrect())) {
			if (boss.hasNext()) {
				boss.nextImage();
			}
		} else {
			if (boss.hasPrev()) {
				boss.prevImage();
			}
		}
		
		if (questions.hasNext()) {
			questions.nextQuestion();
		} else {			
			sound.stop();
			game.setScreen(new EndScreen(game, questions.getPercentCorrect()));
		}
		
		stage.clear();
		stage.addActor(background);
		stage.addActor(boss.getBackgroundImage());
		stage.addActor(new DialogueBox(questions.getQuestion().getQuestion() + "\n \n \n \n \n ", 
									   DialogueBox.BOTTOMWIDE, 
									   Align.left));
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
		gamesound.dispose();
	}

}
