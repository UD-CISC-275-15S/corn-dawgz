package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TestScreen extends GameScreen {
	private SpriteBatch batch;
	private FileHandle file;
	private Skin skin;
	private QuestionStage stage;
	private int questionNumber;
	private Question[] questions;
	private BitmapFont font;
	
	public TestScreen(Game g) {
		super(g);
	}
	
	public void nextQuestion() {
		questionNumber++;
		if (questionNumber >= questions.length) {
			game.setScreen(new ReportScreen(game));
		} else {
			stage.setQuestion(questions[questionNumber]);			
		}
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new QuestionStage(Gdx.files.local("data/myfile.txt"), skin, this);
		file = Gdx.files.local("data/TestQuestions.txt");
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		// parse the question file
		String f = file.readString();
		// get the number of questions by looking for the number of @
		int numQuestions = 0;
		for (char c : f.toCharArray()) {
			if (c == '@') {
				numQuestions++;
			}
		}
		
		// fill in all the question objects
		questions = new Question[numQuestions - 1];
		int next = f.indexOf("@");
		for (int j = 0; j < questions.length; j++) {
			Question question = new Question();
			int q = f.indexOf("<Q>", next);
			int a = f.indexOf("<A>", next);
			int b = f.indexOf("<B>", next);
			int c = f.indexOf("<C>", next);
			int d = f.indexOf("<D>", next);
			next = f.indexOf("@", next + 1);
			question.setQuestion(f.substring(q+3, a));
			System.out.println(f.substring(q+3, a));
			question.setAnswerA(f.substring(a+3, b));
			question.setAnswerB(f.substring(b+3, c));
			question.setAnswerC(f.substring(c+3, d));
			question.setAnswerD(f.substring(d+3, next));
			questions[j] = question;
		}
		questionNumber = 0;
		stage.setQuestion(questions[0]);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// doesnt work for some reason
		System.out.println(stage.getQuestion());
		font.draw(batch, stage.getQuestion(), 0, Gdx.graphics.getHeight() - 10f);
		stage.draw();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
