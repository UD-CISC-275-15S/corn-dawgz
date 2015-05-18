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
	
	static public final int TOPLEFT = 0;
	static public final int TOP = 1;
	static public final int TOPRIGHT = 2;
	static public final int LEFT = 3;
	static public final int CENTER = 4;
	static public final int RIGHT = 5;
	static public final int BOTTOMLEFT = 6;
	static public final int BOTTOM = 7;
	static public final int BOTTOMRIGHT = 8;
	static public final int TOPWIDE = 9;
	static public final int CENTERWIDE = 10;
	static public final int BOTTOMWIDE = 11;
	
	public DialogueBox(String content)	{
		this(content, 11, Align.center);
	}
	
	public DialogueBox(String content, int alignment)	{
		this(content, alignment, Align.center);
	}
	
	public DialogueBox(String content,  int alignment, int align)	{
		super(content, new Skin(Gdx.files.internal("skin/uiskin.json")));
		super.setAlignment(align);
		super.setWrap(true);
		alignBox(alignment);
		
		
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
		String content = super.getText().toString();
		int lines = content.split("\n").length;
		return (float) (Gdx.graphics.getHeight()*(1.0/30.0)) * (lines + 1);
	}

	public float getMaxWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getMaxHeight() {
		String content = super.getText().toString();
		int lines = content.split("\n").length;
		return (float) (Gdx.graphics.getHeight()*(1.0/30.0)) * (lines + 1);
	}
	
	public void alignBox(int alignment)	{
		switch (alignment)	{
		//TOPLEFT
		case 0: setX(10f);
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/4.0)) - 10f);
				break;
		//TOP
		case 1: setX((float) (Gdx.graphics.getWidth()*(5.0/16.0)));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/4.0)) - 10f);
				break;
		//TOPRIGHT
		case 2: setX((float) (Gdx.graphics.getWidth()*(10.0/16.0) - 10f));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/4.0)) - 10f);
				break;
		//LEFT
		case 3: setX(10f);
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/8.0)));
				break;
		//CENTER
		case 4: setX((float) (Gdx.graphics.getWidth()*(5.0/16.0)));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/8.0)));
				break;
		//RIGHT
		case 5: setX((float) (Gdx.graphics.getWidth()*(10.0/16.0) - 10f));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY((float) (Gdx.graphics.getHeight()*(3.0/8.0)));
				break;
		//BOTTOMLEFT
		case 6: setX(10f);
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY(10f);
				break;
		//BOTTOM
		case 7: setX((float) (Gdx.graphics.getWidth()*(5.0/16.0)));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY(10f);
				break;
		//BOTTOMRIGHT
		case 8: setX((float) (Gdx.graphics.getWidth()*(10.0/16.0) - 10f));
				super.setWidth((float) (Gdx.graphics.getWidth()*(3.0/8.0)));
				setY(10f);
				break;
		//TOPWIDE
		case 9: setX(10f);
				setY((float) (Gdx.graphics.getHeight()*(3.0/4.0)) - 10f);
				break;
		//CENTERWIDE
		case 10:setX(10f);
				setY((float) (Gdx.graphics.getHeight()*(3.0/8.0)));
				break;
		//BOTTOMWIDE
		case 11:setX(10f);
				setY(10f);
				break;
		}
	}

}
