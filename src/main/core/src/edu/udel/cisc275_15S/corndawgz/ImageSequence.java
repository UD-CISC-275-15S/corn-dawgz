package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageSequence extends Stage {
	private ArrayList<Image> images;
	private int counter;
	
	public ImageSequence () {
		images = new ArrayList<Image>();
		counter = 0;
	}

	public void update(){
		this.clear();
		this.addActor(images.get(counter));
	}
	
	public void addImages(Image i) {
		i.setSize(this.getWidth(), this.getWidth());
		images.add(i);
		
	}
	
	public void addImages(ArrayList<Image> i) {
		images.addAll(i);
	}
	
	public void nextImage() {
		counter++;
		this.clear();
		this.addActor(images.get(counter));
	}
	public boolean hasNext() {
		if (counter+1 >= images.size()) {
			return false;
		}
		return true;
	}
	
	public boolean hasPrev() {
		if(counter-1 < 0){
			return false;
		}
		return true;
	}

	public void prevImage(){
		counter--;
		this.clear();
		this.addActor(images.get(counter));
	}
	
	public void setFillParentTrue() {
		for(Image i: images) {
			i.setFillParent(true);
		}
	}
}
