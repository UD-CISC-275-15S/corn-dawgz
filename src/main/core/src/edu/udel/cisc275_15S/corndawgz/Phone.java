package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Phone {
	private final int APP_WIDTH = 117;
	private final int APP_HEIGHT = 119;
	private final int APP_BUFFER = 13;
	private final int HOME_BUTTON_SIZE = 120;
	private final int SMALL_PHONE_HEIGHT = 183;
	private final int SMALL_PHONE_WIDTH = 100;
	private final int LARGE_PHONE_HEIGHT = 1094;
	private final int LARGE_PHONE_WIDTH = 600;
	private int x;
	private int y;
	int currScreen;
	SpriteBatch batch;
	Texture phoneImg;
	Rectangle[] apps;
	boolean largePhone;
	boolean homeScreen;

	public Phone() {
		currScreen = 0;
		largePhone = false;
		homeScreen = false;
		batch = new SpriteBatch();
		phoneImg = new Texture("phone/phone.png");
		this.x = 20;
		this.y = Gdx.graphics.getHeight() - SMALL_PHONE_HEIGHT - 20;
		setApps();
	}

	public void clicked(float x, float y) {
		System.out.println("PHONE CLICKED");
		if (!largePhone)
			makeLarge();
		else if (!homeScreen && apps[0].contains(x, y)) {
			setAppScreen(0);
			System.out.println("home button");
		} else {
			for (int i = 1; i < apps.length; i++) {
				if (apps[i].contains(x, y)) {
					setAppScreen(i);
					System.out.println("button " + i);
				}
			}
		}
	}

	private void setApps() {
		apps = new Rectangle[6];
		// sets the position of the
		apps[0] = new Rectangle(this.x
				+ (LARGE_PHONE_WIDTH - HOME_BUTTON_SIZE / 2), this.y
				+ (HOME_BUTTON_SIZE / 2) + APP_BUFFER, HOME_BUTTON_SIZE,
				HOME_BUTTON_SIZE);
		apps[1] = new Rectangle(this.x + APP_BUFFER, this.y
				+ LARGE_PHONE_HEIGHT - APP_BUFFER, APP_WIDTH, APP_HEIGHT);
		apps[2] = new Rectangle(apps[1].getX() + APP_WIDTH + APP_BUFFER,
				apps[1].getY(), APP_WIDTH, APP_HEIGHT);
		apps[3] = new Rectangle(apps[2].getX() + APP_WIDTH + APP_BUFFER,
				apps[1].getY(), APP_WIDTH, APP_HEIGHT);
		apps[4] = new Rectangle(apps[3].getX() + APP_WIDTH + APP_BUFFER,
				apps[1].getY(), APP_WIDTH, APP_HEIGHT);
		apps[5] = new Rectangle(apps[1].getX(), apps[1].getY() - APP_HEIGHT
				- APP_BUFFER, APP_WIDTH, APP_HEIGHT);
	}

	private void setAppScreen(int i) {
		currScreen = i;
		if (i == 0) {
			// do nothing, this is home button
		}
		if (i == 1) {
			// this is messages
			phoneImg = new Texture("phone/messages.png");
			homeScreen = false;
		}
		if (i == 2) {
			// this is email
			phoneImg = new Texture("phone/email.png");
			homeScreen = false;
		}
		if (i == 3) {
			// this is UDSIS
			phoneImg = new Texture("phone/UDSIS.png");
			homeScreen = false;
		}
		if (i == 4) {
			// this is Phone
			phoneImg = new Texture("phone/phone.png");
			homeScreen = false;
		}
		if (i == 5) {
			// this is back to Map
			phoneImg = new Texture("phone/map.png");
			this.x = 20;
			this.y = Gdx.graphics.getHeight() - SMALL_PHONE_HEIGHT - 20;
			homeScreen = false;
			largePhone = false;
		}
	}

	public Texture getTexture() {
		return phoneImg;
	}

	private void makeLarge() {
		this.x = (Gdx.graphics.getWidth() - LARGE_PHONE_WIDTH) / 2;
		this.y = (Gdx.graphics.getHeight() - LARGE_PHONE_HEIGHT) / 2;
		phoneImg = new Texture("phone/phoneLarge.png");
		homeScreen = true;
		largePhone = true;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		if (largePhone) 
			return LARGE_PHONE_HEIGHT;
		return SMALL_PHONE_HEIGHT;
	}
	public int getWidth() {
		if (largePhone) 
			return LARGE_PHONE_WIDTH;
		return SMALL_PHONE_WIDTH;
	}
}
