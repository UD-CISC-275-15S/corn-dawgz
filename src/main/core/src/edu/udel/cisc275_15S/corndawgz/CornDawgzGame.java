package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;

public class CornDawgzGame extends Game{
	@Override
	public void create(){
		setScreen(new SplashScreen(this));
	}
}
