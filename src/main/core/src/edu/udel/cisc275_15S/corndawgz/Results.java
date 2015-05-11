package edu.udel.cisc275_15S.corndawgz;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Results {
	
	//declarations
	private List<String> studentAnswers;
	private List<String> correctAnswers;
	
	private FileHandle caHandle;
	private FileHandle saHandle;
	
	private String correctAns;
	
	private String studentID;
	private int correct;
	private int wrong;
	
	public Results(String id){
		
		studentID = id;
		
		//Arrays to hold student and correct answers for comparison
		studentAnswers = new ArrayList<String>();
		correctAnswers = new ArrayList<String>();
		
		//file locations
		caHandle = Gdx.files.internal("data/correctAnswers.txt");
		saHandle = Gdx.files.internal("data/Student_Responses");
		
		/*
		 * Reads answers from text file
		 * Written in format
		 * 1:A
		 * 2:B
		 * 3:C
		 * ...
		 * Cuts of at \n
		 * Reads last letter at each line and stores in correctAnswers
		 */
		correctAns = caHandle.readString();
		int i = 0;
		int n;
		while(i < correctAns.length()){
			n = correctAns.indexOf("\n",i);
			if(n != -1){
				correctAnswers.add(correctAns.substring(i, n));
				i = n + 1;
			}
			else
				correctAnswers.add(correctAns.substring(i));
				i = correctAns.length();
		}
		
		for(String s : correctAnswers){
			s = s.substring(s.length()-1,s.length());
		}
		
		
	}
	
	public void addStudentResponse(int questionNumber, String response){
		studentAnswers.set(questionNumber - 1, response);
	}
	
	public void checkAnswers(){
		for(int i = 0; i < correctAnswers.size(); i++){
			if(correctAnswers.get(i).equalsIgnoreCase(studentAnswers.get(i))){
				correct++;
			}
			else{
				wrong++;
			}
		}
	}
	
	
	public void writeResults(){
		this.checkAnswers();
		
		saHandle.writeString(studentID + ":" + correct + "," + wrong + "\n", true);
	}
	
	
}
