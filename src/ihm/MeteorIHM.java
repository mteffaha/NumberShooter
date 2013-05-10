package ihm;

import gameEngine.Meteor;
import gameEngine.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MeteorIHM {

	private Meteor meteor;
	private Image display;
	private String texte;
	private int height;
	private int width;
	
	private final static int CIRCLE_HEIGHT = 175;
	private final static int CIRCLE_WIDTH = 175;
	
	public MeteorIHM(int value, float x, float y){
		try {
			this.display = new Image("../ressources/images/meteor2.png");
			this.meteor = new Meteor(value, false, x, y);
			this.texte = String.valueOf(meteor.getValue());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setDestroy(){
		this.meteor.setISDestroyed(true);
	}
	
	public boolean isDestroy(){
		return this.meteor.isDestroy();
	}
	
	public boolean touchGround(float ground){
		return this.meteor.getCentre().getY()+this.height >= ground ? true : false;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void displayImage(Font f){
		this.height = display.getHeight();
		this.width = display.getWidth();
		this.display.draw(meteor.getCentre().getX(), meteor.getCentre().getY());
		f.drawString(meteor.getCentre().getX() + display.getWidth()/6, meteor.getCentre().getY() + display.getHeight()/2,this.texte);
		
	}
	
	public void displayCircle(Font f,Graphics g){
		this.height = CIRCLE_HEIGHT;
		this.width = CIRCLE_WIDTH;
		g.fillOval(meteor.getCentre().getX(), meteor.getCentre().getY(), CIRCLE_HEIGHT, CIRCLE_WIDTH);
		f.drawString(meteor.getCentre().getX()+25, meteor.getCentre().getY()+25, this.texte, Color.black);
	}
	
	public void descente(float speed){
		this.meteor.getCentre().setY(meteor.getCentre().getY()+speed);
	}
	
	public int getValue(){
		return this.meteor.getValue();
	}
	
	public Point getCentre(){
		return this.meteor.getCentre();
	}
	
}
