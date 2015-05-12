package edu.udel.cisc275_15S.corndawgz;

import java.io.BufferedReader;
import java.io.FileReader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;


public class StoryMode extends GameScreen {
	public long starttime;
	public FileHandle file = Gdx.files.internal("data/story.txt");
	public String storystring;
	public String workingstring;
	private Batch batch;
	public boolean active;
	public int activeindex;
	public int inactiveindex;
	public char activechar;
	public char inactivechar;
	public char[] chararray;
	public Image background;
	public Stage stage;
	public BitmapFont text;
	public MyActor myactor;
	public Texture texttest;
	public Text script;
	
	/*
	 * # background
	 * $ script
	 * % actor
	 * ^ start set up
	 * & end set up
	 * * end an if statement
	 * 
	 */
	
	private class MyActor extends Actor {
		Texture texture = new Texture(workingstring);
		//float actorX = 0, actorY= 0;
		public boolean started = false;
		
		public MyActor(){
            setBounds(0, 0,texture.getWidth(),texture.getHeight());
            this.setTouchable(Touchable.enabled);
            this.addListener(new InputListener(){
            	@Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ((MyActor)event.getTarget()).started = true;
                    System.out.println("touched");
                    return true;
                }
            }
            );
        }
		
		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(texture, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
		}
		
		@Override
		public void act(float delta){
			if(started == true)
				active = true;
		}
	}
	

	public class Text extends Actor {

	    BitmapFont font;      //I assumed you have some object 
	                        //that you use to access score.
	                        //Remember to pass this in!
	    public Text(){
	        font = new BitmapFont();
	        font.setColor(Color.WHITE);   //Brown is an underated Colour
	    }

	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	         font.draw(batch, workingstring, 100, 100);
	         //Also remember that an actor uses local coordinates for drawing within
	         //itself!
	    }

	}
	
	StoryMode(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
		active = true;
		activeindex = 0;
		storystring = file.readString();
		chararray = storystring.toCharArray();
		activechar = chararray[activeindex];
		}
	
	@Override
	public void show(){
		
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		while(activechar != '&'){
			
			if(activechar == '#'){
				inactiveindex = activeindex + 3;
				inactivechar = chararray[inactiveindex];
				workingstring = "";
				
				while(inactivechar != '%'){
					inactiveindex += 1;
					workingstring += inactivechar;
					inactivechar = chararray[inactiveindex];
				}

				workingstring = workingstring.substring(0, workingstring.length() - 2);
				
				background = new Image(new Texture(workingstring));
				
			}
			
			//System.out.println(activeindex + " " + activechar);
			if(activechar == '%'){
				inactiveindex = activeindex + 3;
				inactivechar = chararray[inactiveindex];
				workingstring = "";
				
				
				
				while(inactivechar != '$'){
					//System.out.println(workingstring);
					
					inactiveindex += 1;
					
					workingstring = workingstring + inactivechar;
					
					inactivechar = chararray[inactiveindex];
				}
				
				//System.out.println("headtom.png");
				//System.out.println(workingstring);
				workingstring = workingstring.substring(0, workingstring.length() - 2);
				try{	
				myactor = new MyActor();
				}
				catch (GdxRuntimeException e) {
					e.printStackTrace();
				}
			}
			
			
			if(activechar == '$'){
				inactiveindex = activeindex + 3;
				inactivechar = chararray[inactiveindex];
				workingstring = "";
				
				while(inactivechar != '&'){
					workingstring += inactivechar;
					inactiveindex += 1;
					inactivechar = chararray[inactiveindex];
				}
				System.out.println(workingstring);
				
				workingstring = workingstring.substring(0, workingstring.length() - 2);
				
				script = new Text();
			}
				
			
			
			++activeindex;
			activechar = chararray[activeindex];
				
		}
		
		//text = "TEST 1";
		active = true;
		stage.addActor(background);
		myactor.setTouchable(Touchable.enabled);
		
		stage.addActor(myactor);
		stage.addActor(script);
		
		starttime = TimeUtils.millis();
		
			
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.end();
		if (TimeUtils.millis()>(starttime+2000) && active) 
        	updateStory();
	}
	
	@Override
	public void resume(){
		
	}
	@Override
	public void dispose(){
		batch.dispose();
		texttest.dispose();
		
	}
	
	public void updateStory(){
		if(activechar == '&' && activeindex < chararray.length - 1){
			++activeindex;
			activechar = chararray[activeindex];
			while(activechar != '&'){
						if(activechar == '#'){
						inactiveindex = activeindex + 3;
						inactivechar = chararray[inactiveindex];
						workingstring = "";
						
						while(inactivechar != '%'){
							inactiveindex += 1;
							workingstring += inactivechar;
							inactivechar = chararray[inactiveindex];
						}

						workingstring = workingstring.substring(0, workingstring.length() - 2);
						
						background = new Image(new Texture(workingstring));
						
					}
					
					//System.out.println(activeindex + " " + activechar);
					if(activechar == '%'){
						inactiveindex = activeindex + 3;
						inactivechar = chararray[inactiveindex];
						workingstring = "";
						
						
						
						while(inactivechar != '$'){
							//System.out.println(workingstring);
							
							inactiveindex += 1;
							
							workingstring = workingstring + inactivechar;
							
							inactivechar = chararray[inactiveindex];
						}
						
						//System.out.println("headtom.png");
						//System.out.println(workingstring);
						workingstring = workingstring.substring(0, workingstring.length() - 2);
						try{	
						myactor = new MyActor();
						}
						catch (GdxRuntimeException e) {
							e.printStackTrace();
						}
					}
					
					
					if(activechar == '$'){
						inactiveindex = activeindex + 3;
						inactivechar = chararray[inactiveindex];
						workingstring = "";
						
						while(inactivechar != '&'){
							workingstring += inactivechar;
							inactiveindex += 1;
							inactivechar = chararray[inactiveindex];
						}
						System.out.println(workingstring);
						
						workingstring = workingstring.substring(0, workingstring.length() - 2);
						
						script = new Text();
					}
						
					
					
					++activeindex;
					activechar = chararray[activeindex];
						
			}
			active = false;
			stage.dispose();
			
			stage = new Stage();
			Gdx.input.setInputProcessor(stage);
			
			stage.addActor(background);
			stage.addActor(myactor);
			stage.addActor(script);
			
			starttime = TimeUtils.millis();
			
			
		}
	
	
	}
}
