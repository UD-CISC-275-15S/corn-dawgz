package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BossButton extends TextButton {
	private final float textWidth = 8;
	private final float buttonHeight = 20;
	private final float buttonOffset = 25;
	private String name;

	public BossButton(String text, Skin skin, int offset, final BossBattle b, String n) {
		super(text, skin);
		this.setWidth(text.length() * textWidth);
		this.setHeight(buttonHeight);
		this.setPosition(10, 85f - buttonOffset*offset);
		name = n;
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				b.clicked(name);
			}
		});
	}
	
	@Override 
	public void setText(String s) {
		super.setText(s);
		this.setWidth(s.length() * textWidth);
	}
}
