package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;

// GITHUB WORK DAMN YOU

public class CornDawgzGame1 extends Game {
	// change
	@Override
	public void create () {
		setScreen(new StartScreen(this));
	}
}
