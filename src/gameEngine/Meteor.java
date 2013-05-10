package gameEngine;

public class Meteor {	

	private int value;
	private boolean destroy;
	private Point position;
	
	
	
	public Meteor(int value,boolean isDestroy, float x, float y){
		this.position = new Point(x, y);
		this.destroy = isDestroy;
		this.value = value;
	}
	
	
	
	public int getValue(){
		return this.value;
	}
	
	public Point getCentre(){
		return this.position;
	}
	
	public boolean isDestroy(){
		return this.destroy;
	}

    public void setISDestroyed(boolean isDestroyed){
        destroy = isDestroyed;
    }

	
}
