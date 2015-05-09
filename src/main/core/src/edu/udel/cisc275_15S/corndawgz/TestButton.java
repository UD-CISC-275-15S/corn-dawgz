package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TestButton extends TextButton {
	private final float buttonWidth = 200;
	private final float buttonHeight = 20;
	private final float buttonOffset = 25;
	private String name;

	public TestButton(String text, Skin skin, String s) {
		super(text, skin);
		setWidth(buttonWidth);
		setHeight(buttonHeight);
		name = s;
	}
	
	// sets the position of the button
	public void setPosition(int offset, int buttonSize) {
		setPosition((Gdx.graphics.getWidth() - buttonSize)/2,
				(Gdx.graphics.getHeight() - buttonHeight)/2 - buttonOffset*offset);
	}
	
	public void addListener(final FileHandle file, final QuestionStage s){
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				file.writeString("\n" + name, true);
				s.clicked(); // notify observer
			}
		});
	}

}
