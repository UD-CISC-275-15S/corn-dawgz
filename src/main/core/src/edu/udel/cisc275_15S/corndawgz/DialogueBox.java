package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class DialogueBox extends Label{
	
	public DialogueBox(String content)	{
		super(content, new Skin(Gdx.files.internal("skin/uiskin.json")));
		super.setX(10);
		super.setY(0);
		super.setWrap(true);
		super.setAlignment(Align.bottomLeft);
	}


	public float getPrefWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getPrefHeight() {
		return (float) (Gdx.graphics.getHeight()*(1.0/4.0));
	}

	public float getMaxWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getMaxHeight() {
		return (float) (Gdx.graphics.getHeight()*(1.0/4.0));
	}

}
