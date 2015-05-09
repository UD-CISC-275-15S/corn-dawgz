package edu.udel.cisc275_15S.corndawgz;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TempHallLocation extends GameScreen{
	// traits
	private String placename;
	/*
	 * place names are listed as such
	 * -memhall		-mcdhall	-trab	-purnhall	-gorehall	-smithhall	-
	 */
	
	public String getPic4Hall(String name){
		if(this.placename == "memhall"){
			return "memhall.jpg";
		}
		if(this.placename == "mcdhall"){
			return "mcdhall.jpg";
		}
		if(this.placename == "trab"){
			return "traba.jpg";
		}
		if(this.placename == "gorehall"){
			return "gorehallb.png";
		}
		if(this.placename == "smithhall"){
			return "smithhalla.jpg";
		}
		if(this.placename == "purnhall"){
			return "purnhalla.jpg";
		}
		else return "headtom.png";
	}
	
	
	public class MyActor extends Actor {
		Texture texture = new Texture(getPic4Hall(placename));
		float actorX = 0, actorY= 0;
		public boolean started = false;
		
		public MyActor(){
            setBounds(actorX,actorY,texture.getWidth(),texture.getHeight());
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ((MyActor)event.getTarget()).started = true;
                    return true;
                }
            });
        }
		
		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(texture, 100, 75);
		}
		
		@Override
		public void act(float delta){
			if(started){
				//game.setScreen(new QuestionReader(game));
			}
		}
	}
	
	private Stage stage;
	private Batch batch;

	
	public TempHallLocation(Game g, String name) {
		super(g);
		this.placename = name;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void show(){
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		MyActor myActor = new MyActor();
		myActor.setTouchable(Touchable.enabled);
		stage.addActor(myActor);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		//stage.dispose();
	}
	

}
