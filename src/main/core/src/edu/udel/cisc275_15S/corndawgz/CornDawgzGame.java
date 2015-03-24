package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// kuhiuhkjhufsduhuhuhiuh
public class CornDawgzGame extends Game {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {
		setScreen(new StartScreen(this));
	}
}
