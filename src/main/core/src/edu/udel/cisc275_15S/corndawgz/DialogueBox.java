package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class DialogueBox extends Widget{
	private String content;
	private Image speaker;
	
	public DialogueBox(String content, Image speaker)	{
		this.content = content;
		this.speaker = speaker;
	}
	
	public float getMinWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getMinHeight() {
		return Gdx.graphics.getHeight()*(1/3);
	}

	public float getPrefWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getPrefHeight() {
		return Gdx.graphics.getHeight()*(1/3);
	}

	public float getMaxWidth() {
		return Gdx.graphics.getWidth() - 20;
	}

	public float getMaxHeight() {
		return Gdx.graphics.getHeight()*(1/3);
	}

	public void setLayoutEnabled(boolean enabled) {
		super.setLayoutEnabled(enabled);
	}

	public void validate() {
		super.validate();
	}

	public boolean needsLayout() {
		return super.needsLayout();
	}

	public void invalidate() {
		super.invalidate();
	}

	public void invalidateHierarchy() {
		super.invalidateHierarchy();
	}

	public void pack() {
		super.pack();
	}

	public void setFillParent(boolean fillParent) {
		super.setFillParent(fillParent);
	}
	
	public static void backing()	{
	}

	public void draw(Batch batch, float parentAlpha) {
	}

	public void layout() {
		super.layout();
	}
}
