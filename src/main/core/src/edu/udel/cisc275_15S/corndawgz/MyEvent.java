package edu.udel.cisc275_15S.corndawgz;

public interface MyEvent {
	public void render(float delta);
	public boolean done();
	public String getEventType();
}
