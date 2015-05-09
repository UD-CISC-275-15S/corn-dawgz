package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;


public class Phone {
	private ArrayList<String> text;
	private int index;
	
	public Phone() {
		index = 0;
		text = new ArrayList<String>();
	}

	public void addText(String s) {
		text.add(s);
	}
	
	public String getText() {
		return text.get(index);
	}
	
	public void next() {
		index++;
	}
	
	public void prev() {
		index--;
		if (index < 0) 
			index = 0;
	}
	
	public boolean hasNext() {
		return index < text.size();
	}
	

}
