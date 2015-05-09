package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageSequence extends Stage {
	private ArrayList<Image> images;
	private int index;
	
	public ImageSequence () {
		images = new ArrayList<Image>();
		index = 0;
	}

	public void update(){
		this.clear();
		this.addActor(images.get(index));
	}
	
	public void addImages(Image i) {
		i.setSize(this.getWidth(), this.getWidth());
		images.add(i);
		
	}
	
	public void addImages(ArrayList<Image> i) {
		images.addAll(i);
	}
	
	public void nextImage() {
		index++;
		this.clear();
		this.addActor(images.get(index));
	}
	public boolean hasNext() {
		if (index+1 >= images.size()) {
			return false;
		}
		return true;
	}
	
	public boolean hasPrev() {
		if(index-1 < 0){
			return false;
		}
		return true;
	}

	public void prevImage(){
		index--;
		this.clear();
		this.addActor(images.get(index));
	}
	
	public Image getImage() {
		return images.get(index);
	}
	
	public void setFillParentTrue() {
		for(Image i: images) {
			i.setFillParent(true);
		}
	}
	
	public void setAllScale(float s) {
		for (Image i : images) {
			i.setScale(s);
		}
	}
	
	public void setAllPosition(float x, float y) {
		for (Image i : images) {
			i.setPosition(x, y);
		}
	}
}
