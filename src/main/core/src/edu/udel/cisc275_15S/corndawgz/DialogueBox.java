package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DialogueBox extends Label{
	
	private Pixmap grayPixmap;
	private Texture grayTexture;
	private TextureRegionDrawable grayRegion;
	private LabelStyle grayStyle;
	
	public DialogueBox(String content)	{
		super(content, new Skin(Gdx.files.internal("skin/uiskin.json")));
		super.setX(10);
		super.setY(100);
		super.setWrap(true);
		super.setAlignment(Align.bottomLeft);
		
		grayPixmap = new Pixmap((int)super.getWidth(), (int)super.getHeight(), Pixmap.Format.Alpha);
		grayPixmap.setColor(0, 0, 0, 0.75f);
		grayPixmap.fill();
		grayTexture = new Texture(grayPixmap);
		grayRegion = new TextureRegionDrawable(new TextureRegion(grayTexture));
		
		grayStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
		grayStyle.background = grayRegion;
		super.setStyle(grayStyle);
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
