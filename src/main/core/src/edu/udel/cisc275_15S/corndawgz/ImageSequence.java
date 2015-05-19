package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageSequence extends Stage {
	private ArrayList<ArrayList<Image>> images;
	private ArrayList<DialogueBox> dialogue;
	private int index;

	public ImageSequence() {
		images = new ArrayList<ArrayList<Image>>();
		dialogue = new ArrayList<DialogueBox>();
		index = 0;
	}

	public void update() {
		this.clear();
		for (Image i : images.get(index)) {
			this.addActor(i);
		}
		if (dialogue.get(index) != null) {
			this.addActor(dialogue.get(index));
		}
	}

	public void addImages(ArrayList<Image> list) {
		images.add(list);
		dialogue.add(null);
	}
	
	public void addImages(ArrayList<Image> list, DialogueBox d) {
		images.add(list);
		dialogue.add(d);
	}
	
	public void addImages(Image i) {
		i.setSize(this.getWidth(), this.getWidth());
		ArrayList<Image> list = new ArrayList<Image>();
		list.add(i);
		images.add(list);
		dialogue.add(null);
	}

	public void addImages(Image i, DialogueBox d) {
		i.setSize(this.getWidth(), this.getWidth());
		ArrayList<Image> list = new ArrayList<Image>();
		list.add(i);
		images.add(list);
		dialogue.add(d);
	}

	public void nextImage() {
		index++;
		this.clear();
		for (Image i : images.get(index)) {
			this.addActor(i);
		}
		if (dialogue.get(index) != null) {
			this.addActor(dialogue.get(index));
		}
	}

	public boolean hasNext() {
		if (index + 1 >= images.size()) {
			return false;
		}
		return true;
	}

	public boolean hasPrev() {
		if (index - 1 < 0) {
			return false;
		}
		return true;
	}

	public void prevImage() {
		index--;
		this.clear();
		for (Image i : images.get(index)) {
			this.addActor(i);
		}
		if (dialogue.get(index) != null) {
			this.addActor(dialogue.get(index));
		}
	}

	public Image getBackgroundImage() {
		return images.get(index).get(0);
	}

	public void setFillParentTrue() {
		for (ArrayList<Image> list : images) {
			for (Image i : list) {
				i.setFillParent(true);
			}
		}
	}

	public void setAllScale(float s) {
		for (ArrayList<Image> list : images) {
			for (Image i : list) {
				i.setScale(s);
			}
		}
	}
	
	public void setAllScaleX(float s) {
		for (ArrayList<Image> list : images) {
			for (Image i : list) {
				i.setScaleX(s);
			}
		}
	}
	
	public void setAllScaleY(float s) {
		for (ArrayList<Image> list : images) {
			for (Image i : list) {
				i.setScaleY(s);
			}
		}
	}

	public void setAllPosition(float x, float y) {
		for (ArrayList<Image> list : images) {
			for (Image i : list) {
				i.setPosition(x, y);
			}
		}
	}

	public int getIndex() {
		return index;
	}
}
