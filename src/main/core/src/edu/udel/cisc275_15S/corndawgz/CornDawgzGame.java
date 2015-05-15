package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class CornDawgzGame extends Game{
	@Override
	public void create(){
		setScreen(new SplashScreen(this));
	}
	
	

}
