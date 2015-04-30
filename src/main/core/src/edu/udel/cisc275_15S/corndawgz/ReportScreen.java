package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReportScreen extends GameScreen {
	SpriteBatch batch;
	Texture img;
	FileHandle file;
	
	public ReportScreen(Game g) {
		super(g);
	}

	@Override
	public void show (){
		batch = new SpriteBatch();
		img = new Texture("headtom.png");
		file = Gdx.files.internal("data/correctAnswers.txt");
		String cor = file.readString();
		// clean up the file (the numbers were to help make file readable)
		cor = cor.replaceAll(":", "");
		cor = cor.replaceAll("1", "");
		cor = cor.replaceAll("2", "");
		cor = cor.replaceAll("3", "");
		cor = cor.replaceAll("4", "");
		cor = cor.replaceAll("5", "");
		cor = cor.replaceAll("6", "");
		cor = cor.replaceAll("7", "");
		cor = cor.replaceAll("8", "");
		cor = cor.replaceAll("9", "");
		cor = cor.replaceAll("\n","");
		char[] correct = cor.toCharArray();
		
		file = Gdx.files.local("data/myfile.txt");
		String m = file.readString();
		m = m.replaceAll("\n", "");
		char[] ans = m.substring(m.indexOf("@") + 1 , m.length()).toCharArray();
		
		// count the number of correct answers
		int numCorrect = 0;
		for (int i = 0; i < correct.length; i++) {
			if (ans[i] == correct[i]){
				numCorrect++;
			}
		}
		// write it to the file
		String s = "\n" + numCorrect + "/" + ans.length + " = " + (float) numCorrect/ans.length;
		file.writeString(s, true);
	}
	
	@Override
	public void render(float delta) {
		// display the badlogic logo
		Gdx.gl.glClearColor(1, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		if (Gdx.input.isKeyPressed(Keys.SPACE)) game.setScreen(new StartScreen(game));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
	
}
