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
import com.badlogic.gdx.audio.Music;

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
		background = new Image(new Texture("bossBattle/background.png"));
		background.setFillParent(true);
		boss = new ImageSequence();
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/freemusic.mp3"));
		sound.play(1.0f);
		
		Image image1 = new Image(new Texture("bossBattle/Test6.png"));
		Image image2 = new Image(new Texture("bossBattle/Test5.png"));
		Image image3 = new Image(new Texture("bossBattle/Test4.png"));
		Image image4 = new Image(new Texture("bossBattle/Test3.png"));
		Image image5 = new Image(new Texture("bossBattle/Test2.png"));
		Image image6 = new Image(new Texture("bossBattle/Test1.png"));
		

		System.out.println("image1width: " + image1.getWidth());
		System.out.println("image6width: " + image6.getWidth());
//		image1.setScale(.5f);
//		image2.setScale(.9f);
//		image3.setScale(.8f);
//		image4.setScale(.7f);
//		image5.setScale(.6f);
//		image6.setScale(.5f);
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
		image6.setScaleX(image6.getWidth()/image1.getWidth() *.6f);
		image6.setScaleY(image6.getHeight()/image1.getHeight() *.6f);
		
		image1.setPosition((Gdx.graphics.getHeight()-image1.getHeight())/2,
				(Gdx.graphics.getWidth()-image1.getWidth())/2);
		image2.setPosition((Gdx.graphics.getHeight()-image2.getHeight())/2,
				(Gdx.graphics.getWidth()-image2.getWidth())/2);
		image3.setPosition((Gdx.graphics.getHeight()-image3.getHeight())/2,
				(Gdx.graphics.getWidth()-image3.getWidth())/2);
		image4.setPosition((Gdx.graphics.getHeight()-image4.getHeight())/2,
				(Gdx.graphics.getWidth()-image4.getWidth())/2);
		image5.setPosition((Gdx.graphics.getHeight()-image5.getHeight())/2,
				(Gdx.graphics.getWidth()-image5.getWidth())/2);
		image6.setPosition((Gdx.graphics.getHeight()-image6.getHeight())/2,
				(Gdx.graphics.getWidth()-image6.getWidth())/2);
		
		
		boss.addImages(image1);
		boss.addImages(image2);
		boss.addImages(image3);
		boss.addImages(image4);
		boss.addImages(image5);
		boss.addImages(image6);
		//bossScale = .6f;
		
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
		music = false;
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
			if(questions.getNumberCorrect() > 4) {
				winorlose = 1;
			}
			if(questions.getNumberCorrect() < 4) {
				winorlose = 0;
			}
			game.setScreen(new EndScreen(game));
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
		gamesound.dispose();
	}

}
